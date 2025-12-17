package solutions.year2022

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.DataFetcherMock
import solutions.year2022.*

class Day14Tests {
    private var testInputData = """
498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9
    """.trimIndent() //Test data (from Advent of Code example)

    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day14(fetcher)
        val result = day.partOneResult()

        assertEquals(
            "24", //Answer (from Advent of Code)
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day14(fetcher)
        val result = day.partTwoResult()

        assertEquals(
            "93", //Answer (from Advent of Code)
            result
        )
    }
}