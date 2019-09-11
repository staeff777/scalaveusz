package de.dreambeam.veusz.components.graph3d

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, Graph3DItem}

case class Axis3D (label: String = "",
                   min: Option[Double] = None,
                   max: Option[Double] = None,
                   log: Boolean = false,
                   mode: AxisMode.Value = AxisMode.Numeric,
                   scale: Double = 1,
                   direction: String = "horizontal",
                   minPos: Double = 0,
                   maxPos: Double = 1,
                   axisPosition1: Double = 0,
                   axisPosition2: Double = 0,
                   name: String = "",
                   var config: Axis3DConfig = Axis3DConfig())
  extends Graph3DItem
    with Configurable
    with Executable
{
  val group = "axis3d"
}

case class Axis3DConfig(main: Axis3DMainConfig = Axis3DMainConfig(),
                        axisLine: Axis3DLineConfig = Axis3DLineConfig(),
                        axisLabel: Axis3DLabelConfig = Axis3DLabelConfig(),
                        tickLabels: Axis3DTickLabelsConfig = Axis3DTickLabelsConfig(),
                        majorTicks: Axis3DMajorTicksConfig = Axis3DMajorTicksConfig(),
                        minorTicks: Axis3DMinorTicksConfig = Axis3DMinorTicksConfig(),
                        majorGridLines: Axis3DMajorGridLinesConfig = Axis3DMajorGridLinesConfig(),
                        minorGridLines: Axis3DMinorGridLinesConfig = Axis3DMinorGridLinesConfig()
                        )

object XAxis3D {

  def apply(label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxPos: Double = 1,
            axisPosition1: Double = 0,
            axisPosition2: Double = 0,
            name: String = "x") =
    Axis3D(label, min, max, log, mode, scale, "x", minPos, maxPos, axisPosition1, axisPosition2, name)
}

object YAxis3D {

  def apply(label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxPos: Double = 1,
            axisPosition1: Double = 0,
            axisPosition2: Double = 0,
            name: String = "y") =
    Axis3D(label, min, max, log, mode, scale, "y", minPos, maxPos, axisPosition1, axisPosition2, name)
}

object ZAxis3D {

  def apply(label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxPos: Double = 1,
            axisPosition1: Double = 0,
            axisPosition2: Double = 0,
            name: String = "z") =
    Axis3D(label, min, max, log, mode, scale, "z", minPos, maxPos, axisPosition1, axisPosition2, name)
}