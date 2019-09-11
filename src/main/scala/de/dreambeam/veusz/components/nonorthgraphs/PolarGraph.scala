package de.dreambeam.veusz.components.nonorthgraphs

import de.dreambeam.veusz.components.Colorbar
import de.dreambeam.veusz.components.graph.Function
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GridItem, NonOrthGraphItem, PageItem, Parent}

object PolarGraph {
  val defaultName = "polarGraph"

  def apply(children: NonOrthGraphItem*): PolarGraph = PolarGraph(children = children.toVector)

  def apply(name: String, children: NonOrthGraphItem*): PolarGraph = PolarGraph(name, children = children.toVector)

  def apply(name: String, minRadius: Option[Double], maxRadius: Option[Double], children: NonOrthGraphItem*): PolarGraph = PolarGraph(name, children = children.toVector)

  def apply(name: String, minRadius: Option[Double], maxRadius: Option[Double], unit: PolarUnit.Value, children: NonOrthGraphItem*): PolarGraph =
    PolarGraph(name, minRadius, maxRadius, unit, children = children.toVector)

  def apply(name: String, unit: PolarUnit.Value, children: NonOrthGraphItem*): PolarGraph = PolarGraph(name, unit = unit, children = children.toVector)

  def apply(name: String, minRadius: Option[Double], maxRadius: Option[Double], unit: PolarUnit.Value, direction: PolarDirection.Value, children: NonOrthGraphItem*): PolarGraph =
    PolarGraph(name, minRadius, maxRadius, unit, direction, children = children.toVector)

  def apply(name: String,
            minRadius: Option[Double],
            maxRadius: Option[Double],
            unit: PolarUnit.Value,
            direction: PolarDirection.Value,
            positionOf0: PolarPositionOf0.Value,
            children: NonOrthGraphItem*): PolarGraph = PolarGraph(name, minRadius, maxRadius, unit, direction, positionOf0, children = children.toVector)

  def apply(name: String,
            minRadius: Option[Double],
            maxRadius: Option[Double],
            unit: PolarUnit.Value,
            direction: PolarDirection.Value,
            positionOf0: PolarPositionOf0.Value,
            log: Boolean,
            children: NonOrthGraphItem*): PolarGraph =
    new PolarGraph(name, minRadius, maxRadius, unit, direction, positionOf0, log, children = children.toVector, config = PolarGraphConfig())

  def apply(name: String = PolarGraph.defaultName,
            minRadius: Option[Double] = None,
            maxRadius: Option[Double] = None,
            unit: PolarUnit.Value = PolarUnit.degrees,
            direction: PolarDirection.Value = PolarDirection.clockwise,
            positionOf0: PolarPositionOf0.Value = PolarPositionOf0.top,
            log: Boolean = false,
            children: Vector[NonOrthGraphItem] = Vector.empty): PolarGraph =
    new PolarGraph(name, minRadius, maxRadius, unit, direction, positionOf0, log, children, config = PolarGraphConfig())

}

case class PolarGraph(var name: String,
                      var minRadius: Option[Double],
                      var maxRadius: Option[Double],
                      var units: PolarUnit.Value,
                      var direction: PolarDirection.Value,
                      var positionOf0: PolarPositionOf0.Value,
                      var log: Boolean,
                      children: Vector[NonOrthGraphItem],
                      var config: PolarGraphConfig)
    extends PageItem with GridItem with Configurable with Executable with Parent {
  val group = "polar"
}

case class PolarGraphConfig(var notes: String = "",
                            var main: PolarGraphMainConfig = PolarGraphMainConfig(),
                            var background: BackgroundConfig = BackgroundConfig(),
                            var border: BorderConfig = BorderConfig(),
                            var tickLabels: RadialTickLabelsConfig = RadialTickLabelsConfig(),
                            var spokeLine: RadialTicksConfig = RadialTicksConfig(),
                            var radiiLine: RadialTicksConfig = RadialTicksConfig())
