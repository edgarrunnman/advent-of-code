package year2022

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2022.Day1

class Day1Tests {
    private var testInputData = """
       1000
       2000
       3000
       
       4000
       
       5000
       6000
       
       7000
       8000
       9000
       
       10000
    """.trimIndent()

    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day1(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            24000,
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day1(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            45000,
            result
        )
    }
}
