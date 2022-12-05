package year2022

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2022.Day5

class Day5Tests {
    private var testInputData = """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2       
    """.trimIndent() //Test data (from Advent of Code example)

    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day5(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "CMZ", //Answer (from Advent of Code)
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day5(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "MCD", //Answer (from Advent of Code)
            result
        )
    }
}