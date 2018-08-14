package veusz.model

import java.awt.Desktop
import java.io.{File, PrintWriter}


/**
  * Created by Kaufmann on 22.06.2017.
  */
object VeuszOutput {


  implicit class DocumentTools(document:Document) {



    def getVeuszText()= {
       VeuszRenderer(document)
    }


    def show(fileName:String) = {
      val outdir = new File("veusz")
      if(!outdir.exists()) outdir.mkdirs()

      save(fileName)
      val target = s"veusz/$fileName.vsz"
      val targetFile = new File(target)
      Desktop.getDesktop().open(targetFile)
    }


    def save(fileName:String) = {
      val text = getVeuszText()
      val target = s"veusz/$fileName.vsz"
      new PrintWriter(target) { write(text); close }
    }



  }



}
