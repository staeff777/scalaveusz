# scalaveusz
Scala Api for the Veusz scientific plotting package 

# Excerpt of Architecture

![Excerpt of ScalaVeusz Architecture](https://raw.githubusercontent.com/staeff777/scalaveusz/develop/ScalaVeusz.png)

# How to Use

```scala

import de.dreambeam.veusz.components.{XY, Graph}

object RendererTest extends App {

  // XY Plots use 2 one-dimensional datasets
  val xData = (1.0 to 10.0 by 0.5).toVector
  val yLinear = xData.map (_ * 1.25)
  val ySin = xData.map (2 * Math.sin(_) + 5)

  // create a linear XY Point Plot with Lines
  val xyLinearPlot = XY(xData, yLinear)
  xyLinearPlot.config.plotLine.color = "darkblue"
  xyLinearPlot.config.markerFill.color = "blue"

  // create a sinus XY Point Plot with Lines
  val xySinusPlot = XY(xData, ySin)
  xySinusPlot.config.plotLine.color = "darkred"
  xySinusPlot.config.markerFill.color = "red"

  // put both XY Plots into a Graph
  val graph = Graph(xyLinearPlot, xySinusPlot)

  graph.axis(0).label = "X Axis" //Axis can also be defined in the Graph constructor
  graph.axis(1).label = "Y Axis" //More than just two axis is possible

  graph.openInVeusz("newTest")
}
```

This will result in the following Veusz project:

![Veusz Document](https://raw.githubusercontent.com/staeff777/scalaveusz/master/documentation/example.png)

For further examples see [scalaveusz-examples](https://github.com/staeff777/scalaveusz-examples)

# Structure

- Document
  - Page
    - Grid
    - Graph
      - Axis
      - Barchart
      - Boxplot
      - Contour
      - Covariance
      - Function
      - XY
   - Scene3D
     - Graph3D
       - Axis3D
   - Line
   - Rectangle
   - Polygon
   - ImageFile

# Easily discover Plot-Types

![Using autocompletion](https://raw.githubusercontent.com/staeff777/scalaveusz/develop/docs/auto-completion.PNG)

Just append `.$` to any component in your favourite IDE to see which child-components are available.

# Dealing with DateTime

There are several options:

1. **Using java.time.LocalDate**

    ```scala
    val dates: Vector[LocalDate] = ???
    val datesFormatted = DateTimeConstructor.fromLocalDate(dates)
    ```

2. **Using java.time.LocalDateTime**

    ```scala
    val dates: Vector[LocalDateTime] = ???
    val datesFormatted = DateTimeConstructor.fromLocalDateTime(dates)
    ```

3. **Using String**

    ```scala
    val dates = Vector("1/22/2020", "1/23/2020", "1/24/2020")
    val datesFormatted = DateTimeConstructor.fromString(dates)("M/dd/yyyy")
    ```

### Giving an offset to the dates

```scala
// Increase all dates by 30 days
val datesFormatted = DateTimeConstructor.fromString(dates)("M/dd/yyyy")(Map("dd"->30))
```
