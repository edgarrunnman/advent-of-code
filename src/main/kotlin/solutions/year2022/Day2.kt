package solutions.year2022

import DataFetcher
import solutions.Solution

class Day2(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 2
    override lateinit var inputAsList: List<String>
    override lateinit var input: String
    init {
        getInputData()
    }

    //implement first solution
    override fun partOneResult(): String = "not rdy"

    //implement second solution
    override fun partTwoResult(): String = "not rdy"
}