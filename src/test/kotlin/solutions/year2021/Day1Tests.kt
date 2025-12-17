package solutions.year2021

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.DataFetcherMock

class Day1Tests {
    private var testInputData = """
        199
        200
        208
        210
        200
        207
        240
        269
        260
        263
    """.trimIndent()


    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day1(fetcher)
        val result = day.partOneResult()

        assertEquals(
            "7",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day1(fetcher)
        val result = day.partTwoResult()

        assertEquals(
            "5",
            result
        )
    }
}
