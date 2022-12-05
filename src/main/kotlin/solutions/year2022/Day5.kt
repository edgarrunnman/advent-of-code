package solutions.year2022

import DataFetcher
import solutions.Solution

class Day5(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 5
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        val docks = getDocks(input.split("\n\n")[0].split("\n"))
        val commands = getCommands(input.split("\n\n")[1].split("\n").filter { it.isNotEmpty() })
        return docks.runCommands("CrateMover 9000", commands).map { it.first() }.joinToString("")
    }

    override fun partTwoResult(): String {
        val docks = getDocks(input.split("\n\n")[0].split("\n"))
        val commands = getCommands(input.split("\n\n")[1].split("\n").filter { it.isNotEmpty() })
        return docks.runCommands("CrateMover 9001", commands).map { it.first() }.joinToString("")
    }

    private fun getDocks(lines: List<String>): List<List<Char>> =
        (0..lines[1].count() / 4)
            .map { lines.map { line -> line[it * 4 + 1] }.filter { box -> box != ' ' } }

    private fun getCommands(lines: List<String>): List<Triple<Int, Int, Int>> =
        lines.map { it.split(" ").filter { value -> "\\d*".toRegex().matches(value) } }
            .map { Triple(it[0].toInt(), it[1].toInt() - 1, it[2].toInt() - 1) }

    private fun List<List<Char>>.runCommands(
        machine: String,
        commands: List<Triple<Int, Int, Int>>
    ): List<List<Char>> {
        val docks = this.toMutableList()
        commands.forEach { cmd ->
            if (machine == "CrateMover 9000")
                (1..cmd.first).forEach { _ ->
                    docks[cmd.third] = listOf(docks[cmd.second].first()) + docks[cmd.third]
                    docks[cmd.second] = docks[cmd.second].subList(1, docks[cmd.second].count())
                }
            if (machine == "CrateMover 9001") {
                docks[cmd.third] = docks[cmd.second].subList(0, cmd.first) + docks[cmd.third]
                docks[cmd.second] = docks[cmd.second].subList(cmd.first, docks[cmd.second].count())
            }
        }
        return docks
    }
}