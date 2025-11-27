package solutions.year2021

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2021.Day1

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

        Assert.assertEquals(
            "7",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day1(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "5",
            result
        )
    }
}
