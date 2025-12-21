package solutions.year2025

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.DataFetcherMock

class Day10Test {

    private var testInputData = """
[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
    """.trimIndent()
    //1 2 3  : 6 -> 1 5 2 -> 15
    //2 4 6 6 : 6 -> 5 3 12 -> 15
    //1 3 4 5 6 : 6 -> 1 2 3 10 12 -> 15
    // 5 6 : 6 -> 10 12 -> 15

    // 3 4 5 : 2 -> 17 28 15 -> 31

    //2 3 : 29 -> 38 59 -> 63

    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day10(fetcher)
        val result = day.partOneResult()

        assertEquals(
            "7",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day10(fetcher)
        val result = day.partTwoResult()

        assertEquals(
            "24",
            result
        )
    }
}

//.##. - 6
//final - 15
//[3] - 1
//[1, 3] - 5
//[2] - 2
//[2, 3] - 3
//[0, 2] - 10
//[0, 1] - 12
//
//...#. - 2
//final - 31
//[0, 2, 3, 4] - 23
//[2, 3] - 6
//[0, 4] - 17
//[0, 1, 2] - 28
//[1, 2, 3, 4] - 15
//
//.###.# - 29
//final - 63
//[0, 1, 2, 3, 4] - 62
//[0, 3, 4] - 38
//[0, 1, 2, 4, 5] - 59
//[1, 2] - 24
