package solutions.year2022

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.DataFetcherMock

class Day8Tests {
    private var testInputData = """
30373
25512
65332
33549
35390
    """.trimIndent() //Test data (from Advent of Code example)

    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day8(fetcher)
        val result = day.partOneResult()

        assertEquals(
            "21", //Answer (from Advent of Code)
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day8(fetcher)
        val result = day.partTwoResult()

        assertEquals(
            "8", //Answer (from Advent of Code)
            result
        )
    }
}