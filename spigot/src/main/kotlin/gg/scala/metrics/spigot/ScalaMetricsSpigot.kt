package gg.scala.metrics.spigot

import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.metrics.shared.ScalaMetricsShared
import gg.scala.metrics.shared.event.ScalaMetricsSharedEventBus
import gg.scala.metrics.shared.event.ScalaMetricsSharedEventType
import gg.scala.metrics.spigot.events.FastMetricPostEvent
import gg.scala.metrics.spigot.events.SlowMetricPostEvent
import me.lucko.helper.maven.LibraryLoader
import me.lucko.helper.plugin.ap.Plugin
import me.lucko.helper.plugin.ap.PluginDependency
import net.evilblock.cubed.util.bukkit.Tasks
import org.bukkit.Bukkit

/**
 * @author GrowlyX
 * @since 2/2/2022
 */
@Plugin(
    name = "Metrics",
    description = "Advanced metric tracker, processor, & uploader",
    depends = [
        PluginDependency("Lemon")
    ]
)
class ScalaMetricsSpigot : ExtendedScalaPlugin()
{
    override fun enable()
    {
        val config = loadConfig("appoptics.yml")

        ScalaMetricsShared.configure(
            config.getString("token"),
            config.getLong("timeout"),
        )

        ScalaMetricsSharedEventBus.configureEventPost { measurement, type ->
            Tasks.sync {
                Bukkit.getPluginManager().callEvent(
                    when (type)
                    {
                        ScalaMetricsSharedEventType.NORMAL -> SlowMetricPostEvent(measurement)
                        ScalaMetricsSharedEventType.FAST -> FastMetricPostEvent(measurement)
                    }
                )
            }

            return@configureEventPost true
        }

        server.pluginManager.registerEvents(
            ScalaMetricsSpigotListener, this
        )
    }

    override fun disable()
    {
        ScalaMetricsSharedEventBus.scheduler.shutdown()
    }
}
