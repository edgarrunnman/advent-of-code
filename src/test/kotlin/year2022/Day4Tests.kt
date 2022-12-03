package year2022

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2021.Day1

class Day4Tests {
    private var testInputData = """
vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw 
    """.trimIndent() //Test data (from Advent of Code example)

    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day1(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "157", //Answer (from Advent of Code)
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day1(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "", //Answer (from Advent of Code)
            result
        )
    }
}