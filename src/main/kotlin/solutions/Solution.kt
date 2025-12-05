package solutions

import DataFetcher

interface Solution {
    val year: Int
    val day: Int
    val dataFetcher: DataFetcher
    var inputAsList: List<String>
    var input: String

    fun getInputData(removeBlanks: Boolean = true, keepLineBreaks: Boolean = false) {
        val rawInput = this.dataFetcher.getPuzzleInput(this.year, this.day)
        if (keepLineBreaks)
            input = rawInput
        else
            input = rawInput.replace("\n", "")

        inputAsList = rawInput.split("\n")
        if (removeBlanks) inputAsList = inputAsList.filter { it.isNotBlank() }
    }

    fun partOneResult(): String
    fun partTwoResult(): String
}