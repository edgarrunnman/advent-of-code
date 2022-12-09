package year2022

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2022.*

class Day12Tests {
    private var testInputData = """

    """.trimIndent() //Test data (from Advent of Code example)

    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day12(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "95437", //Answer (from Advent of Code)
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day12(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "24933642", //Answer (from Advent of Code)
            result
        )
    }
}