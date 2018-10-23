package de.dreambeam.veusz
import java.awt.{Desktop, GraphicsEnvironment}
import java.io.{File, PrintWriter}
import java.nio.file.Paths

import de.dreambeam.veusz.model.Document
import de.dreambeam.veusz.renderer.VeuszRenderer

/**
  * Created by Kaufmann on 22.06.2017.
  */
object VeuszOutput {

  var outPath = "veusz"

  implicit class DocumentTools(document: Document) {

    def getVeuszText() = {
      VeuszRenderer(document)
    }

    def show(fileName: String, outdir: File = new File(VeuszOutput.outPath)) = {

      if (!outdir.exists()) outdir.mkdirs()

      save(fileName, outdir)
      val target = Paths.get(outdir.getAbsolutePath, s"$fileName.vsz")

      if(!GraphicsEnvironment.isHeadless())
        Desktop.getDesktop().open(target.toFile)
      else{
        println("No Graphics Environment found. Vuesz file saved at "+ target)
      }
    }

    def save(fileName: String, outdir: File = new File(VeuszOutput.outPath)) = {
      val text = getVeuszText()
      if (!outdir.exists()) outdir.mkdirs()
      val target = Paths.get(outdir.getAbsolutePath, s"$fileName.vsz")
      new PrintWriter(target.toFile) { write(text); close }
    }

  }

}
