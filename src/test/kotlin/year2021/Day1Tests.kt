package year2021

import DataFetcher
import org.junit.Assert
import org.junit.Test
import solutions.year2021.Day1

class Day1Tests {
    private var testInputData = listOf(
        "199",
        "200",
        "208",
        "210",
        "200",
        "207",
        "240",
        "269",
        "260",
        "263"
    )

    @Test
    fun solution1Test() {

        val fetcher = DataFetcherMoc(testInputData)
        val day = Day1(fetcher)
        val result = day.solution1()

        Assert.assertEquals(
            "7",
            result
        )
    }

    @Test
    fun solution2Test() {
        val fetcher = DataFetcherMoc(testInputData)
        val day = Day1(fetcher)
        val result = day.solution2()

        Assert.assertEquals(
            "5",
            result
        )
    }
}

class DataFetcherMoc(override var fetched: List<String>) : DataFetcher {
    override fun getPuzzleInput(year: Int, day: Int): List<String> = fetched
}
