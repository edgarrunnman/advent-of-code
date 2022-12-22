package solutions.year2022

import DataFetcher
import solutions.Solution

class Day16(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 16
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        var foo = inputAsList.getValves()
        var valves = foo.getLinkedValves().sortedBy { it.flowRate }.reversed()
        var topicalValves = valves.filter { !it.isOpen && it.flowRate != 0 }
        var openedValves = listOf<Valve>()
        var startValve = valves.getValveById("AA")
        var targetValve = startValve
        var minutesUsed = 1
        var pressureRealised = 0
        (1 until 30).forEach { currentMinute ->
            openedValves.forEach { pressureRealised = it.updatePressure(pressureRealised) }
            if (minutesUsed == currentMinute) {
                if (startValve == targetValve) {
                    targetValve = topicalValves.bestDestination(startValve)
                    var minutesToTarget = startValve.shortestPathTo(targetValve).count()
                    minutesUsed += minutesToTarget - 1
                } else {
                    targetValve.isOpen = true
                    openedValves = openedValves + targetValve
                    topicalValves = topicalValves - targetValve
                    startValve = targetValve
                }
            } else
                minutesUsed++
        }

        return pressureRealised.toString()
    }

    override fun partTwoResult(): String {

        return "hej"
    }

    private fun List<Valve>.bestDestination(startValv: Valve): Valve {
        val valves = this.filter { it != startValv }
        val priority =
            valves.map { valve -> Triple(valve, valve.flowRate.toFloat(), startValv.shortestPathTo(valve).count()) }
        val best = priority.sortedBy { it.second / it.third }.reversed().first().first
        return best
    }

    private fun List<String>.getValves(): List<Pair<Valve, String>> {
        val foo = this.map { line ->
            val valveId = "Valve [A-Z]+".toRegex().find(line)?.value?.split(" ")?.get(1) ?: "missing"
            val flowRate = "rate=\\d+".toRegex().find(line)?.value?.split("=")?.get(1)?.toInt() ?: -1
            val linkedValves = line.split(";")[1]

            Pair(Valve(valveId, flowRate, false), linkedValves)
        }
        return foo
    }

    private fun List<Pair<Valve, String>>.getLinkedValves(): List<Valve> {
        return this.map { pair ->
            var linkedValves = "[A-Z]+".toRegex().findAll(pair.second).map { match ->
                this.first { valve -> valve.first.id == match.value }.first
            }.toList()
            pair.first.linkedValves = linkedValves
            pair.first
        }
    }

    private fun Valve.shortestPathTo(targetValve: Valve, visited: List<Valve> = listOf()): List<Valve> {
        var result = listOf(this)
        var tmpResults = listOf<Valve>()
        this.linkedValves.forEach {
            if (it == targetValve) return result + it
            if (it !in visited) {
                val tmpResults2 = it.shortestPathTo(targetValve, visited + this)
                if (tmpResults.isEmpty() || tmpResults.count() > tmpResults2.count())
                    tmpResults = tmpResults2
            }
        }
        return result + tmpResults
    }

    private fun List<Valve>.getValveById(id: String): Valve {
        return this.first { it.id == id }
    }

    data class Valve(val id: String, val flowRate: Int, var isOpen: Boolean) {
        var linkedValves: List<Valve> = listOf()
        fun updatePressure(pressure: Int): Int {
            return if (isOpen) pressure + flowRate else pressure
        }
    }
}


