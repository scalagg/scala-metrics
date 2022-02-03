package gg.scala.metrics.shared.measurement

import com.appoptics.metrics.client.Measure
import com.appoptics.metrics.client.Measures

/**
 * @author GrowlyX
 * @since 2/2/2022
 */
class Measurement
{
    private val measures = mutableListOf<Measure>()

    fun add(vararg measure: Measure)
    {
        measures.addAll(measure)
    }

    fun add(measures: List<Measure>)
    {
        this.measures.addAll(measures)
    }

    fun convert(): Measures
    {
        val handle = Measures()
        handle.measures.addAll(this.measures)

        return handle
    }
}
