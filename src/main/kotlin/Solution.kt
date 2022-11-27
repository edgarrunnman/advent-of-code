import org.reflections.Reflections

interface Solution {
    fun solution1(): String
    fun solution2(): String

    companion object {
        fun getByYear(year: Int): List<Pair<String, Solution>> {
            val reflections = Reflections("year${year}")
            val classes = reflections.getSubTypesOf(Solution::class.java)
            return classes.map { it.name to (Class.forName(it.name).getDeclaredConstructor().newInstance() as Solution) }
        }
    }

}