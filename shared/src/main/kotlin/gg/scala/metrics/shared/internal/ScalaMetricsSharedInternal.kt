package gg.scala.metrics.shared.internal

import com.appoptics.metrics.client.PostMeasuresResult
import gg.scala.metrics.shared.ScalaMetricsShared
import gg.scala.metrics.shared.event.ScalaMetricsSharedEventBus
import gg.scala.metrics.shared.event.ScalaMetricsSharedEventType
import gg.scala.metrics.shared.measurement.Measurement
import java.util.concurrent.CompletableFuture
import java.util.logging.Level
import java.util.logging.Logger

/**
 * @author GrowlyX
 * @since 2/2/2022
 */
object ScalaMetricsSharedInternal
{
    @JvmStatic
    val LOGGER: Logger = Logger
        .getLogger("ScalaMetricsSharedInternal")

    fun postMeasurements(
        type: ScalaMetricsSharedEventType
    ): CompletableFuture<PostMeasuresResult>
    {
        val client = ScalaMetricsShared.client

        val measurement = Measurement()
        ScalaMetricsSharedEventBus
            .postFunction.invoke(measurement, type)

        val measures = measurement.convert()

        return CompletableFuture.supplyAsync {
            client.postMeasures(measures)
        }.thenApply {
            for (result in it.results)
            {
                if (result.isError)
                    LOGGER.log(Level.SEVERE, result.exception) {
                        "Something went wrong in the upload process of a measurement!"
                    }
            }

            return@thenApply it
        }
    }
}
