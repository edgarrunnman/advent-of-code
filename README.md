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
  
  //use raw input or input as List of input lines
  override lateinit var inputAsList: List<String> 
  override lateinit var input: String

  init {
    getInputData()
  }
  //implement first solution
  override fun partOneResult(): String = ""

  //implement second solution
  override fun partTwoResult(): String = ""
}
```

## Implementation example (AOC 2021 day 1)

```Kotlin
package solutions.year2021

import DataFetcher
import solutions.Solution

class Day1(override var dataFetcher: DataFetcher) : Solution {
  override val year: Int = 2021
  override val day: Int = 1
  override lateinit var inputAsList: List<String>
  override lateinit var input: String

  init {
    getInputData()
  }

  override fun partOneResult(): String =
    inputAsList.map { it.toInt() }.incrementCounter().toString()

  override fun partTwoResult(): String =
    inputAsList.map { it.toInt() }.sumByThree().incrementCounter().toString()

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

import org.junit.Assert
import org.junit.Test
import shared.DataFetcherMock

class Day1Tests {
  private var testInputData = """
       
    """.trimIndent() //Test data (from Advent of Code example)

  @Test
  fun partOneTest() {

    val fetcher = DataFetcherMock(testInputData)
    val day = MySolution(fetcher)
    val result = day.partOneResult()

    Assert.assertEquals(
      "", //Answer (from Advent of Code)
      result
    )
  }

  @Test
  fun partTwoTest() {
    val fetcher = DataFetcherMock(testInputData)
    val day = MySolution(fetcher)
    val result = day.partTwoResult()

    Assert.assertEquals(
      "", //Answer (from Advent of Code)
      result
    )
  }
}
```

