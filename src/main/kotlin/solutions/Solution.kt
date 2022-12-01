package solutions
import DataFetcher

interface Solution {

    val year: Int
    val day: Int
    val dataFetcher: DataFetcher
    var puzzleInput: List<String>
    fun getInputData(blanks: Boolean = false){
        puzzleInput = this.dataFetcher.getPuzzleInput(this.year, this.day)
        if (!blanks) puzzleInput = puzzleInput.filter { it.isNotBlank() }
    }
    fun partOneResult(): String
    fun partTwoResult(): String
}