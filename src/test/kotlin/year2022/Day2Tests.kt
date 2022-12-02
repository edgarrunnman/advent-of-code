package year2022

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2022.Day2

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

        Assert.assertEquals(
            "15",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day2(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "12",
            result
        )
    }
}