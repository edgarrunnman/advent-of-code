package solutions.year2025

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock

class Day7Test {

    private var testInputData = """
        XXX
    """.trimIndent()


    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day7(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "xxx",
            result
        )
    }
    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day7(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "xxx",
            result
        )
    }
}