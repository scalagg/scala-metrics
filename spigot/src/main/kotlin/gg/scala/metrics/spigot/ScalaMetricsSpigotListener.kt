package gg.scala.metrics.spigot

import com.appoptics.metrics.client.Measure
import gg.scala.lemon.Lemon
import gg.scala.metrics.spigot.events.FastMetricPostEvent
import gg.scala.metrics.spigot.events.SlowMetricPostEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

/**
 * @author GrowlyX
 * @since 2/2/2022
 */
object ScalaMetricsSpigotListener : Listener
{
    @EventHandler
    fun onSlowMetricPost(event: SlowMetricPostEvent)
    {
        val players = Bukkit.getOnlinePlayers()
        val measurement = event.measurement

        measurement.add(
            Measure("spigot.general.players", players.size.toDouble()),
            Measure(
                "spigot.general.players.staff",
                players.filter {
                    it.hasPermission("lemon.staff")
                }.size.toDouble()
            ),
            Measure("spigot.general.slots", Bukkit.getMaxPlayers().toDouble())
        )
    }

    @EventHandler
    fun onFastMetricPost(event: FastMetricPostEvent)
    {
        val statisticProvider = Lemon.instance
            .serverStatisticProvider
        val measurement = event.measurement

        measurement.add(
            Measure("spigot.general.tps", statisticProvider.ticksPerSecond())
        )
    }
}
