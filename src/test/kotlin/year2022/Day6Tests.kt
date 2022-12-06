package year2022

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2022.Day5
import solutions.year2022.Day6

class Day6Tests {
    private var testInputData = """
bvwbjplbgvbhsrlpgdmjqwftvncz
nppdvjthqldpwncqszvftbrmjlhg
nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg
zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
    """.trimIndent() //Test data (from Advent of Code example)

    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day6(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "32", //Answer (from Advent of Code)
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day6(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "", //Answer (from Advent of Code)
            result
        )
    }
}