package de.dreambeam.veusz

import java.awt.Desktop
import java.io.{File, IOException, PrintWriter}
import java.nio.file.Paths

import util.RenderTools.newLine
import components._
import de.dreambeam.veusz.data.{BoxplotData, DateTime, Numerical, NumericalImage, Text}
import de.dreambeam.veusz.util.DataHandler
import de.dreambeam.veusz.format._



object GlobalVeuszSettings {
  var veuszPath: String = "veusz"
  var defaultSaveDirectory = "veusz"
}

trait DocumentItem extends Item

trait PageItem extends Item

trait GridItem extends Item

trait GraphItem extends Item

trait PolarGraphItem extends Item

trait Scene3DItem extends Item

trait Graph3DItem extends Item

trait Parent {
  def children: Option[Vector[Item]]
}

trait Item {
  def group: String

  def name: String
}

trait Configurable

trait Executable {
  val NewLine = "\n"

  /**
  * writes all data from the datahandler into the vuesz-Document
    * @param dataHandler
    * @return
    */
  protected def dataImport(dataHandler: DataHandler) = {
    def createNumericTableHeader(d: Numerical) = {
      (if (d.symErrors.isDefined) ",+-" else "") +
        (if (d.negErrors.isDefined) ",-" else "") +
        (if (d.posErrors.isDefined) ",+" else "")
    }

    def createNumericDataTable(d: Numerical) = {
      def writeOptional(ov: Option[Vector[Double]], i: Int) = {
        ov match {
          case Some(v) => " " + v(i)
          case None    => ""
        }
      }

      d.data.zipWithIndex.map {
        case (value, i) =>
          value.toString +
            writeOptional(d.symErrors, i) +
            writeOptional(d.negErrors, i) +
            writeOptional(d.posErrors, i)
      }
    }

    dataHandler.dataset
      .map {
        case (data: Numerical, reference) =>
          s"""
           |ImportString(u'$reference(numeric)${createNumericTableHeader(data)}','''
           |${createNumericDataTable(data).mkString(newLine)}
           |''')
         """.stripMargin

        case (data: DateTime, reference) =>
          s"""
           |ImportString(u'$reference(data)','''
           |${data.data.mkString(newLine)}
           |''')
         """.stripMargin

        case (data: Text, reference) =>
          s"""
           |SetDataText(u'$reference', [
           |${data.data.map(t => s"u'${t}'").mkString("," + newLine)}
           |])
         """.stripMargin

        case (data: BoxplotData, reference) => {
          data.data.map(d => s"""
             |ImportString(u'$reference(numeric)','''
             |${createNumericTableHeader(d)}
             |${createNumericDataTable(d).mkString(newLine)}
             |''')
         """.stripMargin).mkString(newLine)
        }
        case (data: NumericalImage, reference) =>
          val xs = data.map.keys.map(_._1).toVector.sorted
          val ys = data.map.keys.map(_._2).toVector.sorted

          val xRange = s"(${xs.head},${xs.last})"
          val yRange = s"(${ys.head},${ys.last})"

          val stringMap = ys.reverse.foldLeft("")(
                    (text, y) =>
                      text
                        + xs.foldLeft("")((line, x) =>
                          line
                            + data.map((x, y))
                            + " ")
                        + NewLine)
          s"""
           |ImportString2D(u'$reference', '''
           |$stringMap
           |''', xrange=${xRange}, yrange=${yRange})
      """.stripMargin

        case _ => ""

        // TODO: Add 3D Data
      }
      .mkString(newLine)
  }

  /**
  * adds all requiered parents until a Document is formed
  * renders this document as .vsz document text
  * @return
  */
  protected def createDocumentText(): String = this match {

    case document: Document => {
      val dataHandler = DataHandler()
      val renderedContent = Renderer.renderAllItems(document, dataHandler)
      val text = dataImport(dataHandler) + renderedContent
      text
    }
    case p: Page          => Document(p).createDocumentText()
    case g: Graph         => Page(g).createDocumentText()
    case p: PolarGraph    => Page(p).createDocumentText()
    case g: Graph3D       => Scene3D(g).createDocumentText()
    case s: Scene3D       => Page(s).createDocumentText()
    case a: Axis          => Graph(a).createDocumentText()
    case fun: Function    => Graph(fun).createDocumentText()
    case img: ImageFile   => Page(img).createDocumentText()
    case rect: Rectangle  => Page(rect).createDocumentText()
    case el: Ellipse      => Page(el).createDocumentText()
    case line: Line       => Page(line).createDocumentText()
    case poly: Polygon    => Page(poly).createDocumentText()
    case cb: Colorbar     => Graph(cb).createDocumentText()
    case img2d: Image     => Graph(img2d).createDocumentText()
    case con: Contours     => Graph(con).createDocumentText()
    case vec: Vectorfield => Graph(vec).createDocumentText()
    case cov: Covariance  => Graph(cov).createDocumentText()
    case xy: XY           => Graph(xy).createDocumentText()
    case no: NonOrthPoint => PolarGraph(no).createDocumentText()
    case nf: NonOrthFunction => PolarGraph(nf).createDocumentText()
    case bar: Barchart =>
      bar.positions match {
        case d: DateTime =>
          val xAxis = XAxis(mode = AxisMode.DateTime)
          val yAxis = YAxis()
          Graph(children = bar, axis = Vector(xAxis, yAxis)).createDocumentText()
        case n: Numerical => Graph(bar).createDocumentText()
      }
    case box: Boxplot => Graph(box).createDocumentText()
    case x            => throw new RuntimeException(s" $x can not be processed directly")
  }

  /**
    * Save the current object as Veusz Document
    * @param file file object
    */
  def saveAsVuesz(file: File) = {
    val text = this.createDocumentText()

    val _ = new PrintWriter(file) {
      write(text); close
    }
    file
  }

  /**
  * Save the current object as Veusz Document
    * @param fileName filename without the vsz extension
    * @param outdir directory where to save it
    *               default directory GlobalVeuszSettings.defaultSaveDirectory is the ./vuesz directory
    *               directory will be created if it does not exist
    */
  def saveAsVuesz(fileName: String, outdir: File = new File(GlobalVeuszSettings.defaultSaveDirectory)) = {
    val text = this.createDocumentText()

    if (!outdir.exists()) outdir.mkdirs()
    val target = Paths.get(outdir.getAbsolutePath, s"$fileName.vsz")
    val targetFile = target.toFile
    val _ = new PrintWriter(targetFile) {
      write(text); close
    }
    targetFile
  }

  private def saveTemp(text: String): File = {
    val file = File.createTempFile("scalavuesz_", ".vsz")
    val _ = new PrintWriter(file) {
      write(text); close
    }
    file
  }

  private def saveTemp():File = {
    val text = this.createDocumentText()
    saveTemp(text)
  }

  /**
    * Save the current object as Veusz Document and open it in Veusz
    * @param fileName filename without the vsz extension,
    *                 if filename is empty a temporary file will be created
    * @param outdir directory where to save it
    *               default directory GlobalVeuszSettings.defaultSaveDirectory is the ./vuesz directory
    *               directory will be created if it does not exist
    */
  def openInVeusz(fileName: String = "", outdir: File = new File(GlobalVeuszSettings.defaultSaveDirectory)) = {


    val file =
      if(fileName != "") {
        if (!outdir.exists()) outdir.mkdirs()
        saveAsVuesz(fileName, outdir)
      } else
        saveTemp()

    val target = Paths.get(outdir.getAbsolutePath, s"$fileName.vsz")

    Desktop.getDesktop().open(file)
  }


  /**
  * Export to on image file
    *
    * To be able to use the export command, you need to have 'vuesz' in your \n Path settings or you can set an absolte path at 'GlobalVeuszSettings.veuszPath
    *
    * @param filePath  the file extension defines the format (PDF, SVG, EMF, PNG, JPG, BMP, TIFF, XMP)
    * @param pages Page Numbers starting from 0, starting from 0 (empty Vector means all pages) only works for PDF
    * @param color use colors
    * @param dpi resolution for images
    * @param antialias
    * @param quality JPG quality
    * @param backcolor BG Color in #FFFFFF - Format
    * @param pdfdpi PDF resolution
    * @param svgtextastext Editable Text in SVG
    * @param waitForProcess wait until export process is finished
    * @return process
    */
  def export(filePath: String,
             pages: Vector[Int] = Vector(),
             color: Boolean = true,
             dpi: Double = 100,
             antialias: Boolean = true,
             quality: Int = 85,
             backcolor: String = "#ffffff00",
             pdfdpi: Double = 150,
             svgtextastext: Boolean = false,
             waitForProcess: Boolean = true) = {

    val exportOptions = {
      val dpiSettings = "dpi=" + dpi
      val pdfdpiSettings = "pdfdpi=" + pdfdpi
      val colorSettings = "color=" + (if (color) "True" else "False")
      val qualityString = "quality=" + quality

      val backcolorSettings = "backcolor='" + backcolor + "'"

      val antialiasSettings = "antialias=" + (if (antialias) "True" else "False")
      val svgAsTextSettings = "svgtextastext=" + (if (svgtextastext) "True" else "False")

      val pageSettings = (this) match {
        case d: Document if pages.length == 0 => s"page=(${(0 until d.children.get.size).mkString(",")})"
        case d: Document => s"page=(${pages.mkString(",")})"
        case _           => ""
      }

      val exportOptionsBaseArray = Array(dpiSettings, pdfdpiSettings, colorSettings, qualityString, backcolorSettings, antialiasSettings, svgAsTextSettings)
      val exportOptionsArray = if (pageSettings.length > 0) exportOptionsBaseArray :+ pageSettings else exportOptionsBaseArray
      exportOptionsArray.mkString(",")
    }

    val documentText = this.createDocumentText()
    val exportCommand =
      s"""
         |Export("$filePath")
         |Quit()
         |""".stripMargin
    val file = saveTemp(documentText + exportCommand)

    try {
      val processBuilder = new ProcessBuilder
      processBuilder.command(GlobalVeuszSettings.veuszPath, s"--export=$filePath", s"--export-option=$exportOptions", file.getAbsolutePath)
      val process = processBuilder.start
      if (waitForProcess) process.waitFor
      process
    } catch {
      case e: IOException =>
        val newMEssage =
          s"${e.getMessage} \n ${"*" * 80} \n To be able to use the export command, you need to have 'vuesz' in your \n Path settings or you can set an absolte path at 'GlobalVeuszSettings.veuszPath'. \n ${"*" * 80}"

        val ee = new IOException(newMEssage, e.getCause)
        throw (ee)
    }
  }

  /**
    * Export to on image file and open it using the default viewer
    * @param filePath  the file extension defines the format (PDF, SVG, EMF, PNG, JPG, BMP, TIFF, XMP)
    * @param pages Page Numbers starting from 0, starting from 0 (empty Vector means all pages) only works for PDF
    * @param color use colors
    * @param dpi resolution for images
    * @param antialias
    * @param quality JPG quality
    * @param backcolor BG Color in #FFFFFF - Format
    * @param pdfdpi PDF resolution
    * @param svgtextastext Editable Text in SVG
    * @return
    */
  def exportAndOpen(filePath: String,
                    pages: Vector[Int] = Vector(),
                    color: Boolean = true,
                    dpi: Double = 100,
                    antialias: Boolean = true,
                    quality: Int = 85,
                    backcolor: String = "#ffffff00",
                    pdfdpi: Double = 150,
                    svgtextastext: Boolean = false): Unit = {
    export(filePath, pages, color, dpi, antialias, quality, backcolor, pdfdpi, svgtextastext)
    Desktop.getDesktop().open(new File(filePath))
  }

  /***
   * Shurtcut method the set the GlobalVeuszSettings.veuszPath
    * @param p
    */
  def setGlobalVeuszPath(p: String) = GlobalVeuszSettings.veuszPath = p

}
