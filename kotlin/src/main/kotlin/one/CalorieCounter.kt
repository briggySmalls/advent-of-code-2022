package one

data class Bag(val calories: List<Int>) {
    fun totalCalories(): Int { return calories.sum() }
}

object CalorieCounter {
    fun findMaxCalories(input: String): Int {
        val bags = parseBags(input)
        return bags.maxOf { it.totalCalories() }
    }

    fun parseBags(input: String): List<Bag> {
        val strPerElf = input.split("\n\n")
        return strPerElf.map { parseBag(it) }
    }

    fun parseBag(elfsString: String): Bag {
        val individualCaloriesStr = elfsString.split("\n")
        val individualCalories = individualCaloriesStr.mapNotNull { it.toIntOrNull() }
        return Bag(individualCalories)
    }
}