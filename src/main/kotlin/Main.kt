fun main(args: Array<String>) {
    val solutions = Solution.getByYear(2021)
    solutions.forEach {
        val solutionTimer1 = TimeLogger()
        println("day ${it.first} first solution: ${it.second.solution1()} - time: ${solutionTimer1.getExecutionTime()} ms")
        val solutionTimer2 = TimeLogger()
        println("day ${it.first} second solution: ${it.second.solution2()} - time: ${solutionTimer2.getExecutionTime()} ms")
    }
}
