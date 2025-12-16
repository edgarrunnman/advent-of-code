package solutions.year2025

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock

class Day8Test {

    private var testInputData = """
162,817,812
57,618,57
906,360,560
592,479,940
352,342,300
466,668,158
542,29,236
431,825,988
739,650,466
52,470,668
216,146,977
819,987,18
117,168,530
805,96,715
346,949,466
970,615,88
941,993,340
862,61,35
984,92,344
425,690,689
    """.trimIndent()
//0 (162,817,812) and 19 (425,690,689) to circut1 (2)
//0 (162,817,812) and 7 (431,825,988) to circut1 (3)
//2 (906,360,560) and 13 (805,96,715) to cicut2 (2)
//7 (431,825,988) and 19 (425,690,689) nothing happens?
    // 5 4 2 2 1 1 1 1 1 1 1
    @Test
    fun partOneTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day8(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "40",
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day8(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "25272",
            result
        )
    }
}