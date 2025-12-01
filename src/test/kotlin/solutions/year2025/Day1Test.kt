package solutions.year2025

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock

class Day1Test {

    private var testInputData = """
L68
L30
R48
L5
R60
L55
L1
L99
R14
L82
    """.trimIndent()

    private var myTestInputData = """
L68
L30
R48
L5
R60
L55
L1
L99
R14
L82
L510
R850
R230
L110
    """.trimIndent()

    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day1(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "3",
            result
        )
    }
    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(myTestInputData)
        val day = Day1(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "24",
            result
        )
    }
}