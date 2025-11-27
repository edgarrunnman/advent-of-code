package solutions.year2024

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock

class Day2Test {

    private var testInputData = """
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9
    """.trimIndent()

    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day2(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "2",
            result
        )
    }
    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day2(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "4",
            result
        )
    }
}