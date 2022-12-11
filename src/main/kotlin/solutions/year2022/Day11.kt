package solutions.year2022

import DataFetcher
import solutions.Solution

class Day11(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 11
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        val monkeys = input.generateMonkeys()
        (1..20).forEach { _ ->
            monkeys.runRound(null, ::inspectItemPartOne)
        }
        val foo = monkeys.map { it.counter }.sorted().reversed()
        return (foo[0] * foo[1]).toString()
    }

    override fun partTwoResult(): String {
        val monkeys = input.generateMonkeys()
        val lcd = monkeys.lcd()
        (1..10000).forEach { _ ->
            monkeys.runRound(lcd, ::inspectItemPartTwo)
        }
        val foo = monkeys.map { it.counter.toLong() }.sorted().reversed()
        return (foo[0] * foo[1]).toString()
    }

    private fun List<Monkey>.runRound(lcd: Int?, inspectionFunc: (Monkey, Long, Int?) -> Long): List<Monkey> {
        this.forEach { monkey ->
            monkey.items
                .map { inspectionFunc(monkey, it, lcd) }
                .forEach { item ->
                    monkey.counter++
                    if (item % monkey.testVal == 0L)
                        this.first { m -> m.id == monkey.positiveLink }.items.add(item)
                    else
                        this.first { m -> m.id == monkey.negativeLink }.items.add(item)
                }
            monkey.items = mutableListOf()
        }
        return this
    }

    private fun inspectItemPartOne(monkey: Monkey, item: Long, lcd: Int?): Long =
        when (monkey.operation) {
            '+' -> (item + if (monkey.insVal != 0) monkey.insVal.toLong() else item) / 3
            else -> (item * if (monkey.insVal != 0) monkey.insVal.toLong() else item) / 3
        }

    private fun inspectItemPartTwo(monkey: Monkey, item: Long, lcd: Int?): Long =
        when (monkey.operation) {
            '+' -> (item + if (monkey.insVal != 0) monkey.insVal.toLong() else item) % lcd!!
            else -> (item * if (monkey.insVal != 0) monkey.insVal.toLong() else item) % lcd!!
        }

    private fun String.generateMonkeys(): List<Monkey> =
        this.split("\n\n").map {
            val lines = it.split("\n")
            Monkey(
                "\\d+".toRegex().find(lines[0])?.value?.toInt() ?: 0,
                "\\d+".toRegex().findAll(lines[1]).map { it.value.toLong() }.toMutableList(),
                lines[2][23],
                "\\d+".toRegex().find(lines[2])?.value?.toInt() ?: 0,
                "\\d+".toRegex().find(lines[3])?.value?.toInt() ?: 0,
                "\\d+".toRegex().find(lines[4])?.value?.toInt() ?: 0,
                "\\d+".toRegex().find(lines[5])?.value?.toInt() ?: 0
            )
        }

    private fun List<Monkey>.lcd(): Int {
        val numbers = this.map { it.testVal }
        var candidate = numbers.max()
        val step = candidate
        while (numbers.any { candidate % it != 0 })
            candidate += step
        return candidate
    }
}

class Monkey(
    val id: Int,
    var items: MutableList<Long>,
    val operation: Char,
    val insVal: Int,
    val testVal: Int,
    val positiveLink: Int,
    val negativeLink: Int,
    var counter: Int = 0
)

