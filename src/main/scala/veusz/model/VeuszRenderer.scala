package veusz.model
import veusz.model.GraphItems.{BoxPlot, BoxPlotData, Label, LabelConfig, SimpleBorder, SimpleFill}

/**
  * Created by Kaufmann on 10.03.2017.
  */
object VeuszRenderer {

  val NewLine = "\n"

  case class Data(numericData: Map[scala.Vector[Double], String], textData: Map[scala.Vector[String], String], numeric3DData: Map[Map[(Double, Double), Double], String])

  /**
    * Collects all data elements and gives each element a unique ID
    * @param document
    * @return
    */
  private def collectData(document: Document) = {

    var gridindex = 1
    var graphIndex = 1

    def collectData(namedPageItems: Vector[(String, (PageItem, Int))]): Vector[(String, VeuszData)] = {

      (for ((pageName, (pi, pi_index)) <- namedPageItems)
        yield
          pi match {
            case g: Grid => Some(collectData((g.pageItems.zipWithIndex.map { case (pi_, pii_) => (s"${pageName}_${g.name}_${pi_index + 1}", (pi_, pii_)) })))
            case g: Graph =>
              Some(
                (for (gi <- g.graphItems.zipWithIndex)
                  yield
                    gi match {
                      case (xy: GraphItems.XY, i: Int) => Some((s"{${pageName.noBlanks}_${g.name.noBlanks}_${pi_index + 1}}_${xy.name.noBlanks}{_$i}", xy.xYData))
                      case (bp: BoxPlot, i: Int)       => Some((s"{${pageName.noBlanks}_${g.name.noBlanks}_${pi_index + 1}}_${bp.name.noBlanks}{_$i}", bp.data))
                      case (xyzImage: GraphItems.XyzImage, i: Int) =>
                        Some((s"{${pageName.noBlanks}_${g.name.noBlanks}_${pi_index + 1}}_${xyzImage.name.noBlanks}{_$i}", xyzImage.data))
                      case _ => None
                    }).flatten)
            case _ => None
          }).flatten.flatten
    }

    val pageItems = document.pages.zipWithIndex.flatMap { case (p, i) => p.pageItems.zipWithIndex.map { case (pi, pi_index) => (p.name.noBlanks + s"_${i + 1}", (pi, pi_index)) } }
    val allXYData = collectData(pageItems)
    val entries2d = allXYData.collect {
      case (n, xy: XYData) =>
        Vector(
          (xy.x.data, n + "_x_" + xy.x.name.noBlanks),
          (xy.y.data, n + "_y_" + xy.y.name.noBlanks),
          (xy.scaleMarkers.data, n + "_scale_" + xy.scaleMarkers.name.noBlanks),
          (xy.colorMarkes.data, n + "_color_" + xy.colorMarkes.name.noBlanks)
        )
      case (n, bp: BoxPlotData) =>
        ()
        bp.values.zipWithIndex.map { case (d, i) => (d.data, n + s"_value${i}_" + d.name) } ++
          (bp.positions match {
            case None    => Vector()
            case Some(p) => Vector((p.data, n + "_position_" + p.name))
          })
    }.flatten
    val groupedfun = entries2d.groupBy(_._1)

    val numeric2DData = entries2d.toMap

    val textData = allXYData.collect {
      case (n, xy: XYData)      => (xy.labels, n + "_labels")
      case (n, bp: BoxPlotData) => (bp.labels, n + "_labels")
    }.toMap
    val numeric3DData = allXYData.collect { case (n, xyz: XYZData) => (xyz.dataset, n + "_xyz_" + xyz.name.noBlanks) }.toMap

    Data(numeric2DData, textData, numeric3DData)
  }

  /**
    * Writes Data in Vuesz Format
    * @param data
    * @return
    */
  private def dataToImportText(data: Data) = {
    val d = data.numericData
      .map(d => {
        s"""
        |ImportString(u'${d._2}(numeric)','''
        |${d._1.mkString(NewLine)}
        |''')
      """.stripMargin
      })
      .mkString(NewLine)

    val d2d = data.numeric3DData
      .map {
        case (macroMap, name) =>
          ()

          val xs = macroMap.keys.map(_._1).toVector.sorted
          val ys = macroMap.keys.map(_._2).toVector.sorted

          val xRange = s"(${xs.head},${xs.last})"
          val yRange = s"(${ys.head},${ys.last})"

          val stringMap = ys.reverse.foldLeft("")(
            (text, y) =>
              text
                + xs.foldLeft("")((line, x) =>
                  line
                    + macroMap((x, y))
                    + " ")
                + NewLine)
          s"""
         |ImportString2D(u'$name', '''
         |$stringMap
         |''', xrange=${xRange}, yrange=${yRange})
      """.stripMargin
      }
      .mkString(NewLine)

    val text = data.textData
      .map {
        case (labels, name) =>
          s"""
         |SetDataText(u'$name', [
         |${labels.map(l => s"u'$l'").mkString("," + NewLine)}
         |])
         |
      """.stripMargin
      }
      .mkString(NewLine)

    d + d2d + text
  }

  def apply(document: Document) = {

    implicit val data = collectData(document)

    val dataText = dataToImportText(data)
    val documentText = document.pages.zipWithIndex.map { case (p, i) => page(p, i + 1) }.mkString("", NewLine, "")

    s"""
       |
       |$dataText
       |
       |Set('width',  u'${document.config.width}')
       |Set('height',  u'${document.config.height}')
       |Set('englishlocale', ${getBool(document.config.englishLocal)})
       |
       |$documentText
       |
     """.stripMargin
  }

  def page(page: Page, index: Int)(implicit data: Data): String = {
    val pageText = page.pageItems.zipWithIndex.map { case (p, i) => pageItem(p, i + 1) }.mkString("", NewLine, "")

    s"""
       |
       |Add('page', name='${page.name}_[$index]', autoadd=False)
       |To('${page.name}_[$index]')
       |Set('width', u'${page.config.width}')
       |Set('height', u'${page.config.height}')
       |$pageText
       |To('..')    # End of Page: ${page.name}_[$index]
       |
     """.stripMargin

  }

  def grid(grid: Grid, index: Int)(implicit data: Data): String = {
    val pageText = grid.pageItems.zipWithIndex.map { case (p, i) => pageItem(p, i + 1) }.mkString("", NewLine, "")
    val gridcfg = gridConfig(grid.config)

    s"""
       |
       |Add('grid', name='${grid.name}_[$index]', autoadd=False)
       |To('${grid.name}_[$index]')
       |Set('rows', ${grid.rows})
       |Set('columns', ${grid.columns})
       |Set('scaleRows', [${grid.scaleRows}])
       |Set('scaleCols', [${grid.scaleCols}])
       |${gridcfg}
       |
       |$pageText
       |To('..')    # End of Grid: ${grid.name}_[$index]
       |
     """.stripMargin

  }

  def gridConfig(config: GridConfig) =
    s"""
       |
       |Set('leftMargin', u'${config.leftMargin}')
       |Set('rightMargin', u'${config.rightMargin}')
       |Set('topMargin', u'${config.topMargin}')
       |Set('bottomMargin', u'${config.bottomMargin}')
       |Set('internalMargin', u'${config.internalMargin}')
       |
     """.stripMargin

  def pageItem(pageItem: PageItem, index: Int)(implicit data: Data): String = {
    pageItem match {
      case g: Grid  => grid(g, index)
      case t: Text  => text(t, index)
      case g: Graph => graph(g, index)
    }
  }

  def text(text: Text, index: Int)(implicit data: Data) = ???

  def graph(graph: Graph, index: Int)(implicit data: Data): String = {

    val graphItems = graph.graphItems.zipWithIndex.map { case (g, i) => graphItem(g, i + 1) }.mkString("", NewLine, "")

    val graphAxis = graph.axis.map(axis(_)).mkString("", NewLine, "")

    s"""
       |
       |Add('graph', name='${graph.name}_[$index]', autoadd=False)
       |To('${graph.name}_[$index]')
       |Set('leftMargin', u'${graph.config.border.leftMargin}')
       |Set('rightMargin', u'${graph.config.border.rightMargin}')
       |Set('topMargin', u'${graph.config.border.topMargin}')
       |Set('bottomMargin', u'${graph.config.border.bottomMargin}')
       |Set('aspect', ${getOption(graph.config.border.aspectRatio)})
       |Set('Border/width', u'${graph.config.border.width}')
       |
       |$graphAxis
       |
       |$graphItems
       |
       |To('..')  # End of Graph ${graph.name}_[$index]
       |
     """.stripMargin
    //TODO check aspect ratio

  }

  def axis(axis: Axis) =
    s"""
       |Add('axis', name='${axis.name}', autoadd=False)
       |To('${axis.name}')
       |Set('direction', 'vertical')
       |Set('label', u'${axis.label}')
       |Set('min', ${getOption(axis.min)})
       |Set('max', ${getOption(axis.max)})
       |Set('mode', u'${axis.mode}')
       |Set('datascale', ${axis.scale})
       |Set('lowerPosition', ${axis.minPos})
       |Set('upperPosition', ${axis.maxPos})
       |Set('direction', u'${axis.direction}')
       |Set('otherPosition', ${axis.axisPosition})
       |${axisConfig(axis.config)}
       |To('..')  # End of Axis ${axis.name}
     """.stripMargin

  def axisConfig(config: AxisConfig) = {
    s"""
       |Set('autoRange', u'${config.autoRange}')
       |Set('autoMirror', ${getBool(config.autoMirror)})
       |Set('reflect', ${getBool(config.reflect)})
       |Set('outerticks', ${getBool(config.outerticks)})
       |${lineStyle(config.axisLine)}
       |${axisLabelStyle(config.labelStyle)}
       |${tickLabelStyle(config.tickLabelStyle)}
       |${majorTickStyle(config.majorTickStyle)}
       |${minorTickStyle(config.minorTickStyle)}
       |${majorGridLines(config.majorGridLines)}
       |${minorGridLines(config.minorGridLines)}
     """.stripMargin

  }

  def graphItem(graphItem: GraphItem, index: Int)(implicit data: Data): String = {
    graphItem match {
      case x: GraphItems.XyzImage => xyzImage(x, index)
      case x: GraphItems.XY       => xy(x, index)
      case f: GraphItems.Function => function(f, index)
      case l: GraphItems.Line     => line(l, index)
      case p: GraphItems.Polygon  => polygon(p, index)
      case b: GraphItems.BoxPlot  => boxplot(b, index)
      case l: GraphItems.Label    => label(l, index)
    }
  }

  def label(l: Label, index: Int)(implicit data: Data): String = {

    val name = s"${l.label.replace(" ", "_").take(10)}_${index}"
    s"""
       |
       |Add('label', name = '$name', autoadd = False)
       |To('$name')
       |Set('label', u'${l.label}')
       |Set('xPos', [${l.xPositions.mkString(", ")}])
       |Set('yPos', [${l.yPositions.mkString(", ")}])
       |Set('positioning', u'${l.positionMode}')
       |Set('xAxis', u'${l.xAxis}')
       |Set('yAxis', u'${l.yAxis}')
       |
       |${labelConfig(l.config)}
       |To('..')
       |
     """.stripMargin
  }

  def labelConfig(lc:LabelConfig):String = {
    s"""
       |Set('alignHorz', u'${lc.alignment.horizontal}')
       |Set('alignVert', u'${lc.alignment.vertical}')
       |Set('angle', ${lc.alignment.angle})
       |Set('margin', u'${lc.alignment.margin.getValue}')
       |Set('clip', ${getBool(lc.alignment.clip)})
       |       |
       |Set('Text/font', u'${lc.textConfig.font}')
       |Set('Text/size', u'${lc.textConfig.size.getValue}')
       |Set('Text/color', u'${lc.textConfig.color}')
       |Set('Text/italic', ${getBool(lc.textConfig.italic)})
       |Set('Text/bold', ${getBool(lc.textConfig.bold)})
       |Set('Text/underline', ${getBool(lc.textConfig.underline)})
       |Set('Text/hide', ${getBool(lc.textConfig.hide)})
       |
       |Set('Background/color', u'${lc.background.color}')
       |Set('Background/style', u'${lc.background.style}')
       |Set('Background/transparency', ${lc.background.transparency})
       |Set('Background/hide', ${getBool(lc.background.hide)})
       |
       |Set('Border/color', u'${lc.border.color}')
       |Set('Border/style', u'${lc.border.style}')
       |Set('Border/transparency', ${lc.border.transparency})
       |Set('Border/hide', ${getBool(lc.border.hide)})
     """.stripMargin
  }

  def boxplot(bp: BoxPlot, index: Int)(implicit data: Data): String = {

    val datanames = bp.data.values.map(d => data.numericData(d.data)).map(name => s"u'$name'").mkString(",")
    val labelnames = data.textData(bp.data.labels)

    s"""
       |
       |Add('boxplot', name='${bp.name}_${index}', autoadd=False)
       |To('${bp.name}_${index}')
       |Set('values', ($datanames))
       |Set('labels', u'${labelnames}')
       |Set('whiskermode', u'${bp.whiskerMode}')
       |Set('fillfraction', ${bp.fillFraction})
       |Set('Fill/color', u'white')
       |${simpleMarkerBorder(bp.config.markerBorder)}
       |To('..')
       |
     """.stripMargin
  }

  def xyzImage(xy2d: GraphItems.XyzImage, index: Int)(implicit data: Data) = {

    val dataName = data.numeric3DData(xy2d.data.dataset)
    s"""
       |Add('image', name='${xy2d.data.name}_[$index]', autoadd=False)
       |To('${xy2d.data.name}_[$index]')
       |Set('data', u'${dataName}')
       |
       |Set('min', ${getOption(xy2d.min)})
       |Set('max', ${getOption(xy2d.max)})
       |Set('colorScaling', u'${xy2d.scaling}')
       |Set('colorMap', u'${xy2d.config.colorMap}')
       |Set('colorInvert', ${getBool(xy2d.config.invertColormap)})
       |Set('transparency', ${xy2d.config.transparency})
       |Set('hide', ${getBool(xy2d.config.hide)})
       |Set('smooth', ${getBool(xy2d.config.smooth)})
       |To('..')
       |
     """.stripMargin

    //Set('min', <valueChartConfig.minY>)
    //  Set('max',<valueChartConfig.maxY>)
  }

  def xy(xy: GraphItems.XY, index: Int)(implicit data: Data) = {
    val xName = data.numericData(xy.xYData.x.data)
    val yName = data.numericData(xy.xYData.y.data)
    val scaleName =
      if (xy.xYData.scaleMarkers.data.size > 0) data.numericData(xy.xYData.scaleMarkers.data)
      else ""
    s"""
       |
       |Add('xy', name='${xy.name}_[$index]', autoadd=False)
       |To('${xy.name}_[$index]')
       |Set('xData', u'$xName')
       |Set('yData', u'$yName')
       |Set('xAxis', u'${xy.xAxis}')
       |Set('yAxis', u'${xy.yAxis}')
       |Set('scalePoints', u'$scaleName')
       |Set('key', u'${xy.keyText}')
       |${markerStyle(xy.config.mainStyle)}
       |${plotlineStyle(xy.config.lineStyle)}
       |${markerBorder(xy.config.markerBorder)}
       |${markerFill(xy.config.markerFill)}
       |${fill("Below", xy.config.fillBelow)}
       |${fill("Above", xy.config.fillAbove)}
       |To('..') # End of Graph Item XY : ${xy.name}_[$index]
       |
     """.stripMargin
  }

  /*



Set('labels', u'${xy.labels}')
Set('scalePoints', u'${xy.scaleMarkers}')
Set('Color/points', u'${xy.colorMarkers}')


  Set('xAxis', u'${xy.xAxis}')




   */
  def function(function: GraphItems.Function, index: Int) =
    s"""
       |
       |Add('function', name='${function.name}_[$index]', autoadd=False)
       |To('${function.name}_[$index]')
       |Set('function', u'${function.function}')
       |Set('key', u'${function.key}')
       |Set('min', ${getOption(function.max)})
       |Set('max', ${getOption(function.min)})
       |
       |Set('steps', ${function.config.steps})
       |${lineStyle(function.config.plotLine)}
       |
       |To('..')  # End of Graph Item Function ${function.name}_[$index]
       |
     """.stripMargin

  def polygon(poly: GraphItems.Polygon, index: Int) =
    s"""
       |Add('polygon', name='${poly.name}_[$index]', autoadd=False)
       |To('${poly.name}_[$index]')
       |Set('xPos', [${poly.xPositions.mkString(", ")}])
       |Set('yPos', [${poly.yPositions.mkString(", ")}])
       |Set('positioning', u'${poly.positionMode}')
       |${lineStyle(poly.config.lineStyle)}
       |${simpleFill(poly.config.fill)}
       |To('..')
       |
       |""".stripMargin

  def simpleFill(f: SimpleFill) =
    s"""
       |
       |Set('Fill/color', u'${f.color}')
       |Set('Fill/style', u'${f.style}')
       |Set('Fill/hide',  ${getBool(f.hide)})
       |Set('Fill/transparency', ${f.transparency})
       |
     """.stripMargin

  def line(line: GraphItems.Line, index: Int) =
    s"""
       |Add('line', name='${line.name}_[$index]', autoadd=False)
       |To('${line.name}_[$index]')
       |Set('mode', u'length-angle')
       |Set('xPos', [${line.xPos}])
       |Set('yPos', [${line.yPos}])
       |Set('length', [${line.length}])
       |Set('angle', [${line.angle}])
       |Set('positioning', u'axes')
       |
       |Set('arrowleft', u'${line.config.arrowStyle.arrowleft}')
       |Set('arrowright', u'${line.config.arrowStyle.arrowright}')
       |Set('Fill/color', u'${line.config.arrowStyle.color}')
       |${lineStyle(line.config.plotLine)}
       |To('..')  # End of Graph Item Line {line.name}_[$index]
       |""".stripMargin

  def markerStyle(ms: XYMainStyle) = {
    s"""
       |
       |Set('marker', u'${ms.markerType}')
       |Set('markerSize', u'${ms.size}pt')
       |Set('color', u'${ms.color}')
       |Set('thinfactor', ${ms.thinMarkers})
       |Set('errorthin', ${ms.thinErrors})
       |Set('hide', ${getBool(ms.hide)})
       |Set('errorStyle', u'${ms.errorType}')
       |
     """.stripMargin
  }

  def simpleMarkerBorder(mb: SimpleBorder) = {
    s"""
       |
       |Set('MarkersLine/color', u'${mb.color}')
       |Set('MarkersLine/width', u'${mb.width}pt')
       |Set('MarkersLine/style', u'${mb.style}')
       |Set('MarkersLine/hide', ${getBool(mb.hide)})
       |
    """.stripMargin
  }

  def markerBorder(mb: MarkerBorder) = {
    s"""
      |
      |Set('MarkerLine/color', u'${mb.color}')
      |Set('MarkerLine/width', u'${mb.width}pt')
      |Set('MarkerLine/style', u'${mb.style}')
      |Set('MarkerLine/scaleLine', ${getBool(mb.scale)})
      |Set('MarkerLine/hide', ${getBool(mb.hide)})
      |
    """.stripMargin
  }

  def markerFill(mf: MarkerFill) = {
    s"""
       |
       |Set('MarkerFill/color', u'${mf.color}')
       |Set('MarkerFill/style', u'solid')
       |Set('MarkerFill/hide', ${getBool(mf.hide)})
       |Set('MarkerFill/colorMap', u'${mf.colorMap}')
       |Set('MarkerFill/colorMapInvert', ${getBool(mf.invertmap)})
       |
     """.stripMargin
  }

  def fill(filltype: String = "Below", f: Fill) = {
    s"""
       |
       |Set('Fill$filltype/fillto', u'${f.fillTo}')
       |Set('Fill$filltype/color', u'${f.color}')
       |Set('Fill$filltype/style', u'${f.style}')
       |Set('Fill$filltype/hide',  ${getBool(f.hide)})
       |Set('Fill$filltype/hideerror',  ${getBool(f.hideErrorFill)})
       |Set('Fill$filltype/transparency', ${f.transparency})
       |
     """.stripMargin
  }

  def plotlineStyle(ls: LineStyle) =
    s"""
       |
       |Set('PlotLine/color', u'${ls.color}')
       |Set('PlotLine/width', u'${ls.width}pt')
       |Set('PlotLine/style', u'${ls.style}')
       |Set('PlotLine/transparency', ${ls.transparency})
       |Set('PlotLine/hide', ${getBool(ls.hide)})
       |
     """.stripMargin

  def lineStyle(ls: LineStyle) =
    s"""
       |
       |Set('Line/color', u'${ls.color}')
       |Set('Line/width', u'${ls.width}pt')
       |Set('Line/style', u'${ls.style}')
       |Set('Line/transparency', ${ls.transparency})
       |Set('Line/hide', ${getBool(ls.hide)})
       |
     """.stripMargin

  def axisLabelStyle(ls: AxisLabelStyle) =
    s"""
       |
       |Set('Label/font', u'${ls.font}')
       |Set('Label/size', u'${ls.size}pt')
       |Set('Label/color', u'${ls.color}')
       |Set('Label/italic', ${getBool(ls.italic)})
       |Set('Label/bold', ${getBool(ls.bold)})
       |Set('Label/underline', ${getBool(ls.underline)})
       |Set('Label/atEdge', ${getBool(ls.atEdge)})
       |Set('Label/rotate', u'${ls.rotate}')
       |Set('Label/offset', u'${ls.labelOffset}pt')
       |Set('Label/position', u'${ls.position}')
       |
     """.stripMargin

  def tickLabelStyle(ls: TickLabelStyle) =
    s"""
       |
       |Set('TickLabels/font', u'${ls.font}')
       |Set('TickLabels/size', u'${ls.size}pt')
       |Set('TickLabels/color', u'${ls.color}')
       |Set('TickLabels/italic', ${getBool(ls.italic)})
       |Set('TickLabels/bold', ${getBool(ls.bold)})
       |Set('TickLabels/underline', ${getBool(ls.underline)})
       |Set('TickLabels/rotate', u'${ls.rotate}')
       |Set('TickLabels/offset', u'${ls.tickOffset}pt')
       |Set('TickLabels/scale', ${ls.scale})
       |
     """.stripMargin

  def majorTickStyle(ts: MajorTickStyle, minor: Boolean = false) = {

    s"""
       |
       |Set('MajorTicks/color', u'${ts.color}')
       |Set('MajorTicks/width', u'${ts.width}pt')
       |Set('MajorTicks/manualTicks', [${ts.manualTicks}])
       |Set('MajorTicks/style', u'${ts.style}')
       |Set('MajorTicks/length', u'${ts.length}pt')
       |Set('MajorTicks/number', ${ts.number})
       |Set('MajorTicks/hide', ${getBool(ts.hide)})
       |
     """.stripMargin
  }

  def minorTickStyle(ts: MinorTickStyle) = {
    s"""
       |
       |Set('MinorTicks/color',  u'${ts.color}')
       |Set('MinorTicks/width', u'${ts.width}pt')
       |Set('MinorTicks/style',u'${ts.style}')
       |Set('MinorTicks/length',  u'${ts.length}pt')
       |Set('MinorTicks/number', ${ts.number})
       |Set('MinorTicks/hide', ${getBool(ts.hide)})
       |
     """.stripMargin
  }

  def majorGridLines(gl: MajorGridLines) = {

    s"""
       |
       |Set('GridLines/color', u'${gl.color}')
       |Set('GridLines/width', u'${gl.width}pt')
       |Set('GridLines/style', u'${gl.style}')
       |Set('GridLines/transparency', ${gl.transparency})
       |Set('GridLines/hide', ${getBool(gl.hide)})
       |Set('GridLines/onTop', ${getBool(gl.onTop)})
       |
     """.stripMargin
  }

  def minorGridLines(gl: MinorGridLines) = {

    s"""
       |
       |Set('MinorGridLines/color', u'${gl.color}')
       |Set('MinorGridLines/width', u'${gl.width}pt')
       |Set('MinorGridLines/style', u'${gl.style}')
       |Set('MinorGridLines/transparency', ${gl.transparency})
       |Set('MinorGridLines/hide', ${getBool(gl.hide)})
       |
     """.stripMargin
  }

  def getBool(b: Boolean) = if (b) "True" else "False"

  def getOption(value: Option[Double]) = value match {
    case Some(v) => v.toString
    case None    => "u'Auto'"

  }

  implicit class StringTools(v: String) {

    def noBlanks() = {
      v.replace(" ", "_").replace(",", "")
    }
  }
}
