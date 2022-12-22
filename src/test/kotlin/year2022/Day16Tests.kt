package year2022

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock
import solutions.year2022.Day15
import solutions.year2022.Day16

class Day16Tests {
    private var testInputData = """
Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
Valve BB has flow rate=13; tunnels lead to valves CC, AA
Valve CC has flow rate=2; tunnels lead to valves DD, BB
Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
Valve EE has flow rate=3; tunnels lead to valves FF, DD
Valve FF has flow rate=0; tunnels lead to valves EE, GG
Valve GG has flow rate=0; tunnels lead to valves FF, HH
Valve HH has flow rate=22; tunnel leads to valve GG
Valve II has flow rate=0; tunnels lead to valves AA, JJ
Valve JJ has flow rate=21; tunnel leads to valve II
""".trimIndent() //Test data (from Advent of Code example)


    @Test
    fun partOneTest() {

        val fetcher = DataFetcherMock(testInputData)
        val day = Day16(fetcher)
        val result = day.partOneResult()

        Assert.assertEquals(
            "1651", //Answer (from Advent of Code)
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