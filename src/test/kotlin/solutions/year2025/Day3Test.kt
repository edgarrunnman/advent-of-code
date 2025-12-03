package solutions.year2025

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock

class Day3Test {

    private var testInputData = """
987654321111111
811111111111119
234234234234278
818181911112111
    """.trimIndent()


    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day3(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "357",
            result
        )
    }
    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day3(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "3121910778619",
            result
        )
    }
}