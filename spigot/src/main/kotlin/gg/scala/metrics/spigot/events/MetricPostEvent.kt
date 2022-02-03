package gg.scala.metrics.spigot.events

import gg.scala.metrics.shared.measurement.Measurement
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author GrowlyX
 * @since 2/2/2022
 */
abstract class MetricPostEvent(
    val measurement: Measurement
) : Event()
{
    companion object
    {
        @JvmStatic
        val HANDLERS = HandlerList()
    }

    override fun getHandlers() = HANDLERS
}
