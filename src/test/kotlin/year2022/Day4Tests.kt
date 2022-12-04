package year2022

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2022.Day4

class Day4Tests {
    private var testInputData = """
2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8
""".trimIndent() //Test data (from Advent of Code example)

    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day4(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            2,
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day4(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            4,
            result
        )
    }
}