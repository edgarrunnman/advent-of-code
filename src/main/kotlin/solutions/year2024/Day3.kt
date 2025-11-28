package solutions.year2024

import DataFetcher
import solutions.Solution

class Day3(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2024
    override val day: Int = 3
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        input.foldIndexed(emptyList<Int>()) { n, acc, _ ->
            if (input.length > (n + 5) && input.substring(n..n + 3) == "mul(") {
                var pos = n + 4
                var number1 = ""
                var number2 = ""
                while (number1.length < 4) {
                    if (input[pos] in '0'..'9') {
                        number1 += input[pos]
                        pos++
                        continue
                    }
                    break
                }
                if (input[pos] == ',' && number1.isNotEmpty()) {
                    pos++
                    while (number2.length < 4) {
                        if (input[pos] in '0'..'9') {
                            number2 += input[pos]
                            pos++
                            continue
                        }
                        break
                    }
                }
                if (number1.isNotEmpty() && number2.isNotEmpty() && input[pos] == ')') {
                    acc + (number1.toInt() * number2.toInt())
                } else acc
            } else acc
        }.sum().toString()

    override fun partTwoResult(): String =
        input.foldIndexed(emptyList<Int>() to true) { n, acc, _ ->
            if (input.length > (n + 5) && input.substring(n..n + 3) == "do()") {
                acc.first to true
            }
            else if (input.length > (n + 6) && input.substring(n..n + 6) == "don't()") {
                acc.first to false
            }
            else if (input.length > (n + 5) && input.substring(n..n + 3) == "mul(") {
                var pos = n + 4
                var number1 = ""
                var number2 = ""
                while (number1.length < 4) {
                    if (input[pos] in '0'..'9') {
                        number1 += input[pos]
                        pos++
                        continue
                    }
                    break
                }
                if (input[pos] == ',' && number1.isNotEmpty()) {
                    pos++
                    while (number2.length < 4) {
                        if (input[pos] in '0'..'9') {
                            number2 += input[pos]
                            pos++
                            continue
                        }
                        break
                    }
                }
                if (number1.isNotEmpty() && number2.isNotEmpty() && input[pos] == ')' && acc.second) {
                    acc.first + (number1.toInt() * number2.toInt()) to true
                } else acc
            } else acc
        }.first.sum().toString()

}
