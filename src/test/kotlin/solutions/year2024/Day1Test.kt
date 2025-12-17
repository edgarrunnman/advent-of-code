package solutions.year2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.DataFetcherMock

class Day1Test {

    private var testInputData = """
    3   4
    4   3
    2   5
    1   3
    3   9
    3   3
    """.trimIndent()

    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day1(fetcher)
        val result = day.partOneResult()

        assertEquals(
            "11",
            result
        )
    }
    @Test
    fun partTwoTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day1(fetcher)
        val result = day.partTwoResult()

        assertEquals(
            "31",
            result
        )
    }
}