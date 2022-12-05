package solutions.year2021

import DataFetcher
import solutions.Solution

class Day3(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2021
    override val day: Int = 3
    override lateinit var inputAsList: List<String>
    override lateinit var input: String
    init { getInputData() }

    override fun partOneResult(): String =
        List(inputAsList.first().length) { n ->
            inputAsList.fold(0) { counter, reading -> if (reading[n] == '1') counter + 1 else counter }
        }
            .getEnergyConsumption(inputAsList.count()).toString()

    override fun partTwoResult(): String =
        getLifeSupportRating(inputAsList.dataFilterOxygen(0), inputAsList.dataFilterCO2(0)).toString()

    private fun List<Int>.getEnergyConsumption(maxCount: Int): Int {
        val gamaRate = String(this.map { if (it > maxCount / 2) '1' else '0' }.toCharArray()).toInt(2)
        val epsilonRate = String(this.map { if (it < maxCount / 2) '1' else '0' }.toCharArray()).toInt(2)
        return gamaRate * epsilonRate
    }

    private fun getLifeSupportRating(co2: List<String>, oxygen: List<String>): Int {
        val oxygenRate = oxygen.first().toInt(2)
        val co2Rate = co2.first().toInt(2)
        return oxygenRate * co2Rate
    }

    private fun List<String>.dataFilterCO2(startIndex: Int): List<String> {
        var count = 0
        val result: List<String>
        if (startIndex == this.first().count()) result = this
        else {
            this.forEach { if (it[startIndex] == '1') count += 1 }
            result =
                if (count == this.count() || count == 0)
                    this.dataFilterCO2(startIndex + 1)
                else if (count * 2 >= this.count())
                    this.filter { it[startIndex] == '1' }.dataFilterCO2(startIndex + 1)
                else
                    this.filter { it[startIndex] == '0' }.dataFilterCO2(startIndex + 1)
        }
        return result
    }

    private fun List<String>.dataFilterOxygen(startIndex: Int): List<String> {
        var count = 0
        val result: List<String>
        if (startIndex == this.first().count()) result = this
        else {
            this.forEach { if (it[startIndex] == '1') count += 1 }
            result =
                if (count == this.count() || count == 0)
                    this.dataFilterOxygen(startIndex + 1)
                else if (count * 2 < this.count())
                    this.filter { it[startIndex] == '1' }.dataFilterOxygen(startIndex + 1)
                else
                    this.filter { it[startIndex] == '0' }.dataFilterOxygen(startIndex + 1)
        }
        return result
    }
}