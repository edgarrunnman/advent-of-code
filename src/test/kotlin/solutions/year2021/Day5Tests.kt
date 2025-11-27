package solutions.year2021

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2021.Day3
import solutions.year2021.Day5

class Day5Tests {
    private var testInputData = """
0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2
    """.trimIndent()

    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day5(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "5",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day5(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "12",
            result
        )
    }
}
