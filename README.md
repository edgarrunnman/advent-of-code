# Advent of code

## Instructions

* Create `.env` file in root folder and provide `AOC_TOKEN=` value. That is you private session token found in browser
  cookies

  ![image](https://user-images.githubusercontent.com/46990844/204498170-7311407e-ce8a-4338-a93c-72ddb6af405e.png)
* Implement Solution abstraction and save implementations within corresponding package `year2021, year2022`
* Provide year and day values
* Use attribute `puzzleInput: List<String>` as puzzle input data
* Implement functions `solution1` and `solution2`
* Run application

## Implementation template

```Kotlin
package year2022
import Solution
import Solution.Companion.getInputData

class MySolution(override var dataFetcher: DataFetcher) : Solution {
  override val year: Int = 2021 //specify year
  override val day: Int = 4 //specify day
  override lateinit var puzzleInput: List<String>

  init {
    getInputData()
  }
  //implement first solution
  override fun solution1(): String = "not rdy"

  //implement second solution
  override fun solution2(): String = "not rdy"
}
```

## Implementation example (AOC 2021 day 1)

```Kotlin
package year2021

import DataFetcher
import Solution

class Day1(override var dataFetcher: DataFetcher) : Solution {
  override val year: Int = 2021
  override val day: Int = 1
  override lateinit var puzzleInput: List<String>

  init {
    getInputData()
  }

  override fun solution1(): String =
    puzzleInput.map { it.toInt() }.incrementCounter().toString()

  override fun solution2(): String =
    puzzleInput.map { it.toInt() }.sumByThree().incrementCounter().toString()

  private fun List<Int>.sumByThree(): List<Int> =
    (1..this.size - 3)
      .fold<Int, List<Int>>(mutableListOf(0)) { sumsOfTree, n ->
        sumsOfTree.plus(this.slice(n..n + 2).sum())
      }

  private fun List<Int>.incrementCounter(): Int =
    this.slice(1 until this.size)
      .foldIndexed(0) { n, count, reading ->
        if (this[n] < reading) count + 1 else count
      }
}
```

## Unit tests template
```Kotlin
package year2022

import DataFetcher
import org.junit.Assert
import org.junit.Test
import solutions.year2021.Day1

class Day1Tests {
  private var testInputData = listOf(
    "" //Test data (from Advent of Code example)
  )

  @Test
  fun solution1Test() {
    val fetcher = DataFetcherMoc(testInputData)
    val day = Day1(fetcher) //Your solution class
    val result = day.solution1()
    Assert.assertEquals(
      "", //Answer (from Advent of Code)
      result
    )
  }

  @Test
  fun solution2Test() {
    val fetcher = DataFetcherMoc(testInputData)
    val day = Day1(fetcher) //Your solution class
    val result = day.solution2()
    Assert.assertEquals(
      "", //Answer (from Advent of Code)
      result
    )
  }
}

class DataFetcherMoc(override var fetched: List<String>) : DataFetcher {
  override fun getPuzzleInput(year: Int, day: Int): List<String> = fetched
}
```

