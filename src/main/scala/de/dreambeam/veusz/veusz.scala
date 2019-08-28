package de.dreambeam.veusz

import java.awt.Desktop
import java.io.{File, PrintWriter}
import java.nio.file.Paths

import util.RenderTools.newLine
import components._
import de.dreambeam.veusz.data.{BoxplotData, DateTime, Numerical, NumericalImage, Text}
import de.dreambeam.veusz.util.DataHandler
import de.dreambeam.veusz.format._

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

  def dataImport(dataHandler: DataHandler) = {
    def createNumericTableHeader(d: Numerical) = {
      (if (d.symErrors.isDefined) ",+-" else "") +
        (if (d.negErrors.isDefined) ",-" else "") +
        (if (d.posErrors.isDefined) ",+" else "")
    }

    def createNumericDataTable(d: Numerical) = {
      def writeOptional(ov: Option[Vector[Double]], i: Int) = {
        ov match {
          case Some(v) => " " + v(i)
          case None => ""
        }
      }

      d.data.zipWithIndex.map {
        case (value, i) =>
          value +
            writeOptional(d.symErrors, i) +
            writeOptional(d.negErrors, i) +
            writeOptional(d.posErrors, i)
      }
    }

    dataHandler.dataset.map {
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
        data.data.map(d =>
          s"""
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
    }.mkString(newLine)
  }

  def save(fileName: String, outdir: File = new File(Document.OutPath)): Unit = this match {

    case document: Document => {
      val dataHandler = DataHandler()
      val renderedContent = Renderer.renderAllItems(document, dataHandler)
      val text = dataImport(dataHandler) + renderedContent
      if (!outdir.exists()) outdir.mkdirs()
      val target = Paths.get(outdir.getAbsolutePath, s"$fileName.vsz")
      val _ = new PrintWriter(target.toFile) {
        write(text); close
      }
    }

    case p: Page => Document(p).save(fileName)
    case g: Graph => Page(g).save(fileName)
    case p: PolarGraph => Page(p).save(fileName)
    case g: Graph3D => Scene3D(g).save(fileName)
    case s: Scene3D => Page(s).save(fileName)
    case a: Axis => Graph(a).save(fileName)
    case fun: Function => Graph(fun).save(fileName)
    case img: ImageFile => Page(img).save(fileName)
    case rect: Rectangle => Page(rect).save(fileName)
    case el: Ellipse => Page(el).save(fileName)
    case line: Line => Page(line).save(fileName)
    case poly: Polygon => Page(poly).save(fileName)
    case cb: Colorbar => Graph(cb).save(fileName)
    case img2d: Image => Graph(img2d).save(fileName)
    case con: Contour => Graph(con).save(fileName)
    case vec: Vectorfield => Graph(vec).save(fileName)
    case cov: Covariance => Graph(cov).save(fileName)
    case nono: NonOrthPoint => PolarGraph(nono).save(fileName)
    case bar: Barchart => bar.positions match {
      case d: DateTime =>
        val xAxis = XAxis(mode = AxisMode.DateTime)
        val yAxis = YAxis()
        Graph(children = bar, axis = Vector(xAxis, yAxis)).save(fileName)
      case n: Numerical => Graph(bar).save(fileName)
    }
  }

  def show(fileName: String, outdir: File = new File(Document.OutPath)) = {
    if (!outdir.exists()) outdir.mkdirs()

    save(fileName, outdir)
    val target = Paths.get(outdir.getAbsolutePath, s"$fileName.vsz")

    Desktop.getDesktop().open(target.toFile)
  }
}
