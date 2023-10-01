import one.CalorieCounter

fun main(args: Array<String>) {
    val i = object {}::class.java.getResource("/one/calories.txt").readText()

    val result = CalorieCounter.findMaxCalories(i)

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Elf with max calories: $result")
}