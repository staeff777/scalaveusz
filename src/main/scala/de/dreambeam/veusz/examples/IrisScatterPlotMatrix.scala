package de.dreambeam.veusz.examples

import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._

import java.io.File

import de.dreambeam.veusz.components._
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.util.SizeUnits._

/**
  * It creates a Scatter Plot Matrix
  * based on the well-known Iris dataset.
  * In this example, the Scatter Plot Matrix
  * consists of 16 scatter plots.
  * Each scatter plot shows the 3 categories
  * plotted in different colors.
  */
object IrisScatterPlotMatrix extends App {

  // Parse the CSV from the Iris dataset
  // It will throw an exception if unable to parse the file
  val reader = new File("data/iris/iris.data").asUnsafeCsvReader[IrisRow](rfc.withoutHeader).toVector

  // Assign each Iris Category a flower
  val categoryColor = Map("Iris-setosa" -> "#f47458" // ~ red
    ,"Iris-versicolor" -> "#58f46d" // ~ green
    ,"Iris-virginica" -> "#5f8cf4" // ~ blue
  )

  type featureData = Vector[Double]

  /**
    *
    * @param name name of the category
    * @param color hex color code
    */
  case class Category(name: String, color: String)

  /**
    *
    * @param name name of the feature
    * @param values the feature data for each category
    */
  case class Feature(name: String, values: Map[Category, featureData])

  /**
    *
    * @param name the name of feature is a tuple of both features names
    * @param x feature on x-Axis
    * @param y feature on y-Axis
    */
  case class FeatureCombination(name: (String, String), x: Feature, y: Feature)

  /**
    * Extract the features
    * - sepalLength
    * - sepalWidth
    * - petalLength
    * - petalWidth
    * from the CSV reader
    * grouped by the category
    */
  val sepalLengthFeature = reader.groupBy(_.name).map {
    case (name, values) => (Category(name, categoryColor(name)), values.map(_.sepalLength))
  }

  val sepalWidthFeature = reader.groupBy(_.name).map {
    case (name, values) => (Category(name, categoryColor(name)), values.map(_.sepalWidth))
  }

  val petalLengthFeature = reader.groupBy(_.name).map {
    case (name, values) => (Category(name, categoryColor(name)), values.map(_.petalLength))
  }

  val petalWidthFeature = reader.groupBy(_.name).map {
    case (name, values) => (Category(name, categoryColor(name)), values.map(_.petalWidth))
  }

  /**
    * Name the features and wrap them in case classes
    */
  val sepalLength = Feature("SepalLength", sepalLengthFeature)
  val sepalWidth = Feature("SepalWidth", sepalWidthFeature)
  val petalLength = Feature("PetalLength", petalLengthFeature)
  val petalWidth = Feature("PetalWidth", petalWidthFeature)

  /**
    * A list of all the features.
    * Since the Vector is ordered, the position of the features
    * determines their position in the graph,
    * where the position in the vector will be the
    * position of the feature row from top to bottom.
    */
  val features = Vector(sepalLength, sepalWidth, petalLength, petalWidth)

  /**
    * Here we combine the features.
    */
  val featureCombinations = for (feature2 <- features; feature1 <- features) yield {
    FeatureCombination((feature1.name, feature2.name), feature1, feature2)
  }

  /**
    *
    *
    *
    * @param fc FeatureCombination which contains xFeatures and yFeatures
    * @return a Graph which contains these features, or, if the x and y features are the same,
    *         the name of the feature
    */
  def createGraph(fc: FeatureCombination) = {
    val (ft1, ft2) = (fc.x, fc.y) // Extract the x, y features
    val (xName, yName) = fc.name

    if (xName == yName) { // here we only display the name of the feature
      val label = Label(xName)
      // Configuring the label
      label.config.alignment.horizontal = HorizontalPosition.Centre // align label at horizontal center
      label.config.alignment.vertical = VerticalPosition.Centre // align label at vertical center
      label.config.border.hide = true // no border

      Graph(label) // returns the graph
    } else {
      val featureValues = ft1.values.zip(ft2.values)

      val plots = for (featureValue <- featureValues) yield {
        val (xFeatures, yFeatures) = featureValue
        val xData = xFeatures._2 // pass feature_data
        val yData = yFeatures._2 // pass feature_data
        val xyPlot = XY(xData, yData)

        // Configure the plot
        xyPlot.config.main.markerSize = 1.5 pt;
        xyPlot.config.plotLine.hide = true
        xyPlot.config.markerBorder.hide = true
        xyPlot.config.markerFill.color = xFeatures._1.color

        xyPlot
      }

      Graph(children=plots.toVector)
    }
  }

  /**
    * Create all the graphs (16 in total)
    * And adjust some of the graph settings
    * depending on graphs position in the grid
    */
  val graphs = for((fc, i) <- featureCombinations.zipWithIndex) yield {
    val ix = (i % 4) + 1
    val iy = (i / 4) + 1

    val graph = createGraph(fc)

    // This is for only showing the
    // x and y axis at certain positions
    // if the axis is not needed, it will be hidden
    (ix, iy) match {
      case (ix, iy) if ix == 1 && iy == 4 => {
        // fine as it is
      }
      case (ix, iy) if ix == 3 && iy == 4 => {
        graph.axis(1).config.main.hide = true
      }
      case (ix, iy) if ix == 4 && iy == 1 => {
        graph.axis(0).axisPosition = 1
        graph.axis(1).axisPosition = 1
      }
      case (ix, iy) if ix == 2 && iy == 1 => {
        graph.axis(0).axisPosition = 1
        graph.axis(1).config.main.hide = true
      }
      case (ix, iy) if ix == 1 && iy == 2 => {
        graph.axis(0).config.main.hide = true
      }
      case (ix, iy) if ix == 4 && iy == 3 => {
        graph.axis(0).config.main.hide = true
        graph.axis(1).axisPosition = 1
      }
      case (ix, iy) if ix == iy => {
        graph.axis(0).config.main.hide = true
        graph.axis(1).config.main.hide = true
      }
      case (_, _) => {
        graph.axis(0).config.main.hide = true
        graph.axis(1).config.main.hide = true
      }
    }

    // Configure the graph
    graph.axis(0).config.tickLabels.size = 8.0 // adjust size of x-axis numbers
    graph.axis(1).config.tickLabels.size = 8.0 // adjust size of y-axis numbers
    graph.config.main.leftMargin = 2.0 pt;
    graph.config.main.rightMargin = 2.0 pt;
    graph.config.main.topMargin = 2.0 pt;
    graph.config.main.bottomMargin = 2.0 pt;

    graph
  }

  // Add the graphs to the grid
  val grid = Grid(Some(graphs), rows=4, columns=4) // since we compare all x/y combinations of those 4 features (4x4)

  // Configure the grid
  grid.config.leftMargin = 1 cm;
  grid.config.rightMargin = 1 cm;
  grid.config.topMargin = 1 cm;
  grid.config.bottomMargin = 1 cm;

  val page = Page(grid) //
  // Add grid to page
  val document = Document(page) // Add page to document

  document.show("IrisScatterPlotMatrix") // save the document and open in Veusz
}