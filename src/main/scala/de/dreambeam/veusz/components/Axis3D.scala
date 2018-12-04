package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Item, Graph3DItem, Executable, Configurable}
import de.dreambeam.veusz.format._

object Axis3D {

  def apply(name: String,
            label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            direction: String = "horizontal",
            minPos: Double = 0,
            maxPos: Double = 1,
            axisPosition1: Double = 0,
            axisPosition2: Double = 0
           ) =
    new Axis3D(name, None, label, min, max, log, mode, scale, direction, minPos, maxPos, axisPosition1, axisPosition2)

}

class Axis3D private (val name: String,
                      val children: Option[Vector[Item]],
                      var label: String,
                      var min: Option[Double],
                      var max: Option[Double],
                      var log: Boolean,
                      var mode: AxisMode.Value,
                      var scale: Double,
                      var direction: String,
                      var minPos: Double,
                      var maxPos: Double,
                      var axisPosition1: Double,
                      var axisPosition2: Double
                     )
  extends Graph3DItem
    with Configurable
    with Executable
{
  val group = "axis3d"

  object config {
    val main = Axis3DMainConfig()
    val axisLine = Axis3DLineConfig()
    val axisLabel = Axis3DLabelConfig()
    val tickLabels = Axis3DTickLabelsConfig()
    val majorTicks = Axis3DMajorTicksConfig()
    val minorTicks = Axis3DMinorTicksConfig()
    val majorGridLines = Axis3DMajorGridLinesConfig()
    val minorGridLines = Axis3DMinorGridLinesConfig()
  }
}

object XAxis3D {

  def apply(name: String = "x",
            label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxPos: Double = 1,
            axisPosition1: Double = 0,
            axisPosition2: Double = 0) =
    Axis3D(name, label, min, max, log, mode, scale, "x", minPos, maxPos, axisPosition1, axisPosition2)
}

object YAxis3D {

  def apply(name: String = "y",
            label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxPos: Double = 1,
            axisPosition1: Double = 0,
            axisPosition2: Double = 0) =
    Axis3D(name, label, min, max, log, mode, scale, "y", minPos, maxPos, axisPosition1, axisPosition2)
}

object ZAxis3D {

  def apply(name: String = "z",
            label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxPos: Double = 1,
            axisPosition1: Double = 0,
            axisPosition2: Double = 0) =
    Axis3D(name, label, min, max, log, mode, scale, "z", minPos, maxPos, axisPosition1, axisPosition2)
}