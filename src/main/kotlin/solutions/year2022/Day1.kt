package solutions.year2022

import DataFetcher
import solutions.Solution

class Day1(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022 //specify year
    override val day: Int = 1 //specify day
    override lateinit var puzzleInput: List<String>
    init {
        getInputData()
    }

    //implement first solution
    override fun solution1(): String = "not rdy"

    //implement second solution
    override fun solution2(): String = "not rdy"
}