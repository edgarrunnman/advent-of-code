package solutions.year2025

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock

class Day6Test {

    private var testInputData = """
        XXX
    """.trimIndent()


    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day6(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "XXX",
            result
        )
    }
    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day6(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "XXX",
            result
        )
    }
}