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
            name: String = "fit",
            config: FunctionConfig = FunctionConfig()) = new Fit(Numerical(xData), Numerical(yData), function, parameters, fitOnlyRange, outputLabel, variable, xAxis, yAxis, min, max, name, config)

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
            name: String) = new Fit(xData, yData, function, parameters, fitOnlyRange, outputLabel, variable, xAxis, yAxis, min, max, name,  FunctionConfig())

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
            max: Option[Double]) = new Fit(xData, yData, function, parameters, fitOnlyRange, outputLabel, variable, xAxis, yAxis, min, max, "fit",  FunctionConfig())

  def apply(xData: Numerical,
            yData: Numerical,
            function: String,
            parameters: Map[String, Double],
            fitOnlyRange: Boolean,
            outputLabel: String,
            variable: String,
            xAxis: String,
            yAxis: String) = new Fit(xData, yData, function, parameters, fitOnlyRange, outputLabel, variable, xAxis, yAxis, None, None, "fit",  FunctionConfig())

  def apply(xData: Numerical,
            yData: Numerical,
            function: String,
            parameters: Map[String, Double],
            fitOnlyRange: Boolean,
            outputLabel: String,
            variable: String) = new Fit(xData, yData, function, parameters, fitOnlyRange, outputLabel, variable, "x", "y", None, None, "fit",  FunctionConfig())

  def apply(xData: Numerical,
            yData: Numerical,
            function: String,
            parameters: Map[String, Double],
            fitOnlyRange: Boolean,
            outputLabel: String) = new Fit(xData, yData, function, parameters, fitOnlyRange, outputLabel, "x", "x", "y", None, None, "fit",  FunctionConfig())

  def apply(xData: Numerical,
            yData: Numerical,
            function: String,
            parameters: Map[String, Double],
            fitOnlyRange: Boolean) = new Fit(xData, yData, function, parameters, fitOnlyRange, "", "x", "x", "y", None, None, "fit",  FunctionConfig())

  def apply(xData: Numerical,
            yData: Numerical,
            function: String,
            parameters: Map[String, Double]) = new Fit(xData, yData, function, parameters, false, "", "x", "x", "y", None, None, "fit",  FunctionConfig())
}

case class Fit(var xData: Numerical,
               var yData: Numerical,
               var function: String,
               var parameters: Map[String, Double],
               var fitOnlyRange: Boolean,
               var outputLabel: String,
               var variable: String,
               var xAxis: String,
               var yAxis: String,
               var min: Option[Double],
               var max: Option[Double],
               var name: String,
               var config: FunctionConfig)
    extends GraphItem with NonOrthGraphItem with Configurable with Executable {


  override def group: String = "fit"

}
