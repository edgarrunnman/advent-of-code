package solutions.year2025

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock

class Day4Test {

    private var testInputData = """
..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@.
    """.trimIndent()


    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day4(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "13",
            result
        )
    }
    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day4(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "43",
            result
        )
    }
}