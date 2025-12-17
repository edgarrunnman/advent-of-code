package solutions.year2022

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.DataFetcherMock

class Day2Tests {
    private var testInputData = """
A Y
B X
C Z
""".trimIndent()

    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day2(fetcher)
        val result = day.partOneResult()

        assertEquals(
            "15",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day2(fetcher)
        val result = day.partTwoResult()

        assertEquals(
            "12",
            result
        )
    }
}