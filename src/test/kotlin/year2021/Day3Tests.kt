package year2021

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2021.Day3

class Day3Tests {
    private var testInputData = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010",
    )

    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day3(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "198",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day3(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "230",
            result
        )
    }
}
