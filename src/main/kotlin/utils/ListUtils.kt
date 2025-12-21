package utils

fun <T> List<T>.combinations(): List<List<T>> {
    val result = mutableListOf<List<T>>()

    fun backtrack(start: Int, current: MutableList<T>) {
        if (current.isNotEmpty()) {
            result += current.toList()
        }
        for (i in start until this.size) {
            current += this[i]
            backtrack(i + 1, current)
            current.removeAt(current.lastIndex)
        }
    }

    backtrack(0, mutableListOf())
    return result
}