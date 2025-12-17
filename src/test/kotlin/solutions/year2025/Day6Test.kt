package solutions.year2025

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.DataFetcherMock

class Day6Test {

    private var testInputData = """
123 328  51 64  
 45 64  387 23 
  6 98  215 314
*   +   *   +  
    """.trimIndent()


    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day6(fetcher)
        val result = day.partOneResult()

        assertEquals(
            "4277559",
            result
        )
    }
    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day6(fetcher)
        val result = day.partTwoResult()

        assertEquals(
            "3263827",
            result
        )
    }
}