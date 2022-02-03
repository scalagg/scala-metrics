package gg.scala.metrics.shared.event

import gg.scala.metrics.shared.internal.ScalaMetricsSharedInternal
import gg.scala.metrics.shared.measurement.Measurement
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * @author GrowlyX
 * @since 2/2/2022
 */
object ScalaMetricsSharedEventBus
{
    internal lateinit var postFunction: (Measurement, ScalaMetricsSharedEventType) -> Boolean

    val scheduler: ScheduledExecutorService = Executors.newScheduledThreadPool(4)

    fun configureEventPost(
        lambda: (Measurement, ScalaMetricsSharedEventType) -> Boolean
    )
    {
        postFunction = lambda

        scheduler.scheduleAtFixedRate({
            ScalaMetricsSharedInternal.postMeasurements(
                ScalaMetricsSharedEventType.FAST
            )
        }, 0L, 5L, TimeUnit.SECONDS)

        scheduler.scheduleAtFixedRate({
            ScalaMetricsSharedInternal.postMeasurements(
                ScalaMetricsSharedEventType.NORMAL
            )
        }, 0L, 1L, TimeUnit.MINUTES)
    }
}
