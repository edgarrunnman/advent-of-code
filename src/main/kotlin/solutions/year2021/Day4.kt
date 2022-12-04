package solutions.year2021

import DataFetcher
import solutions.Solution

class Day4(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2021
    override val day: Int = 4
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): Int {
        val inputData = input.split("\n\n")
        val numbers = inputData.first().split(",")
        val tables = inputData.slice(1 until inputData.size).map { Table(it) }
        return playToFirst(numbers, tables)
    }

    override fun partTwoResult(): Int {
        val inputData = input.split("\n\n")
        val numbers = inputData.first().split(",")
        val tables = inputData.slice(1 until inputData.size).map { Table(it) }
        return playToLast(numbers, tables)
    }

    private fun playToFirst(numbers: List<String>, tables: List<Table>): Int {
        numbers.first().let { tables.forEach { table -> table.markCell(it) } }
        if (tables.any { it.checkBingo() })
            return tables.first { it.checkBingo() }.getSum() * numbers.first().toInt()
        return playToFirst(numbers.slice(1 until numbers.size), tables)
    }

    private fun playToLast(numbers: List<String>, tables: List<Table>, lastBingoResult: Int = -1): Int {
        var lastBingo = lastBingoResult
        numbers.first().let { tables.forEach { table -> table.markCell(it) } }
        if (tables.any { it.checkBingo() })
            lastBingo = tables.first { it.checkBingo() }.getSum() * numbers.first().toInt()
        if (numbers.count() == 1 || tables.isEmpty())
            return lastBingo
        return playToLast(numbers.slice(1 until numbers.size), tables.filter { !it.checkBingo() }, lastBingo)
    }
}

class Table(def: String) {
    private lateinit var rows: List<List<String>>
    private lateinit var columns: List<List<String>>

    init {
        rows = def.split("\n", limit = 5).map { it.split(" ").filter { cell -> cell.isNotBlank() } }
        columns = List(5) { rows.map { row -> row[it] } }
    }

    fun markCell(number: String) {
        rows = rows.map { it.map { cell -> if (cell == number) "" else cell } }
        columns = columns.map { it.map { cell -> if (cell == number) "" else cell } }
    }

    fun checkBingo(): Boolean =
        rows.any { it.all { cell -> cell == "" } } || columns.any { it.all { cell -> cell == "" } }

    fun getSum(): Int =
        rows.sumOf { it.filter { cell -> cell.isNotBlank() }.sumOf { cell -> cell.toInt() } }
}