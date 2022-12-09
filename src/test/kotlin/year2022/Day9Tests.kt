package year2022

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2022.Day8
import solutions.year2022.Day9

class Day9Tests {
    private var testInputData = """
R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2
    """.trimIndent() //Test data (from Advent of Code example)

    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day9(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "13", //Answer (from Advent of Code)
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day9(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "24933642", //Answer (from Advent of Code)
            result
        )
    }
}