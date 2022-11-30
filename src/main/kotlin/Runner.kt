import org.reflections.Reflections
import solutions.Solution

interface Runner {
    val solutions: List<Solution>
    fun runAll()
    fun runByDay(day: Int)
}

class RunnerImp(private val year: Int) : Runner {
    override val solutions: List<Solution> = getByYear(year)
    private fun getByYear(year: Int): List<Solution> =
        Reflections("solutions.year${year}")
            .getSubTypesOf(Solution::class.java)
            .map {
                val cArgs = arrayOf<Class<*>?>(DataFetcher::class.java)
                Class.forName(it.name).getConstructor(*cArgs).newInstance(DataFetcherImp()) as Solution
            }
            .sortedBy { it.day }

    override fun runAll() {
        this.solutions.forEach {
            val solutionTimer1 = TimeLogger()
            println("day ${it.day} 1#: ${it.solution1()} - time: ${solutionTimer1.getExecutionTime()} ms")
            val solutionTimer2 = TimeLogger()
            println("day ${it.day} 2#: ${it.solution2()} - time: ${solutionTimer2.getExecutionTime()} ms")
        }
    }

    override fun runByDay(day: Int) {
        this.solutions.first { it.day == day }.let {

            val solutionTimer1 = TimeLogger()
            println("day ${it.day} 1#: ${it.solution1()} - time: ${solutionTimer1.getExecutionTime()} ms")
            val solutionTimer2 = TimeLogger()
            println("day ${it.day} 2#: ${it.solution2()} - time: ${solutionTimer2.getExecutionTime()} ms")
        }
    }
}