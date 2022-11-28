fun main(args: Array<String>) {
    val solutions = Solution.getByYear(2021)
    solutions.forEach {
        val solution = it
        val solutionTimer1 = TimeLogger()
        println("day ${it.day} 1#: ${it.solution1()} - time: ${solutionTimer1.getExecutionTime()} ms")
        val solutionTimer2 = TimeLogger()
        println("day ${it.day} 2#: ${it.solution2()} - time: ${solutionTimer2.getExecutionTime()} ms")
    }
}
