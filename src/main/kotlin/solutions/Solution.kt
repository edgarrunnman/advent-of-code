package solutions
import DataFetcher

interface Solution {

    val year: Int
    val day: Int
    val dataFetcher: DataFetcher
    var inputAsList: List<String>
    var input: String
    fun getInputData(removeBlanks: Boolean = true){
        input = this.dataFetcher.getPuzzleInput(this.year, this.day)
        inputAsList = input.split("\n")
        if (removeBlanks) inputAsList = inputAsList.filter { it.isNotBlank() }
    }
    fun partOneResult(): String
    fun partTwoResult(): String
}