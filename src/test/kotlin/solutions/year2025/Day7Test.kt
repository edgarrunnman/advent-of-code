package solutions.year2025

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.DataFetcherMock

class Day7Test {

    private var testInputData = """
.......S.......
...............
.......^.......
...............
......^.^......
...............
.....^.^.^.....
...............
....^.^...^....
...............
...^.^...^.^...
...............
..^...^.....^..
...............
.^.^.^.^.^...^.
...............
    """.trimIndent()


    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day7(fetcher)
        val result = day.partOneResult()

        assertEquals(
            "21",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day7(fetcher)
        val result = day.partTwoResult()

        assertEquals(
            "40",
            result
        )
    }
}