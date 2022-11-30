package solutions
import DataFetcher

interface Solution {

    val year: Int
    val day: Int
    val dataFetcher: DataFetcher
    var puzzleInput: List<String>
    fun getInputData(){
        puzzleInput = this.dataFetcher.getPuzzleInput(this.year, this.day)
    }
    fun partOneResult(): String
    fun partTwoResult(): String
}