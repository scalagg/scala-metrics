package gg.scala.metrics.shared.event

import com.appoptics.metrics.client.Measures

/**
 * @author GrowlyX
 * @since 2/2/2022
 */
object ScalaMetricsSharedEventBus
{
    internal lateinit var postFunction: (Measures, ScalaMetricsSharedEventType) -> Boolean

    fun configureEventPost(
        lambda: (Measures, ScalaMetricsSharedEventType) -> Boolean
    )
    {
        postFunction = lambda
    }
}
