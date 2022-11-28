import org.reflections.Reflections
import java.time.Duration
import java.time.LocalDateTime


interface Solution {

    val year: Int
    val day: Int
    var puzzleInput: List<String>
    fun solution1(): String
    fun solution2(): String

    companion object {
        fun getInputData(year: Int, day: Int): List<String> =
            DataFetcherImp().getPuzzleInput(year, day)

        fun getByYear(year: Int): List<Solution> =
            Reflections("year${year}")
                .getSubTypesOf(Solution::class.java)
                .map {
                    Class.forName(it.name).getDeclaredConstructor().newInstance() as Solution
                }
                .sortedBy { it.day }
    }
}

open class SolutionBase
class TimeLogger() {
    private val startTime: LocalDateTime = LocalDateTime.now()
    fun getExecutionTime(): Long = Duration.between(startTime, LocalDateTime.now()).toMillis()

}