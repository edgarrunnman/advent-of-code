package year2022

import DataFetcher
import org.junit.Assert
import org.junit.Test
import solutions.year2021.Day1

class Day1Tests {
    private var testInputData = listOf(
    ""
        //Test data (from Advent of Code example)
    )

    @Test
    fun solution1Test() {

        val fetcher = DataFetcherMoc(testInputData)
        val day = Day1(fetcher)
        val result = day.solution1()

        Assert.assertEquals(
            "", //Answer (from Advent of Code)
            result
        )
    }

    @Test
    fun solution2Test() {
        val fetcher = DataFetcherMoc(testInputData)
        val day = Day1(fetcher)
        val result = day.solution2()

        Assert.assertEquals(
            "", //Answer (from Advent of Code)
            result
        )
    }
}

class DataFetcherMoc(override var fetched: List<String>) : DataFetcher {
    override fun getPuzzleInput(year: Int, day: Int): List<String> = fetched
}
