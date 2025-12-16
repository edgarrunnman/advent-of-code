package solutions.year2025

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock

class Day9Test {

    private var testInputData = """
7,1
11,1
11,7
9,7
9,5
2,5
2,3
7,3
    """.trimIndent()
    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day9(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "50",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day9(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "",
            result
        )
    }
}