package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.data.Numerical
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, NonOrthGraphItem}

object Fit {

  def apply(xData: Vector[Double],
            yData: Vector[Double],
            function: String = "a+b*x",
            parameters: Map[String, Double] = Map("a" -> 0.0, "b" -> 1.0),
            fitOnlyRange: Boolean = false,
            outputLabel: String = "",
            variable: String = "x",
            xAxis: String = "x",
            yAxis: String = "y",
            min: Option[Double] = None,
            max: Option[Double] = None,
            name: String = "fit") = new Fit(Numerical(xData), Numerical(yData), function, parameters, fitOnlyRange, outputLabel, variable, xAxis, yAxis, min, max, name)

  def apply(xData: Numerical,
            yData: Numerical,
            function: String,
            parameters: Map[String, Double],
            fitOnlyRange: Boolean,
            outputLabel: String,
            variable: String,
            xAxis: String,
            yAxis: String,
            min: Option[Double],
            max: Option[Double],
            name: String) = new Fit(xData, yData, function, parameters, fitOnlyRange, outputLabel, variable, xAxis, yAxis, min, max, name)
}

case class Fit(xData: Numerical,
               yData: Numerical,
               function: String,
               parameters: Map[String, Double],
               fitOnlyRange: Boolean,
               outputLabel: String,
               variable: String,
               xAxis: String,
               yAxis: String,
               min: Option[Double],
               max: Option[Double],
               name: String)
    extends GraphItem with NonOrthGraphItem with Configurable with Executable {
  var config = FunctionConfig()

  override def group: String = "fit"

}
