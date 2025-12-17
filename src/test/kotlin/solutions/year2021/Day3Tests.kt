package solutions.year2021

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.DataFetcherMock

class Day3Tests {
    private var testInputData = """
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010
    """.trimIndent()

    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day3(fetcher)
        val result = day.partOneResult()

        assertEquals(
            "198",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day3(fetcher)
        val result = day.partTwoResult()

        assertEquals(
            "230",
            result
        )
    }
}
