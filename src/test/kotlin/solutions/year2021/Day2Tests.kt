package solutions.year2021

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.DataFetcherMock

class Day2Tests {
    private var testInputData = """
        forward 5
        down 5
        forward 8
        up 3
        down 8
        forward 2
    """.trimIndent()

    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day2(fetcher)
        val result = day.partOneResult()

        assertEquals(
            "150",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day2(fetcher)
        val result = day.partTwoResult()

        assertEquals(
            "900",
            result
        )
    }
}
