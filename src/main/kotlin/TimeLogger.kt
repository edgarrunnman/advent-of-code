import java.time.Duration
import java.time.LocalDateTime

class TimeLogger() {
    private val startTime: LocalDateTime = LocalDateTime.now()
    fun getExecutionTime(): Long = Duration.between(startTime, LocalDateTime.now()).toMillis()
}