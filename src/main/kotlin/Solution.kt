import jdk.jfr.Timespan
import org.reflections.Reflections
import java.time.Duration
import java.time.LocalDateTime


interface Solution {
    fun solution1(): String
    fun solution2(): String

    companion object {
        fun getByYear(year: Int): List<Pair<String, Solution>> =
            Reflections("year${year}")
                .getSubTypesOf(Solution::class.java)
                .map { it.name to (Class.forName(it.name).getDeclaredConstructor().newInstance() as Solution) }
    }
}

class TimeLogger() {
    private val startTime: LocalDateTime = LocalDateTime.now()
    fun getExecutionTime(): Long = Duration.between(startTime, LocalDateTime.now()).toMillis()

}