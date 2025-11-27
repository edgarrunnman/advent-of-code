package solutions.year2021

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2021.Day2

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

        Assert.assertEquals(
            "150",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day2(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "900",
            result
        )
    }
}
