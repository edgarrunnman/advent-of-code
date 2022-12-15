package year2022

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2022.*
class Day15Tests {
    private var testInputData = """
Sensor at x=2, y=18: closest beacon is at x=-2, y=15
Sensor at x=9, y=16: closest beacon is at x=10, y=16
Sensor at x=13, y=2: closest beacon is at x=15, y=3
Sensor at x=12, y=14: closest beacon is at x=10, y=16
Sensor at x=10, y=20: closest beacon is at x=10, y=16
Sensor at x=14, y=17: closest beacon is at x=10, y=16
Sensor at x=8, y=7: closest beacon is at x=2, y=10
Sensor at x=2, y=0: closest beacon is at x=2, y=10
Sensor at x=0, y=11: closest beacon is at x=2, y=10
Sensor at x=20, y=14: closest beacon is at x=25, y=17
Sensor at x=17, y=20: closest beacon is at x=21, y=22
Sensor at x=16, y=7: closest beacon is at x=15, y=3
Sensor at x=14, y=3: closest beacon is at x=15, y=3
Sensor at x=20, y=1: closest beacon is at x=15, y=3
""".trimIndent() //Test data (from Advent of Code example)

    private var inputData = """
Sensor at x=1384790, y=3850432: closest beacon is at x=2674241, y=4192888
Sensor at x=2825953, y=288046: closest beacon is at x=2154954, y=-342775
Sensor at x=3553843, y=2822363: closest beacon is at x=3444765, y=2347460
Sensor at x=2495377, y=3130491: closest beacon is at x=2761496, y=2831113
Sensor at x=1329263, y=1778185: closest beacon is at x=2729595, y=2000000
Sensor at x=2882039, y=2206085: closest beacon is at x=2729595, y=2000000
Sensor at x=3903141, y=2510440: closest beacon is at x=4006219, y=3011198
Sensor at x=3403454, y=3996578: closest beacon is at x=3754119, y=4475047
Sensor at x=3630476, y=1048796: closest beacon is at x=3444765, y=2347460
Sensor at x=16252, y=2089672: closest beacon is at x=-276514, y=2995794
Sensor at x=428672, y=1150723: closest beacon is at x=-281319, y=668868
Sensor at x=2939101, y=3624676: closest beacon is at x=2674241, y=4192888
Sensor at x=3166958, y=2890076: closest beacon is at x=2761496, y=2831113
Sensor at x=3758241, y=3546895: closest beacon is at x=4006219, y=3011198
Sensor at x=218942, y=3011070: closest beacon is at x=-276514, y=2995794
Sensor at x=52656, y=3484635: closest beacon is at x=-276514, y=2995794
Sensor at x=2057106, y=405314: closest beacon is at x=2154954, y=-342775
Sensor at x=1966905, y=2495701: closest beacon is at x=2761496, y=2831113
Sensor at x=511976, y=2696731: closest beacon is at x=-276514, y=2995794
Sensor at x=3094465, y=2478570: closest beacon is at x=3444765, y=2347460
Sensor at x=806671, y=228252: closest beacon is at x=-281319, y=668868
Sensor at x=3011731, y=1976307: closest beacon is at x=2729595, y=2000000
    """.trimIndent()

private var testInputData2 = """
Sensor at x=16, y=7: closest beacon is at x=15, y=3
""".trimIndent() //Test data (from Advent of Code example)

    // 4559759 tooLow'
    // 5256609 wrong
    // 5256611 right
    // 5256612 tooHigh
    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day15(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "26", //Answer (from Advent of Code)
            result
        )
    }

    @Test
    fun partTwoTest() {
        val fetcher = DataFetcherMock(testInputData)
        val day = Day15(fetcher)
        val result = day.partTwoResult()

        Assert.assertEquals(
            "1", //Answer (from Advent of Code)
            result
        )
    }
}