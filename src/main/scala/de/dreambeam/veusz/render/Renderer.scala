package de.dreambeam.veusz

import de.dreambeam.veusz.components.{graph, _}
import de.dreambeam.veusz.components.graph.{Axis, Barchart, Boxplot, Contours, Covariance, Fit, Graph, Image, Vectorfield, XY}
import de.dreambeam.veusz.components.graph3d.{Axis3D, Function3D, Graph3D, PlotLine3DConfig, Point3D, Scene3D, Surface3D, Surface3DGridLineConfig, Surface3DSurfaceConfig, Volume3D}
import de.dreambeam.veusz.components.nonorthgraphs.{NonOrthFunction, NonOrthPoint, PolarGraph, TernaryGraph}
import de.dreambeam.veusz.components.shapes.{Ellipse, ImageFile, LineLengthAngle, LinePoint2Point, Polygon, Rectangle}
import de.dreambeam.veusz.data.{DateTime, Numerical, Text}
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.util.{DataHandler, RenderTools => R, StringTools => S}

/**
  * Renderer contains all methods for rendering
  * the Veusz components
  */
object Renderer {

  def renderAllItems(document: Document, dataHandler: DataHandler) = {
    val renderer = new Renderer(dataHandler)
    renderer.renderDocument(document)
  }
}

class Renderer(dataHandler: DataHandler) {

  /**
    * Render all items recursively starting with the Document
    *
    * @return the rendered text for the Veusz components
    */
  def renderDocument(document: Document): String = {

    def go(item: Item): String = {

      def autoWrapWrappedGridItem(i: WrappedGridItem) = i match {
        case g: GridItem           => go(g)
        case g: GraphItem          => go(Graph(g)).mkString("")
        case n: WrapInTernaryGraph => go(TernaryGraph(n)).mkString("")
        case n: NonOrthGraphItem   => go(PolarGraph(n)).mkString("")
        case s: Scene3DItem        => go(Scene3D(s)).mkString("")
        case g: Graph3DItem        => go(Scene3D(Graph3D(g))).mkString("")
        case e                     => throw new RuntimeException(s"$e not matched")
      }

      def autoWrapPageItem(p: WrappedPageItem) = p match {
        case p: Ellipse         => go(p)
        case p: ImageFile       => go(p)
        case p: LineLengthAngle => go(p)
        case p: LinePoint2Point => go(p)
        case p: Label           => go(p)
        case p: Polygon         => go(p)
        case r: Rectangle       => go(r)
        case g: Grid            => go(g)
        case g: Graph           => go(g)
        case p: PolarGraph      => go(p)
        case t: TernaryGraph    => go(t)
        case s: Scene3D         => go(s)
        case i: WrappedGridItem => autoWrapWrappedGridItem(i)
      }

      val childRender = item match {
        // a graph must render its axes,
        // since these are not under the category of children
        case g: Graph => {
          g.axis.map(go).mkString("") + (g.children.map(go).mkString(""))
        }

        case g: Graph3D => {
          g.axis.map(go).mkString("") + (g.children.map(go).mkString(""))
        }

        // allow other children than griditems
        case g: Grid => g.children.map(autoWrapWrappedGridItem).mkString("")

        case p: Page =>
          p.children
            .map(autoWrapPageItem)
            .mkString("")

        case d: Document =>
          d.children
            .map(c =>
              c match {
                case p: Page => go(p)
                case p: Item => go(Page(p))
            })
            .mkString("")
        // a parent is a component that has children
        // and thus triggers the recursive render
        case x: Parent => x.children.map(go).mkString("")

        // and if no children exist, do nothing
        case _ => ""
      }

      item match {

        case d: Document => {
          s"""
             |${render(d)}
             |$childRender
         """.stripMargin
        }

        case x => {

          val name = x match {
            case axis: Axis   => S.noBlanks(item.name)
            case axis: Axis3D => S.noBlanks(item.name)
            case _            => S.uniqueName(S.noBlanks(item.name))
          }

          val entry = s"Add('${item.group}', name='$name', autoadd=False)"
          val to = s"To('$name')"
          val exit = "To('..')"

          val thisRender = render(x)
          s"""
             |$entry
             |$to
             |$thisRender
             |$childRender
             |$exit
          """.stripMargin
        }
      }
    }
    go(document)
  }

  def render(item: Item): String = item match {
    case d: Document     => render(d)
    case p: Page         => render(p)
    case g: Grid         => render(g)
    case g: Graph        => render(g)
    case p: PolarGraph   => render(p)
    case t: TernaryGraph => render(t)
    case g: Graph3D      => render(g)
    case a: Axis         => render(a)
    case a: Axis3D       => render(a)
    case s: Scene3D      => render(s)
    case k: Key          => render(k)
    case xy: XY          => render(xy)
    case bp: Boxplot     => render(bp)
    case bar: Barchart   => render(bar)
    case fun: graph.Function => render(fun)
    case fit: Fit              => render(fit)
    case img: Image            => render(img)
    case img: ImageFile        => render(img)
    case l: Label              => render(l)
    case rect: Rectangle       => render(rect)
    case el: Ellipse           => render(el)
    case poly: Polygon         => render(poly)
    case line: LineLengthAngle => render(line)
    case line: LinePoint2Point => render(line)
    case cb: Colorbar          => render(cb)
    case cont: Contours        => render(cont)
    case vec: Vectorfield      => render(vec)
    case cov: Covariance       => render(cov)
    case no: NonOrthPoint      => render(no)
    case nf: NonOrthFunction   => render(nf)
    case p3: Point3D           => render(p3)
    case s3: Surface3D         => render(s3)
    case v3: Volume3D          => render(v3)
    case f3: Function3D        => render(f3)
    case x                     => throw new RuntimeException(x.toString + " is currently not supported")
  }

  def render(d: Document) =
    s"""
       |${R.render("width", d.config.width)}
       |${R.render("height", d.config.height)}
       |${R.render("englishlocale", d.config.englishLocale)}
     """.stripMargin

  def render(p: Page) =
    s"""
       |${R.renderNotes(p.config.notes)}
       |${R.render("width", p.config.width)}
       |${R.render("height", p.config.height)}
     """.stripMargin

  def render(g: Grid) =
    s"""
       |${R.render("rows", g.rows)}
       |${R.render("columns", g.columns)}
       |Set('scaleRows', [${g.scaleRows.mkString(",")}])
       |Set('scaleCols', [${g.scaleCols.mkString(",")}])
       |
       |# Grid Formatting
       |${R.render("leftMargin", g.config.leftMargin)}
       |${R.render("rightMargin", g.config.rightMargin)}
       |${R.render("topMargin", g.config.topMargin)}
       |${R.render("bottomMargin", g.config.bottomMargin)}
       |${R.render("internalMargin", g.config.internalMargin)}
     """.stripMargin

  def render(g: Graph) =
    s"""
       |${R.renderNotes(g.config.notes)}
       |
       |# Graph Formatting
       |${R.render("hide", g.config.main.hide)}
       |${R.render("leftMargin", g.config.main.leftMargin)}
       |${R.render("rightMargin", g.config.main.rightMargin)}
       |${R.render("topMargin", g.config.main.topMargin)}
       |${R.render("bottomMargin", g.config.main.bottomMargin)}
       |
       |${renderBackgroundConfig(g.config.background)}
       |
       |${renderBorderConfig(g.config.border)}
     """.stripMargin

  def render(p: PolarGraph) =
    s"""

       |${R.renderOption("minradius", p.minRadius, "u'Auto'")}
       |${R.renderOption("maxradius", p.maxRadius, "u'Auto'")}
       |${R.render("units", p.units)}
       |${R.render("direction", p.direction)}
       |${R.render("position0", p.positionOf0)}
       |${R.render("log", p.log)}
       |
       |# Graph Formatting
       |${R.render("hide", p.config.main.hide)}
       |${R.render("leftMargin", p.config.main.leftMargin)}
       |${R.render("rightMargin", p.config.main.rightMargin)}
       |${R.render("topMargin", p.config.main.topMargin)}
       |${R.render("bottomMargin", p.config.main.bottomMargin)}
       |#Tick Labels
       |${R.render("TickLabels")("font", p.config.tickLabels.font)}
       |${R.render("TickLabels")("size", p.config.tickLabels.size)}
       |${R.render("TickLabels")("color", p.config.tickLabels.color)}
       |${R.render("TickLabels")("italic", p.config.tickLabels.italic)}
       |${R.render("TickLabels")("bold", p.config.tickLabels.bold)}
       |${R.render("TickLabels")("underline", p.config.tickLabels.underline)}
       |${R.render("TickLabels")("format", p.config.tickLabels.format)}
       |${R.render("TickLabels")("scale", p.config.tickLabels.scale)}
       |${R.render("TickLabels")("hideradial", p.config.tickLabels.hideRadial)}
       |${R.render("TickLabels")("hidetangential", p.config.tickLabels.hideRadial)}
       |#Spoke Line
       |${R.render("SpokeLine")("color", p.config.spokeLine.color)}
       |${R.render("SpokeLine")("width", p.config.spokeLine.width)}
       |${R.render("SpokeLine")("style", p.config.spokeLine.lineStyle)}
       |${R.render("SpokeLine")("transparency", p.config.spokeLine.transparency)}
       |${R.render("SpokeLine")("hide", p.config.spokeLine.hide)}
       |#Radii Line
       |${R.render("RadiiLine")("color", p.config.radiiLine.color)}
       |${R.render("RadiiLine")("width", p.config.radiiLine.width)}
       |${R.render("RadiiLine")("style", p.config.radiiLine.lineStyle)}
       |${R.render("RadiiLine")("transparency", p.config.radiiLine.transparency)}
       |${R.render("RadiiLine")("number", p.config.radiiLine.number)}
       |${R.render("RadiiLine")("hide", p.config.radiiLine.hide)}
       |
       |${renderBackgroundConfig(p.config.background)}
       |
       |${renderBorderConfig(p.config.border)}
     """.stripMargin

  def render(t: TernaryGraph) =
    s"""
       |${R.render("mode", t.mode)}
       |${R.render("coords", t.coord_System)}
       |${R.render("labelbottom", t.label_bottom)}
       |${R.render("labelleft", t.label_left)}
       |${R.render("labelright", t.label_right)}
       |${R.render("originleft", t.left_origin)}
       |${R.render("originbottom", t.bottom_origin)}
       |${R.render("fracsize", t.size)}
       |${R.render("reverse", t.reverse)}
       |
       |# Graph Formatting
       |${R.render("hide", t.config.main.hide)}
       |${R.render("leftMargin", t.config.main.leftMargin)}
       |${R.render("rightMargin", t.config.main.rightMargin)}
       |${R.render("topMargin", t.config.main.topMargin)}
       |${R.render("bottomMargin", t.config.main.bottomMargin)}
       |
       |${renderBackgroundConfig(t.config.background)}
       |${renderBorderConfig(t.config.border)}
       |${renderLabelConfig(t.config.axisLabel)}
       |${renderTernaryTickLabelsConfig(t.config.tickLabelsConfig)}
       |${renderMajorTicksConfig(t.config.majorTicks)}
       |${renderMinorTicksConfig(t.config.minorTicks)}
       |${renderMajorGridLinesConfig(t.config.majorGridLines)}
       |${renderMinorGridLinesConfig(t.config.minorGridLines)}
     """.stripMargin

  def render(g: Graph3D) =
    s"""
       |${R.render("xSize", g.xSize)}
       |${R.render("ySize", g.ySize)}
       |${R.render("zSize", g.zSize)}
       |
       |# Graph3D Formatting
       |${R.render("hide", g.config.main.hide)}
       |${R.render("Border")("color", g.config.border.color)}
       |${R.render("Border")("width", g.config.border.width)}
       |${R.render("Border")("style", g.config.border.style)}
       |${R.render("Border")("transparency", g.config.border.transparency)}
       |${R.render("Border")("reflectivity", g.config.border.reflectivity)}
       |${R.render("Border")("hide", g.config.border.hide)}
       |
       |${R.render("Back")("color", g.config.back.color)}
       |${R.render("Back")("transparency", g.config.back.transparency)}
       |${R.render("Back")("reflectivity", g.config.back.reflectivity)}
       |${R.render("Back")("hide", g.config.back.hide)}
     """.stripMargin

  def render(a: Axis) =
    s"""
       |${R.render("direction", a.direction)}
       |${R.render("label", a.label)}
       |${R.renderOption("min", a.min, s"Set('min', u'Auto')")}
       |${R.renderOption("max", a.max, s"Set('max', u'Auto')")}
       |${R.render("mode", a.mode)}
       |${R.render("datascale", a.scale)}
       |${R.render("lowerPosition", a.minPos)}
       |${R.render("upperPosition", a.maxPos)}
       |${R.render("direction", a.direction)}
       |${R.render("otherPosition", a.axisPosition)}
       |
       |# Axis Formatting
       |${R.render("hide", a.config.main.hide)}
       |${R.render("autoRange", a.config.main.autoRange)}
       |${R.render("autoMirror", a.config.main.autoMirror)}
       |${R.render("reflect", a.config.main.reflect)}
       |${R.render("outerticks", a.config.main.outerTicks)}
       |
       |${renderLineConfig(a.config.axisLine)}
       |
       |${renderLabelConfig(a.config.axisLabel)}
       |
       |${renderTickLabelsConfig(a.config.tickLabels)}
       |
       |${renderMajorTicksConfig(a.config.majorTicks)}
       |
       |Set('MajorTicks/manualTicks', [${a.config.majorTicks.manualTicks}])
       |
       |${renderMinorTicksConfig(a.config.minorTicks)}
       |
       |${renderMajorGridLinesConfig(a.config.majorGridLines)}
       |
       |${renderMinorGridLinesConfig(a.config.minorGridLines)}
     """.stripMargin

  def render(a: Axis3D) =
    s"""
       |${R.render("direction", a.direction)}
       |${R.render("label", a.label)}
       |${R.renderOption("min", a.min, s"Set('min', u'Auto')")}
       |${R.renderOption("max", a.max, s"Set('max', u'Auto')")}
       |${R.render("mode", a.mode)}
       |${R.render("datascale", a.scale)}
       |${R.render("lowerPosition", a.minPos)}
       |${R.render("upperPosition", a.maxPos)}
       |${R.render("direction", a.direction)}
       |${R.render("otherPosition1", a.axisPosition1)}
       |${R.render("otherPosition2", a.axisPosition2)}
       |
       |# Axis Formatting
       |${R.render("hide", a.config.main.hide)}
       |${R.render("autoRange", a.config.main.autoRange)}
       |${R.render("autoMirror", a.config.main.autoMirror)}
       |
       |${R.render("Line")("color", a.config.axisLine.color)}
       |${R.render("Line")("width", a.config.axisLine.width)}
       |${R.render("Line")("style", a.config.axisLine.style)}
       |${R.render("Line")("transparency", a.config.axisLine.transparency)}
       |${R.render("Line")("reflectivity", a.config.axisLine.reflectivity)}
       |${R.render("Line")("hide", a.config.axisLine.hide)}
       |
       |${R.render("Label")("font", a.config.axisLabel.font)}
       |${R.render("Label")("size", a.config.axisLabel.size)}
       |${R.render("Label")("color", a.config.axisLabel.color)}
       |${R.render("Label")("italic", a.config.axisLabel.italic)}
       |${R.render("Label")("bold", a.config.axisLabel.bold)}
       |${R.render("Label")("underline", a.config.axisLabel.underline)}
       |${R.render("Label")("hide", a.config.axisLabel.hide)}
       |${R.render("Label")("position", a.config.axisLabel.position)}
       |
       |${R.render("TickLabels")("font", a.config.tickLabels.font)}
       |${R.render("TickLabels")("size", a.config.tickLabels.size)}
       |${R.render("TickLabels")("color", a.config.tickLabels.color)}
       |${R.render("TickLabels")("italic", a.config.tickLabels.italic)}
       |${R.render("TickLabels")("bold", a.config.tickLabels.bold)}
       |${R.render("TickLabels")("underline", a.config.tickLabels.underline)}
       |${R.render("TickLabels")("hide", a.config.tickLabels.hide)}
       |${R.render("TickLabels")("format", a.config.tickLabels.format)}
       |${R.render("TickLabels")("scale", a.config.tickLabels.scale)}
       |
       |${R.render("MajorTicks")("color", a.config.majorTicks.color)}
       |${R.render("MajorTicks")("width", a.config.majorTicks.width)}
       |${R.render("MajorTicks")("style", a.config.majorTicks.style)}
       |${R.render("MajorTicks")("transparency", a.config.majorTicks.transparency)}
       |${R.render("MajorTicks")("transparency", a.config.majorTicks.reflectivity)}
       |${R.render("MajorTicks")("hide", a.config.majorTicks.hide)}
       |${R.render("MajorTicks")("length", a.config.majorTicks.length)}
       |${R.render("MajorTicks")("number", a.config.majorTicks.number)}
       |Set('MajorTicks/manualTicks', [${a.config.majorTicks.manualTicks}])
       |
       |${R.render("MinorTicks")("color", a.config.minorTicks.color)}
       |${R.render("MinorTicks")("width", a.config.minorTicks.width)}
       |${R.render("MinorTicks")("style", a.config.minorTicks.style)}
       |${R.render("MinorTicks")("transparency", a.config.minorTicks.transparency)}
       |${R.render("MinorTicks")("reflectivity", a.config.minorTicks.reflectivity)}
       |${R.render("MinorTicks")("hide", a.config.minorTicks.hide)}
       |${R.render("MinorTicks")("length", a.config.minorTicks.length)}
       |${R.render("MinorTicks")("number", a.config.minorTicks.number)}
       |
       |${R.render("GridLines")("color", a.config.majorGridLines.color)}
       |${R.render("GridLines")("width", a.config.majorGridLines.width)}
       |${R.render("GridLines")("style", a.config.majorGridLines.style)}
       |${R.render("GridLines")("transparency", a.config.majorGridLines.transparency)}
       |${R.render("GridLines")("hide", a.config.majorGridLines.hide)}
       |
       |${R.render("MinorGridLines")("color", a.config.minorGridLines.color)}
       |${R.render("MinorGridLines")("width", a.config.minorGridLines.width)}
       |${R.render("MinorGridLines")("style", a.config.minorGridLines.style)}
       |${R.render("MinorGridLines")("transparency", a.config.minorGridLines.transparency)}
       |${R.render("MinorGridLines")("reflectivity", a.config.minorGridLines.reflectivity)}
       |${R.render("MinorGridLines")("hide", a.config.minorGridLines.hide)}
     """.stripMargin

  def render(s: Scene3D) =
    s"""
       |${R.render("xRotation", s.xRotation)}
       |${R.render("yRotation", s.yRotation)}
       |${R.render("zRotation", s.zRotation)}
       |
       |# Scene3D Formatting
       |${R.render("hide", s.config.main.hide)}
       |${R.render("leftMargin", s.config.main.leftMargin)}
       |${R.render("rightMargin", s.config.main.rightMargin)}
       |${R.render("topMargin", s.config.main.topMargin)}
       |${R.render("bottomMargin", s.config.main.bottomMargin)}
       |
       |${R.render("Lighting1")("enable", s.config.lighting1.enable)}
       |${R.render("Lighting1")("color", s.config.lighting1.color)}
       |${R.render("Lighting1")("intensity", s.config.lighting1.intensity)}
       |${R.render("Lighting1")("x", s.config.lighting1.xPosition)}
       |${R.render("Lighting1")("y", s.config.lighting1.yPosition)}
       |${R.render("Lighting1")("z", s.config.lighting1.zPosition)}
       |
       |${R.render("Lighting2")("enable", s.config.lighting2.enable)}
       |${R.render("Lighting2")("color", s.config.lighting2.color)}
       |${R.render("Lighting2")("intensity", s.config.lighting2.intensity)}
       |${R.render("Lighting2")("x", s.config.lighting2.xPosition)}
       |${R.render("Lighting2")("y", s.config.lighting2.yPosition)}
       |${R.render("Lighting2")("z", s.config.lighting2.zPosition)}
       |
       |${R.render("Lighting3")("enable", s.config.lighting3.enable)}
       |${R.render("Lighting3")("color", s.config.lighting3.color)}
       |${R.render("Lighting3")("intensity", s.config.lighting3.intensity)}
       |${R.render("Lighting3")("x", s.config.lighting3.xPosition)}
       |${R.render("Lighting3")("y", s.config.lighting3.yPosition)}
       |${R.render("Lighting3")("z", s.config.lighting3.zPosition)}
     """.stripMargin

  def render(k: Key) =
    s"""
       |# Key Formatting
       |${R.render("hide", k.config.main.hide)}
       |${R.render("horzPosn", k.config.main.horizontalPosition)}
       |${R.render("vertPosn", k.config.main.verticalPosition)}
       |${R.render("keyLength", k.config.main.keyLength)}
       |${R.renderOption("horzManual", k.config.main.horizontalManual, "")}
       |${R.renderOption("vertManual", k.config.main.verticalManual, "")}
       |${R.render("marginSize", k.config.main.marginSize)}
       |${R.render("columns", k.config.main.columns)}
       |${R.render("symbolswap", k.config.main.swapSymbol)}
       |
       |${renderTextConfig(k.config.text)}
       |${renderBackgroundConfig(k.config.background)}
       |
       |${renderBorderConfig(k.config.border)}
     """.stripMargin

  def render(xy: XY) = {

    def fill(fillType: String, fc: XYFillConfig) =
      s"""
         |${R.render("Fill" + fillType)("fillto", fc.fillTo)}
         |${R.render("Fill" + fillType)("color", fc.color)}
         |${R.render("Fill" + fillType)("style", fc.style)}
         |${R.render("Fill" + fillType)("hide", fc.hide)}
         |${R.render("Fill" + fillType)("hideerror", fc.hideError)}
         |${R.render("Fill" + fillType)("transparency", fc.transparency)}
       """.stripMargin

    //store data in datahandler and receive unique dataset references
    val xName = dataHandler.uniqueReference(xy.x, "x")
    val yName = dataHandler.uniqueReference(xy.y, "y")
    val scaleName = dataHandler.uniqueReference(xy.scaleMarkers, "s")
    val colorName = dataHandler.uniqueReference(xy.colorMarkers, "c")
    val labelsName = dataHandler.uniqueReference(xy.labels,"l")

    s"""
       |${R.render("xData", xName)}
       |${R.render("yData", yName)}
       |${R.render("xAxis", xy.xAxis)}
       |${R.render("yAxis", xy.yAxis)}
       |${R.render("scalePoints", scaleName)}
       |${R.render("labels", labelsName)}
       |${R.render("Color")("points", colorName)}
       |${R.render("key", xy.keyText)}
       |# XY Color Config
       | ${colorConfig(xy.config.colorConfig)}
       |# XY Formatting
       |${R.render("marker", xy.config.main.markerType)}
       |${R.render("markerSize", xy.config.main.markerSize)}
       |${R.render("color", xy.config.main.color)}
       |${R.render("thinfactor", xy.config.main.thinMarkers)}
       |${R.render("errorthin", xy.config.main.thinErrors)}
       |${R.render("hide", xy.config.main.hide)}
       |${R.render("errorStyle", xy.config.main.errorStyle)}
       |
       |${plotLineConfig(xy.config.plotLine)}
       |
       |${markerBorderConfig(xy.config.markerBorder)}
       |
       |${markerFillConfig(xy.config.markerFill)}
       |
       |${R.render("ErrorBarLine")("color", xy.config.errorBarLine.color)}
       |${R.render("ErrorBarLine")("width", xy.config.errorBarLine.width)}
       |${R.render("ErrorBarLine")("style", xy.config.errorBarLine.style)}
       |${R.render("ErrorBarLine")("transparency", xy.config.errorBarLine.transparency)}
       |${R.render("ErrorBarLine")("hide", xy.config.errorBarLine.hide)}
       |${R.render("ErrorBarLine")("endsize", xy.config.errorBarLine.endSize)}
       |${R.render("ErrorBarLine")("hideHorz", xy.config.errorBarLine.hideHorz)}
       |${R.render("ErrorBarLine")("hideVert", xy.config.errorBarLine.hideVert)}
       |
       |${fill("Below", xy.config.fillBelow)}
       |${fill("Above", xy.config.fillAbove)}
       |
       |${xyLabelConfig(xy.config.label)}
     """.stripMargin
  }

  def colorConfig(colorConfig: ColorConfig) = {
    s"""
    |# Color
    |${R.render("Color")("min", colorConfig.min)}
    |${R.render("Color")("max", colorConfig.max)}
    |${R.render("Color")("scaling", colorConfig.scaling)}
    """.stripMargin
  }

  def markerFillConfig(markerFillConfig: MarkerFillConfig) = {
    s"""
    |# Marker Fill
    |${R.render("MarkerFill")("color", markerFillConfig.color)}
    |${R.render("MarkerFill")("style", markerFillConfig.style)}
    |${R.render("MarkerFill")("transparency", markerFillConfig.transparency)}
    |${R.render("MarkerFill")("hide", markerFillConfig.hide)}
    |${R.render("MarkerFill")("colorMap", markerFillConfig.colorMap)}
    |${R.render("MarkerFill")("colorMapInvert", markerFillConfig.invertMap)}
    """.stripMargin
  }

  def markerBorderConfig(markerBorderConfig: MarkerBorderConfig) = {
    s"""
       |# Marker Border
       |${R.render("MarkerLine")("color", markerBorderConfig.color)}
       |${R.render("MarkerLine")("width", markerBorderConfig.width)}
       |${R.render("MarkerLine")("style", markerBorderConfig.style)}
       |${R.render("MarkerLine")("transparency", markerBorderConfig.transparency)}
       |${R.render("MarkerLine")("scaleLine", markerBorderConfig.scale)}
       |${R.render("MarkerLine")("hide", markerBorderConfig.hide)}
       |""".stripMargin
  }

  def plotLineConfig(plotLineConfig: PlotLineConfig) = {
    s"""
       |# Plot Line
       |${R.render("PlotLine")("steps", plotLineConfig.steps)}
       |${R.render("PlotLine")("bezierJoin", plotLineConfig.bezierJoin)}
       |${R.render("PlotLine")("color", plotLineConfig.color)}
       |${R.render("PlotLine")("width", plotLineConfig.width)}
       |${R.render("PlotLine")("style", plotLineConfig.style)}
       |${R.render("PlotLine")("transparency", plotLineConfig.transparency)}
       |${R.render("PlotLine")("hide", plotLineConfig.hide)}
       |""".stripMargin
  }

  def xyLabelConfig(xyLabelConfig: XYLabelConfig) = {
    s"""
      |# Label
      |${R.render("Label")("posnHorz", xyLabelConfig.horzPosition)}
      |${R.render("Label")("posnVert", xyLabelConfig.vertPosition)}
      |${R.render("Label")("angle", xyLabelConfig.angle)}
      |${R.render("Label")("font", xyLabelConfig.font)}
      |${R.render("Label")("size", xyLabelConfig.size)}
      |${R.render("Label")("color", xyLabelConfig.color)}
      |${R.render("Label")("italic", xyLabelConfig.italic)}
      |${R.render("Label")("bold", xyLabelConfig.bold)}
      |${R.render("Label")("underline", xyLabelConfig.underline)}
      |${R.render("Label")("hide", xyLabelConfig.hide)}
      """.stripMargin
  }

  def render(no: NonOrthPoint) = {

    //store data in datahandler and receive unique dataset references
    val d1Name = dataHandler.uniqueReference(no.data1, "d1")
    val d2Name = dataHandler.uniqueReference(no.data2, "d2")
    val scaleName = dataHandler.uniqueReference(no.scaleMarkers, "s")
    val colorName = dataHandler.uniqueReference(no.colorMarkers, "c")

    s"""
       |${R.render("data1", d1Name)}
       |${R.render("data2", d2Name)}
       |${R.render("scalePoints", scaleName)}
       |${R.render("Color")("points", colorName)}
       |${R.render("labels", no.keyText)}
       |# XY Color Config
       |${colorConfig(no.config.colorConfig)}
       |# XY Formatting
       |${R.render("marker", no.config.main.markerType)}
       |${R.render("markerSize", no.config.main.markerSize)}
       |${R.render("color", no.config.main.color)}
       |${R.render("hide", no.config.main.hide)}
       |
       |${xyLabelConfig(no.config.label)}
       |
       |${renderLineConfig(no.config.plotLine)("PlotLine")}
       |
       |${markerBorderConfig(no.config.markerBorder)}
       |
       |${markerFillConfig(no.config.markerFill)}
       |
       |${fill(1, no.config.areaFill1)}
       |
       |${fill(2, no.config.areaFill2)}
       |
       |${xyLabelConfig(no.config.label)}
     """.stripMargin
  }

  def render(nof: NonOrthFunction) = {

    s"""
       |${R.render("function", nof.function)}
       |${R.render("variable", nof.variable)}
       |${R.renderOption("min", nof.min, s"Set('min', u'Auto')")}
       |${R.renderOption("max", nof.max, s"Set('max', u'Auto')")}
       |
       |# Function Formatting
       |${R.render("steps", nof.config.main.steps)}
       |${R.render("hide", nof.config.main.hide)}
       |# Plolt Line Config
       |${renderLineConfig(nof.config.plotLine)(prefix = "PlotLine")}
       |
       |# Fill Config
       |${fill(1, nof.config.areaFill1)}
       |${fill(2, nof.config.areaFill2)}
     """.stripMargin
  }

  def fill(fillID: Int, fc: NonOrthFillConfig) =
    s"""
       |${R.render("Fill" + fillID)("filltype", fc.fillType)}
       |${R.render("Fill" + fillID)("color", fc.color)}
       |${R.render("Fill" + fillID)("style", fc.style)}
       |${R.render("Fill" + fillID)("hide", fc.hide)}
       |${R.render("Fill" + fillID)("transparency", fc.transparency)}
       """.stripMargin

  def render(bp: Boxplot) = {

    def renderAdvancedSettings: String = {
      if (bp.config.fill.style != FillStyle.Solid) {
        bp.config.fill.advanced = AdvancedFillStyleConfig()

        s"""
           |${R.render("Fill")("lineWidth", bp.config.fill.advanced.lineWidth)}
           |${R.render("Fill")("spacing", bp.config.fill.advanced.lineStyle)}
           |${R.render("Fill")("backColor", bp.config.fill.advanced.backColor)}
           |${R.render("Fill")("backTrans", bp.config.fill.advanced.backTrans)}
           |${R.render("Fill")("backHide", bp.config.fill.advanced.backHide)}
         """.stripMargin
      } else ""
    }
    val dataNames = bp.data.data.map(dataHandler.uniqueReference(_, ""))
    val labelNames = dataHandler.uniqueReference(Text(bp.data.labels), "labels")
    val positionName = dataHandler.uniqueReference(bp.data.positions, "pos")

    s"""
       |${R.render("values", dataNames)}
       |${R.render("posn", positionName)}
       |${R.render("labels", labelNames)}
       |${R.render("whiskermode", bp.whiskerMode)}
       |${R.render("fillfraction", bp.fillFraction)}
       |
       |# Boxplot Formatting
       |${R.render("hide", bp.config.main.hide)}
       |${R.render("fillfraction", bp.config.main.fillFraction)}
       |${R.render("outliersmarker", bp.config.main.outliers)}
       |${R.render("meanmarker", bp.config.main.mean)}
       |${R.render("markerSize", bp.config.main.markersSize)}
       |
       |${R.render("Fill")("color", bp.config.fill.color)}
       |${R.render("Fill")("style", bp.config.fill.style)}
       |${R.render("Fill")("hide", bp.config.fill.hide)}
       |$renderAdvancedSettings
       |
       |${renderBorderConfig(bp.config.border)}
       |
       |${R.render("Whisker")("color", bp.config.whiskerLine.color)}
       |${R.render("Whisker")("width", bp.config.whiskerLine.width)}
       |${R.render("Whisker")("style", bp.config.whiskerLine.style)}
       |${R.render("Whisker")("transparency", bp.config.whiskerLine.transparency)}
       |${R.render("Whisker")("hide", bp.config.whiskerLine.hide)}
       |
       |${renderBorderConfig(bp.config.markersBorder)}
       |
       |${renderFillConfig(bp.config.markersFill)("MarkersFill")}
     """.stripMargin
  }

  def render(bar: Barchart) = {

    val fills = bar.config.fill
      .map {
        case BarchartFillConfig(fillStyle, color, hide, transparency, lineWidth, lineStyle, spacing, backcolor, backTransparency, backhide) =>
          (s"'$fillStyle'",
           s"'$color'",
           s"${R.getBool(hide)}",
           s"$transparency",
           s"'$lineWidth'",
           s"'$lineStyle'",
           s"'$spacing'",
           s"'$backcolor'",
           s"$backTransparency",
           s"${R.getBool(backhide)}")
      }
      .mkString(", ")

    val lines = bar.config.line
      .map {
        case BarchartLineConfig(lineStyle, size, color, enable) => (s"'$lineStyle'", s"'$size'", s"'$color'", s"${R.getBool(enable)}")
      }
      .mkString(", ")

    val lengthNames = bar.lengths.map(dataHandler.uniqueReference(_, ""))
    val positionName = bar.positions match {
      case n: Numerical => dataHandler.uniqueReference(n, "")
      case d: DateTime  => dataHandler.uniqueReference(d, "dt")
    }
    val labelsName =
      if (bar.labels.nonEmpty) dataHandler.uniqueReference(Text(bar.labels), "")
      else ""

    s"""
       |${R.render("lengths", lengthNames)}
       |${R.render("posn", positionName)}
       |${R.render("direction", bar.direction)}
       |${R.render("mode", bar.mode)}
       |${R.render("keys", bar.keys)}
       |${R.render("labels", labelsName)}
       |${R.render("xAxis", bar.xAxis)}
       |${R.render("yAxis", bar.yAxis)}
       |
       |Set('BarFill/fills', [$fills])
       |Set('BarLine/lines', [$lines])
     """.stripMargin
  }

  def render(fun: graph.Function) =
    s"""
       |${R.render("function", fun.function)}
       |${R.render("key", fun.key)}
       |${R.renderOption("min", fun.min, s"Set('min', u'Auto')")}
       |${R.renderOption("max", fun.max, s"Set('max', u'Auto')")}
       |
       |# Function Formatting
       |${R.render("steps", fun.config.main.steps)}
       |${R.render("hide", fun.config.main.hide)}
       |
       |${renderLineConfig(fun.config.plotLine)}
       |
       |${renderFillConfig(fun.config.fillBelow)("FillBelow")}
       |
       |${renderFillConfig(fun.config.fillAbove)("FillAbove")}
     """.stripMargin

  def render(fit: Fit) = {
    val xName = dataHandler.uniqueReference(fit.xData, "x")
    val yName = dataHandler.uniqueReference(fit.yData, "y")
    s"""
       |${R.render("function", fit.function)}
       |${R.render("values", fit.parameters)}
       |${R.render("xData", xName)}
       |${R.render("yData", yName)}
       |${R.render("fitRange", fit.fitOnlyRange)}
       |${R.renderOption("min", fit.min, s"Set('min', u'Auto')")}
       |${R.renderOption("max", fit.max, s"Set('max', u'Auto')")}
       |
       |# Function Formatting
       |${R.render("steps", fit.config.main.steps)}
       |${R.render("hide", fit.config.main.hide)}
       |
       |${renderLineConfig(fit.config.plotLine)}
       |
       |${renderFillConfig(fit.config.fillBelow)("FillBelow")}
       |
       |${renderFillConfig(fit.config.fillAbove)("FillAbove")}
       |Action('fit')
     """.stripMargin
    //Action('fit')
  }

  def render(img: Image) = {

    val datasetName = dataHandler.uniqueReference(img.dataset, "")

    s"""
       |
       |${R.render("data", datasetName)}
       |${R.renderOption("min", img.min, s"Set('min', u'Auto')")}
       |${R.renderOption("max", img.max, s"Set('max', u'Auto')")}
       |${R.render("key", img.keyText)}
       |${R.render("xAxis", img.xAxis)}
       |${R.render("yAxis", img.yAxis)}
       |${R.render("colorScaling", img.scaling)}
       |
       |${R.render("colorMap", img.config.colorMap)}
       |${R.render("colorInvert", img.config.invertColormap)}
       |${R.render("transparency", img.config.transparency)}
       |${R.render("hide", img.config.hide)}
       |${R.render("smooth", img.config.smooth)}
       |
     """.stripMargin
  }

  def render(img: ImageFile) =
    s"""
       |${R.render("filename", img.filename)}
       |Set('xPos', [${img.xPositions.mkString(", ")}])
       |Set('yPos', [${img.yPositions.mkString(", ")}])
       |Set('width', [${img.widths.mkString(", ")}])
       |Set('height', [${img.heights.mkString(", ")}])
       |${R.render("rotate", img.rotate)}
       |${R.render("positioning", img.positioning)}
       |${R.render("xAxis", img.xAxis)}
       |${R.render("yAxis", img.yAxis)}
       |
       |${R.render("aspect", img.config.main.preserveAspect)}
       |${R.render("hide", img.config.main.hide)}
       |${R.render("clip", img.config.main.clip)}
       |
       |${renderBackgroundConfig(img.config.fill)("Fill")}
       |${renderBorderConfig(img.config.border)}
     """.stripMargin

  def render(l: Label) =
    s"""
       |${R.render("label", l.text)}
       |Set('xPos', [${l.xPositions.mkString(", ")}])
       |Set('yPos', [${l.yPositions.mkString(", ")}])
       |${R.render("positioning", l.positionMode)}
       |${R.render("xAxis", l.xAxis)}
       |${R.render("yAxis", l.yAxis)}
       |
       |# Label Formatting
       |${R.render("alignHorz", l.config.main.horizontal)}
       |${R.render("alignVert", l.config.main.vertical)}
       |${R.render("angle", l.config.main.angle)}
       |${R.render("margin", l.config.main.margin)}
       |${R.render("clip", l.config.main.clip)}
       |
       |${renderTextConfig(l.config.text)}
       |
       |${renderBackgroundConfig(l.config.background)}
       |
       |${renderBorderConfig(l.config.border)}
     """.stripMargin

  def render(cb: Colorbar) =
    s"""
       |${R.render("widgetName", cb.widget)}
       |${R.render("label", cb.label)}
       |${R.renderOption("min", cb.min, s"Set('min', u'Auto')")}
       |${R.renderOption("max", cb.max, s"Set('max', u'Auto')")}
       |${R.render("direction", cb.direction)}
       |${R.render("mode", cb.mode)}
       |${R.render("lowerPosition", cb.minPosition)}
       |${R.render("upperPosition", cb.maxPosition)}
       |${R.render("otherPosition", cb.axisPosition)}
       |${R.render("mode", cb.mode)}
       |
       |
       |# Colorbar Formatting
       |${R.render("hide", cb.config.main.hide)}
       |${R.render("autoRange", cb.config.main.autoRange)}
       |${R.render("autoMirror", cb.config.main.autoMirror)}
       |${R.render("reflect", cb.config.main.reflect)}
       |${R.render("outerticks", cb.config.main.outerTicks)}
       |${R.render("horzPosn", cb.config.main.horzPosition)}
       |${R.render("vertPosn", cb.config.main.vertPosition)}
       |${R.renderSizeOption("width", cb.config.main.width, "Set('width', u'Auto')")}
       |${R.renderSizeOption("height", cb.config.main.height, "Set('height', u'Auto')")}
       |${R.renderOption("horzManual", cb.config.main.horzManual, "")}
       |${R.renderOption("vertManual", cb.config.main.vertManual, "")}
       |
       |${renderLineConfig(cb.config.axisLine)}
       |
       |${renderLabelConfig(cb.config.axisLabel)}
       |
       |${renderTickLabelsConfig(cb.config.tickLabels)}
       |
       |${renderMajorTicksConfig(cb.config.majorTicks)}
       |
       |${renderMinorTicksConfig(cb.config.minorTicks)}
       |
       |${renderMajorGridLinesConfig(cb.config.majorGridLines)}
       |
       |${renderMinorGridLinesConfig(cb.config.minorGridLines)}
       |
       |${renderBorderConfig(cb.config.border)}

     """.stripMargin

  def render(cont: Contours) = {

    val manualLevels = cont.manualLevels match {
      case Some(ml) => s"Set('levelsManual', [${ml.asInstanceOf[Vector[Int]].mkString(", ")}])"
      case None     => ""
    }

    val contourLines = cont.config.lines.lineStyles
      .map {
        case (lineStyle, size, color, enable) => s"('$lineStyle', '$size', '$color', ${R.getBool(enable)})"
      }
      .mkString(", ")

    val contourFills = cont.config.fill.fillStyles
      .map {
        case (fillStyle, color, enable) => s"('$fillStyle', '$color', ${R.getBool(enable)})"
      }
      .mkString(", ")

    val contourSubLines = cont.config.sublines.lineStyle
      .map {
        case (lineStyle, size, color, enable) => s"('$lineStyle', '$size', '$color', ${R.getBool(enable)})"
      }
      .mkString(", ")

    s"""
       |${R.render("data", cont.dataset)}
       |${R.renderOption("min", cont.min, s"Set('min', u'Auto')")}
       |${R.renderOption("max", cont.max, s"Set('max', u'Auto')")}
       |${R.render("numLevels", cont.numberLevels)}
       |${R.render("scaling", cont.scaling)}
       |$manualLevels
       |${R.render("keyLevels", cont.levelsInKey)}
       |${R.render("xAxis", cont.xAxis)}
       |${R.render("yAxis", cont.yAxis)}
       |
       |# Contour Formatting
       |${R.render("ContourLabels")("font", cont.config.labels)}
       |${R.render("ContourLabels")("size", cont.config.labels.size)}
       |${R.render("ContourLabels")("color", cont.config.labels.color)}
       |${R.render("ContourLabels")("italic", cont.config.labels.italic)}
       |${R.render("ContourLabels")("bold", cont.config.labels.bold)}
       |${R.render("ContourLabels")("underline", cont.config.labels.underline)}
       |${R.render("ContourLabels")("hide", cont.config.labels.hide)}
       |${R.render("ContourLabels")("rotate", cont.config.labels.rotate)}
       |${R.render("ContourLabels")("format", cont.config.labels.format)}
       |${R.render("ContourLabels")("scale", cont.config.labels.scale)}
       |
       |Set('Lines/lines', [$contourLines])
       |Set('Fills/fills', [$contourFills])
       |Set('SubLines/lines', [$contourSubLines])
     """.stripMargin
  }

  def render(rect: Rectangle) =
    s"""
       |Set('xPos', [${rect.xPositions.mkString(", ")}])
       |Set('yPos', [${rect.yPositions.mkString(", ")}])
       |Set('width', [${rect.widths.mkString(", ")}])
       |Set('height', [${rect.heights.mkString(", ")}])
       |
       |${R.render("rotate", rect.rotate)}
       |${R.render("positioning", rect.positionMode)}
       |${R.render("xAxis", rect.xAxis)}
       |${R.render("yAxis", rect.yAxis)}
       |
       |${renderBackgroundConfig(rect.config.fill)("Fill")}
       |
       |${renderBorderConfig(rect.config.border)}
     """.stripMargin

  def render(el: Ellipse) =
    s"""
       |Set('xPos', [${el.xPositions.mkString(", ")}])
       |Set('yPos', [${el.yPositions.mkString(", ")}])
       |Set('width', [${el.widths.mkString(", ")}])
       |Set('height', [${el.heights.mkString(", ")}])
       |
       |${R.render("rotate", el.rotate)}
       |${R.render("positioning", el.positionMode)}
       |${R.render("xAxis", el.xAxis)}
       |${R.render("yAxis", el.yAxis)}
       |
       |${renderBackgroundConfig(el.config.fill)("Fill")}
       |
       |${renderBorderConfig(el.config.border)}
     """.stripMargin

  def render(line: LineLengthAngle) =
    s"""
       |${R.render("mode", "length-angle")}
       |Set('xPos', [${line.xPositions.mkString(", ")}])
       |Set('yPos', [${line.yPositions.mkString(", ")}])
       |Set('length', [${line.lengths.mkString(", ")}])
       |Set('angle', [${line.angles.mkString(", ")}])
       |${R.render("positioning", line.positionMode)}
       |${R.render("xAxis", line.xAxis)}
       |${R.render("yAxis", line.yAxis)}
       |
       |# Line Formatting
       |${R.render("arrowleft", line.config.main.arrowLeft)}
       |${R.render("arrowright", line.config.main.arrowRight)}
       |${R.render("arrowSize", line.config.main.arrowSize)}
       |${R.render("clip", line.config.main.clip)}
       |${R.render("hide", line.config.main.hide)}
       |
       |${renderBorderConfig(line.config.line)("Line")}
       |${renderSimpleFillConfig(line.config.arrowFill)("Fill")}
     """.stripMargin

  def render(line: LinePoint2Point) =
    s"""
       |${R.render("mode", "point-to-point")}
       |Set('xPos', [${line.xPositions.mkString(", ")}])
       |Set('yPos', [${line.yPositions.mkString(", ")}])
       |Set('xPos2', [${line.xPositions2.mkString(", ")}])
       |Set('yPos2', [${line.yPositions2.mkString(", ")}])
       |${R.render("positioning", line.positionMode)}
       |${R.render("xAxis", line.xAxis)}
       |${R.render("yAxis", line.yAxis)}
       |
       |# Line Formatting
       |${R.render("arrowleft", line.config.main.arrowLeft)}
       |${R.render("arrowright", line.config.main.arrowRight)}
       |${R.render("arrowSize", line.config.main.arrowSize)}
       |${R.render("clip", line.config.main.clip)}
       |${R.render("hide", line.config.main.hide)}
       |
       |${renderBorderConfig(line.config.line)("Line")}
       |${renderSimpleFillConfig(line.config.arrowFill)("Fill")}
     """.stripMargin

  def render(poly: Polygon) =
    s"""
       |Set('xPos', [${poly.xPositions.mkString(", ")}])
       |Set('yPos', [${poly.yPositions.mkString(", ")}])
       |${R.render("positioning", poly.positionMode)}
       |${R.render("xAxis", poly.xAxis)}
       |${R.render("yAxis", poly.yAxis)}
       |${R.render("hide", poly.config.hide)}
       |${R.render("hide", poly.config.hide)}
       |${R.render("hide", poly.config.hide)}
       |${renderBorderConfig(poly.config.line)("Line")}
       |${renderBackgroundConfig(poly.config.fill)("Fill")}
     """.stripMargin

  def render(vec: Vectorfield) = {
    val dxName = dataHandler.uniqueReference(vec.dxOrR, "")
    val dyName = dataHandler.uniqueReference(vec.dyOrTheta, "")
    s"""
       |# Vectorfield Formatting
       |${R.render("data1", dxName)}
       |${R.render("data2", dyName)}
       |${R.render("mode", vec.mode)}
       |${R.render("rotate", vec.rotate)}
       |${R.render("reflectx", vec.reflectX)}
       |${R.render("reflecty", vec.reflectY)}
       |${R.render("xAxis", vec.xAxis)}
       |${R.render("yAxis", vec.yAxis)}
       |${R.render("baselength", vec.config.main.baseLength)}
       |${R.render("arrowsize", vec.config.main.arrowSize)}
       |${R.render("scalearrow", vec.config.main.scaleArrow)}
       |${R.render("arrowfront", vec.config.main.arrowFront)}
       |${R.render("arrowback", vec.config.main.arrowBack)}
       |${R.render("hide", vec.config.main.hide)}
       |
       |${renderLineConfig(vec.config.line)}
       |
       |${renderFillConfig(vec.config.fill)}
     """.stripMargin
  }

  def render(cov: Covariance) =
    s"""
       |${R.render("xData", cov.xData)}
       |${R.render("yData", cov.yData)}
       |${R.render("covxx", cov.covXX)}
       |${R.render("covyx", cov.covYX)}
       |${R.render("covxy", cov.covXY)}
       |${R.render("covyy", cov.covYY)}
       |${R.render("key", cov.keyText)}
       |${R.render("xAxis", cov.xAxis)}
       |${R.render("yAxis", cov.yAxis)}
     """.stripMargin

  /* Render Configs */

  def renderBorderConfig(bc: BorderConfig)(implicit prefix: String = "Border"): String =
    s"""
       |${R.render(prefix)("color", bc.color)}
       |${R.render(prefix)("width", bc.width)}
       |${R.render(prefix)("style", bc.style)}
       |${R.render(prefix)("transparency", bc.transparency)}
       |${R.render(prefix)("hide", bc.hide)}
     """.stripMargin

  def renderBackgroundConfig(bc: BackgroundConfig)(implicit prefix: String = "Background"): String =
    s"""
       |${R.render(prefix)("color", bc.color)}
       |${R.render(prefix)("style", bc.fillStyle)}
       |${R.render(prefix)("hide", bc.hide)}
       |${R.render(prefix)("transparency", bc.transparency)}
       |${R.render(prefix)("linewidth", bc.lineWidth)}
       |${R.render(prefix)("linestyle", bc.lineStyle)}
       |${R.render(prefix)("patternspacing", bc.spacing)}
       |${R.render(prefix)("backcolor", bc.backColor)}
       |${R.render(prefix)("backtransparency", bc.backTransparency)}
       |${R.render(prefix)("backhide", bc.backHide)}
     """.stripMargin

  def renderSimpleFillConfig(bc: SimpleFill)(implicit prefix: String = "Background"): String =
    s"""
       |${R.render(prefix)("color", bc.color)}
       |${R.render(prefix)("style", bc.fillStyle)}
       |${R.render(prefix)("hide", bc.hide)}
       |${R.render(prefix)("transparency", bc.transparency)}
     """.stripMargin

  def renderLabelConfig(lc: de.dreambeam.veusz.format.AxisLabelConfig)(implicit prefix: String = "Label"): String =
    s"""
       |${R.render(prefix)("font", lc.font)}
       |${R.render(prefix)("size", lc.size)}
       |${R.render(prefix)("color", lc.color)}
       |${R.render(prefix)("italic", lc.italic)}
       |${R.render(prefix)("bold", lc.bold)}
       |${R.render(prefix)("underline", lc.underline)}
       |${R.render(prefix)("hide", lc.hide)}
       |${R.render(prefix)("atEdge", lc.atEdge)}
       |${R.render(prefix)("rotate", lc.rotate)}
       |${R.render(prefix)("offset", lc.offset)}
       |${R.render(prefix)("position", lc.position)}
     """.stripMargin

  def renderTickLabelsConfig(tlc: TickLabelsConfig)(implicit prefix: String = "TickLabels"): String =
    s"""
       |${R.render(prefix)("font", tlc.font)}
       |${R.render(prefix)("size", tlc.size)}
       |${R.render(prefix)("color", tlc.color)}
       |${R.render(prefix)("italic", tlc.italic)}
       |${R.render(prefix)("bold", tlc.bold)}
       |${R.render(prefix)("underline", tlc.underline)}
       |${R.render(prefix)("hide", tlc.hide)}
       |${R.render(prefix)("rotate", tlc.rotate)}
       |${R.render(prefix)("format", tlc.format)}
       |${R.render(prefix)("scale", tlc.scale)}
       |${R.render(prefix)("offset", tlc.offset)}
     """.stripMargin

  def renderTernaryTickLabelsConfig(tlc: TernaryTickLabelsConfig)(implicit prefix: String = "TickLabels"): String =
    s"""
       |${R.render(prefix)("font", tlc.font)}
       |${R.render(prefix)("size", tlc.size)}
       |${R.render(prefix)("color", tlc.color)}
       |${R.render(prefix)("italic", tlc.italic)}
       |${R.render(prefix)("bold", tlc.bold)}
       |${R.render(prefix)("underline", tlc.underline)}
       |${R.render(prefix)("hide", tlc.hide)}
       |${R.render(prefix)("format", tlc.format)}
       |${R.render(prefix)("scale", tlc.scale)}
       |${R.render(prefix)("offset", tlc.offset)}
     """.stripMargin

  def renderMajorTicksConfig(tc: MajorTicksConfig)(implicit prefix: String = "MajorTicks"): String =
    s"""
       |${R.render(prefix)("color", tc.color)}
       |${R.render(prefix)("width", tc.width)}
       |${R.render(prefix)("style", tc.style)}
       |${R.render(prefix)("transparency", tc.transparency)}
       |${R.render(prefix)("hide", tc.hide)}
       |${R.render(prefix)("length", tc.length)}
       |${R.render(prefix)("number", tc.number)}
     """.stripMargin

  def renderMinorTicksConfig(tc: MinorTicksConfig)(implicit prefix: String = "MinorTicks"): String =
    s"""
       |${R.render(prefix)("color", tc.color)}
       |${R.render(prefix)("width", tc.width)}
       |${R.render(prefix)("style", tc.style)}
       |${R.render(prefix)("transparency", tc.transparency)}
       |${R.render(prefix)("hide", tc.hide)}
       |${R.render(prefix)("length", tc.length)}
       |${R.render(prefix)("number", tc.number)}
     """.stripMargin

  def renderMajorGridLinesConfig(glc: MajorGridLinesConfig)(implicit prefix: String = "GridLines"): String =
    s"""
       |${R.render(prefix)("color", glc.color)}
       |${R.render(prefix)("width", glc.width)}
       |${R.render(prefix)("style", glc.style)}
       |${R.render(prefix)("transparency", glc.transparency)}
       |${R.render(prefix)("hide", glc.hide)}
       |${R.render(prefix)("onTop", glc.onTop)}
     """.stripMargin

  def renderMinorGridLinesConfig(glc: MinorGridLinesConfig)(implicit prefix: String = "MinorGridLines"): String =
    s"""
       |${R.render(prefix)("color", glc.color)}
       |${R.render(prefix)("width", glc.width)}
       |${R.render(prefix)("style", glc.style)}
       |${R.render(prefix)("transparency", glc.transparency)}
       |${R.render(prefix)("hide", glc.hide)}
     """.stripMargin

  def renderTextConfig(tc: TextConfig)(implicit prefix: String = "Text"): String =
    s"""
       |${R.render(prefix)("font", tc.font)}
       |${R.render(prefix)("size", tc.size)}
       |${R.render(prefix)("color", tc.color)}
       |${R.render(prefix)("italic", tc.italic)}
       |${R.render(prefix)("bold", tc.bold)}
       |${R.render(prefix)("underline", tc.underline)}
       |${R.render(prefix)("hide", tc.hide)}
     """.stripMargin

  def renderLineConfig(lc: LineStyleConfig)(implicit prefix: String = "Line"): String =
    s"""
       |${R.render(prefix)("color", lc.color)}
       |${R.render(prefix)("width", lc.width)}
       |${R.render(prefix)("style", lc.style)}
       |${R.render(prefix)("transparency", lc.transparency)}
       |${R.render(prefix)("hide", lc.hide)}
     """.stripMargin

  def renderFillConfig(fc: FillConfig)(implicit prefix: String = "Fill"): String =
    s"""
       |${R.render(prefix)("color", fc.color)}
       |${R.render(prefix)("style", fc.style)}
       |${R.render(prefix)("hide", fc.hide)}
       |${R.render(prefix)("transparency", fc.transparency)}
     """.stripMargin

  def render(p3: Point3D) = {

    //store data in datahandler and receive unique dataset references
    val xName = dataHandler.uniqueReference(p3.x, "x")
    val yName = dataHandler.uniqueReference(p3.y, "y")
    val zName = dataHandler.uniqueReference(p3.z, "z")
    val scaleName = dataHandler.uniqueReference(p3.scaleMarkers, "s")
    val colorName = dataHandler.uniqueReference(p3.colorMarkers, "c")

    s"""
       |${R.render("xData", xName)}
       |${R.render("yData", yName)}
       |${R.render("zData", zName)}
       |${R.render("xAxis", p3.xAxis)}
       |${R.render("yAxis", p3.yAxis)}
       |${R.render("zAxis", p3.zAxis)}
       |${R.render("scalePoints", scaleName)}
       |${R.render("Color")("points", colorName)}
       |# XY Color Config
       | ${colorConfig(p3.config.colorConfig)}
       | ${plotLineConfig(p3.config.plotLine)}
       |# Marker Fill
       |${R.render("MarkerFill")("color", p3.config.markerFill.color)}
       |${R.render("MarkerFill")("transparency", p3.config.markerFill.transparency)}
       |${R.render("MarkerFill")("colorMapInvert", p3.config.markerFill.invertMap)}
       |${R.render("MarkerFill")("colorMap", p3.config.markerFill.colorMap)}
       |${R.render("MarkerFill")("hide", p3.config.markerFill.hide)}
       |# Marker Line
       |${R.render("MarkerLine")("color", p3.config.markerBorder.color)}
       |${R.render("MarkerLine")("transparency", p3.config.markerBorder.transparency)}
       |${R.render("MarkerLine")("reflectivity", p3.config.markerBorder.reflectivity)}
       |${R.render("MarkerLine")("width", p3.config.markerBorder.width)}
       |${R.render("MarkerLine")("style", p3.config.markerBorder.style)}
       |${R.render("MarkerLine")("scale", p3.config.markerBorder.scale)}
       |${R.render("MarkerLine")("hide", p3.config.markerBorder.hide)}
       |# Error bar
       |${R.render("Error")("color", p3.config.errorBar.color)}
       |${R.render("Error")("transparency", p3.config.errorBar.transparency)}
       |${R.render("Error")("reflectivity", p3.config.errorBar.reflectivity)}
       |${R.render("Error")("width", p3.config.errorBar.width)}
       |${R.render("Error")("style", p3.config.errorBar.style)}
       |${R.render("Error")("hide", p3.config.errorBar.hide)}
     """.stripMargin
  }

  def render(s3: Surface3D) = {

    //store data in datahandler and receive unique dataset references
    val datasetName = dataHandler.uniqueReference(s3.dataset, "")
    val colorName = dataHandler.uniqueReference(s3.colorMarkers, "c")

    s"""
       |${R.render("mode", s3.mode)}
       |${R.render("data", datasetName)}
       |${R.render("xAxis", s3.xAxis)}
       |${R.render("yAxis", s3.yAxis)}
       |${R.render("zAxis", s3.zAxis)}
       |${R.render("hide", s3.config.main.hide)}
       |${R.render("highres", s3.config.main.highres)}
       |# XY Color Config
       |${R.render("DataColor")("points", colorName)}
       |${R.render("DataColor")("min", s3.config.colorConfig.min)}
       |${R.render("DataColor")("max", s3.config.colorConfig.max)}
       |${R.render("DataColor")("scaling", s3.config.colorConfig.scaling)}
       |${gridLineConfig(s3.config.gridLine)}
       |${surfaceConfig(s3.config.surface)}

     """.stripMargin
  }

  def plotLineConfig(c: PlotLine3DConfig, prefix: String = "PlotLine") =
    s"""
       |# Plotline
       |${R.render(prefix)("color", c.color)}
       |${R.render(prefix)("width", c.width)}
       |${R.render(prefix)("transparency", c.transparency)}
       |${R.render(prefix)("reflectivity", c.reflectivity)}
       |${R.render(prefix)("hide", c.hide)}
       |${R.render(prefix)("colorMap", c.colorMap)}
       |${R.render(prefix)("colorMapInvert", c.invertMap)}
       |""".stripMargin

  def gridLineConfig(c: Surface3DGridLineConfig, prefix: String = "Line") =
    s"""
     |# SurfaceGrid
     |${R.render(prefix)("color", c.color)}
     |${R.render(prefix)("width", c.width)}
     |${R.render(prefix)("transparency", c.transparency)}
     |${R.render(prefix)("reflectivity", c.reflectivity)}
     |${R.render(prefix)("hide", c.hide)}
     |${R.render(prefix)("hidehorz", c.hideHorz)}
     |${R.render(prefix)("hidevert", c.hideVert)}
    """.stripMargin

  def surfaceConfig(c: Surface3DSurfaceConfig) =
    s"""
       |# SurfaceGrid
       |# Surface Config
       |${R.render("Surface")("color", c.color)}
       |${R.render("Surface")("transparency", c.transparency)}
       |${R.render("Surface")("reflectivity", c.reflectivity)}
       |${R.render("Surface")("colorMapInvert", c.invertMap)}
       |${R.render("Surface")("colorMap", c.colorMap)}
       |${R.render("Surface")("hide", c.hide)}     
    """.stripMargin

  def render(v3: Volume3D) = {

    //store data in datahandler and receive unique dataset references
    val xName = dataHandler.uniqueReference(v3.x, "x")
    val yName = dataHandler.uniqueReference(v3.y, "y")
    val zName = dataHandler.uniqueReference(v3.z, "z")
    val colorName = dataHandler.uniqueReference(v3.colorMarkers, "c")
    val transparencyName = dataHandler.uniqueReference(v3.transparency, "t")

    s"""
       |${R.render("xData", xName)}
       |${R.render("yData", yName)}
       |${R.render("zData", zName)}
       |${R.render("xAxis", v3.xAxis)}
       |${R.render("yAxis", v3.yAxis)}
       |${R.render("zAxis", v3.zAxis)}
       |${R.render("transData", transparencyName)}
       |${R.render("colorMap", v3.config.main.colorMap)}
       |${R.render("colorInvert", v3.config.main.invertMap)}
       |${R.render("transparency", v3.config.main.transparency)}
       |${R.render("reflectivity", v3.config.main.reflectivity)}
       |${R.render("fillfactor", v3.config.main.fillFactor)}
       |${R.render("DataColor")("points", colorName)}
       |${R.render("DataColor")("min", v3.config.colorConfig.min)}
       |${R.render("DataColor")("max", v3.config.colorConfig.max)}
       |${R.render("DataColor")("scaling", v3.config.colorConfig.scaling)}
       |# Plotline
       |${R.render("Line")("color", v3.config.line.color)}
       |${R.render("Line")("width", v3.config.line.width)}
       |${R.render("Line")("style", v3.config.line.style)}
       |${R.render("Line")("transparency", v3.config.line.transparency)}
       |${R.render("Line")("hide", v3.config.line.hide)}

     """.stripMargin
  }

  def render(f: Function3D) = {

    s"""
       |${R.render("fnx", f.x_func)}
       |${R.render("fny", f.y_func)}
       |${R.render("fnz", f.z_func)}
       |${R.render("fncolor", f.color_func)}
       |${R.render("mode", f.mode)}
       |${R.render("color", f.config.main.color)}
       |${R.render("linesteps", f.config.main.lineSteps)}
       |${R.render("surfacesteps", f.config.main.surfaceSteps)}
       |${R.render("hide", f.config.main.hide)}
       |${plotLineConfig(f.config.plotLine, "Line")}
       |${gridLineConfig(f.config.gridLine, "GridLine")}
       |${surfaceConfig(f.config.surface)}
     """.stripMargin
  }
}
