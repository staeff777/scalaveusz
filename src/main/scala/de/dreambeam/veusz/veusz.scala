package de.dreambeam.veusz

import java.awt.Desktop
import java.io.{File, PrintWriter}
import java.nio.file.Paths

import util.RenderTools.newLine
import components._
import de.dreambeam.veusz.data.{BoxplotData, DateTime, Numerical, Text}
import de.dreambeam.veusz.util.MemoryTools

trait DocumentItem extends Item
trait PageItem extends Item
trait GridItem extends Item
trait GraphItem extends Item
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

  def dataImport = {
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
    MemoryTools.dataset.map {
      case (data: Numerical, reference) =>
        s"""
           |ImportString(u'$reference(numeric)','''
           |${createNumericTableHeader(data)}
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


      case _ => ""

      // TODO: Add 3D Data
    }.mkString(newLine)
  }

  def save(fileName: String, outdir: File = new File(Document.OutPath)): Unit = this match {
    case _: Document => {
      val text = dataImport + Renderer.renderAllItems
      if (!outdir.exists()) outdir.mkdirs()
      val target = Paths.get(outdir.getAbsolutePath, s"$fileName.vsz")
      val _ = new PrintWriter(target.toFile) { write(text); close }
    }

    case p: Page => Document(p).save(fileName)
    case g: Graph => Page(g).save(fileName)
    case g: Graph3D => Scene3D(g).save(fileName)
    case s: Scene3D => Page(s).save(fileName)
    case a: Axis => Graph(a).save(fileName)
    case bar: Barchart => Graph(bar).save(fileName)
    case fun: Function => Graph(fun).save(fileName)
    case img: ImageFile => Page(img).save(fileName)
    case rect: Rectangle => Page(rect).save(fileName)
    case el: Ellipse => Page(el).save(fileName)
    case line: Line => Page(line).save(fileName)
    case poly: Polygon => Page(poly).save(fileName)
    case cb: Colorbar => Graph(cb).save(fileName)
    case con: Contour => Graph(con).save(fileName)
    case vec: Vectorfield => Graph(vec).save(fileName)
    case cov: Covariance => Graph(cov).save(fileName)
  }

  def show(fileName: String, outdir: File = new File(Document.OutPath)) = {
    if (!outdir.exists()) outdir.mkdirs()

    save(fileName, outdir)
    val target = Paths.get(outdir.getAbsolutePath, s"$fileName.vsz")

    Desktop.getDesktop().open(target.toFile)
  }
}
