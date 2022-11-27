fun main(args: Array<String>) {
    val solutions = Solution.getByYear(2021)
    solutions.forEach {
        println("day ${it.first} first solution: ${it.second.solution1()}")
        println("day ${it.first} second solution: ${it.second.solution2()}")
    }
}
