package solutions.year2021

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2021.Day3

class Day6Tests {
    private var testInputData = """
    """.trimIndent()

    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day3(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "1",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day3(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "1",
            result
        )
    }
}
