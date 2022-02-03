package gg.scala.metrics.spigot.events

import gg.scala.metrics.shared.measurement.Measurement

/**
 * @author GrowlyX
 * @since 2/2/2022
 */
class FastMetricPostEvent(
    measurement: Measurement
) : MetricPostEvent(measurement)

class SlowMetricPostEvent(
    measurement: Measurement
) : MetricPostEvent(measurement)
