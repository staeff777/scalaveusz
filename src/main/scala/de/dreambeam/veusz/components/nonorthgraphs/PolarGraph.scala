package de.dreambeam.veusz.components.nonorthgraphs

import de.dreambeam.veusz.components.Colorbar
import de.dreambeam.veusz.components.graph.Function
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GridItem, PageItem, Parent, PolarGraphItem}

object PolarGraph {
  val defaultName = "polarGraph"


  def apply(children: PolarGraphItem*): PolarGraph = PolarGraph(children = Some(children.toVector))

  def apply(children: Vector[PolarGraphItem]): PolarGraph = PolarGraph(children = Some(children))

  def apply(name: String, children: PolarGraphItem*): PolarGraph = PolarGraph(name, children = Some(children.toVector))

  def apply(name: String, children: Vector[PolarGraphItem]): PolarGraph = PolarGraph(name, children = Some(children))

  def apply(name: String,
            minRadius: Option[Double],
            maxRadius: Option[Double],
            children: PolarGraphItem*): PolarGraph = PolarGraph(name, children = Some(children.toVector))


  def apply(name: String,
            minRadius: Option[Double],
            maxRadius: Option[Double],
            unit: PolarUnit.Value,
            children: PolarGraphItem*): PolarGraph = PolarGraph(name, minRadius, maxRadius, unit, children = Some(children.toVector))

  def apply(name: String,
            unit: PolarUnit.Value,
            children: PolarGraphItem*): PolarGraph = PolarGraph(name, unit = unit, children = Some(children.toVector))

  def apply(name: String,
            minRadius: Option[Double],
            maxRadius: Option[Double],
            unit: PolarUnit.Value,
            direction: PolarDirection.Value,
            children: PolarGraphItem*): PolarGraph = PolarGraph(name, minRadius, maxRadius, unit, direction, children = Some(children.toVector))


  def apply(name: String,
            minRadius: Option[Double],
            maxRadius: Option[Double],
            unit: PolarUnit.Value,
            direction: PolarDirection.Value,
            positionOf0: PolarPositionOf0.Value,
            children: PolarGraphItem*): PolarGraph = PolarGraph(name, minRadius, maxRadius, unit, direction, positionOf0, children = Some(children.toVector))

  def apply(name: String,
            minRadius: Option[Double],
            maxRadius: Option[Double],
            unit: PolarUnit.Value,
            direction: PolarDirection.Value,
            positionOf0: PolarPositionOf0.Value,
            log: Boolean,
            children: PolarGraphItem*): PolarGraph = new PolarGraph(name, minRadius, maxRadius, unit, direction, positionOf0, log, children = Some(children.toVector))


  def apply(name: String = PolarGraph.defaultName,
            minRadius: Option[Double] = None,
            maxRadius: Option[Double] = None,
            unit: PolarUnit.Value = PolarUnit.degrees,
            direction: PolarDirection.Value = PolarDirection.clockwise,
            positionOf0: PolarPositionOf0.Value = PolarPositionOf0.top,
            log: Boolean = false,
            children: Option[Vector[PolarGraphItem]] = None): PolarGraph =
    new PolarGraph(name, minRadius, maxRadius, unit, direction, positionOf0, log, children)


  val $Function = Function
  val $Colorbar = Colorbar
}

case class PolarGraph(var name: String,
                      var minRadius: Option[Double],
                      var maxRadius: Option[Double],
                      var units: PolarUnit.Value,
                      var direction: PolarDirection.Value,
                      var positionOf0: PolarPositionOf0.Value,
                      var log: Boolean,
                      children: Option[Vector[PolarGraphItem]]
                     ) extends PageItem
  with GridItem
  with Configurable
  with Executable
  with Parent {
  val group = "polar"
  var config: PolarGraphConfig = PolarGraphConfig()
}

case class PolarGraphConfig(var notes: String = "",
                            var main: PolarGraphMainConfig = PolarGraphMainConfig(),
                            var background: BackgroundConfig = BackgroundConfig(),
                            var border: BorderConfig = BorderConfig(),
                            var tickLabels: RadialTickLabelsConfig = RadialTickLabelsConfig(),
                            var spokeLine: RadialTicksConfig = RadialTicksConfig(),
                            var radiiLine: RadialTicksConfig = RadialTicksConfig()
                           )