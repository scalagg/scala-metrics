package gg.scala.metrics.shared

import com.appoptics.metrics.client.AppopticsClient
import com.appoptics.metrics.client.Duration
import java.util.concurrent.TimeUnit

/**
 * @author GrowlyX
 * @since 2/2/2022
 */
object ScalaMetricsShared
{
    lateinit var client: AppopticsClient

    fun configure(
        apiToken: String, timeout: Long
    )
    {
        client = AppopticsClient.builder(apiToken)
            .setConnectTimeout(
                Duration(timeout, TimeUnit.SECONDS)
            )
            .setReadTimeout(
                Duration(timeout, TimeUnit.SECONDS)
            )
            .setAgentIdentifier("SC-METRICS")
            .build()
    }
}
