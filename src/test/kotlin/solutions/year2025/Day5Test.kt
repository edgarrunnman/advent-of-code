package solutions.year2025

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.DataFetcherMock

class Day5Test {

    private var testInputData = """
3-5
10-14
16-20
12-18

1
5
8
11
17
32
    """.trimIndent()

    private var testInputData2 = """
3-5
10-14
16-20
12-18
20-30
100-200
150-250
90-110
130-170
80-260
260-265
75-81
1100-1800
1000-2000
2002-2010
3-2010

1
5
8
11
17
32
    """.trimIndent()

    private var testInputData3 = """
100-499
200-499
""".trimIndent() to
            (400).toString()
//    (2000)
        .toString()


    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day5(fetcher)
        val result = day.partOneResult()

        assertEquals(
            "3",
            result
        )
    }
    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData3.first)
        val day = Day5(fetcher)
        val result = day.partTwoResult()

        assertEquals(
//            (125 + 50 + 10 + 20 + 5 + 5 + 1001 + 9).toString(),
            testInputData3.second,
            result
        )
    }
}