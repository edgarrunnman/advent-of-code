# Advent of code
## Instructions
* Create `.env` file in root folder and provide `AOC_TOKEN=` value. That is you private session token found in browser cookies ![image](https://user-images.githubusercontent.com/46990844/204498170-7311407e-ce8a-4338-a93c-72ddb6af405e.png)



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

class Day1Imp: Solution {
    override val year: Int = 2022 //specify year
    override val day: Int = 1 //specify day
    
    override var puzzleInput: List<String> = getInputData(this.year, this.day)

    //implement first solution
    override fun solution1(): String = "not rdy"
    
    //implement second solution
    override fun solution2(): String = "not rdy"
}
```
## Implementation example (AOC 2021 day 1)
```Kotlin
package year2021
import Solution
import Solution.Companion.getInputData

class Day1Imp : Solution {
    override val year: Int = 2021
    override val day: Int = 1
    
    override var puzzleInput: List<String> = getInputData(this.year, this.day)

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
