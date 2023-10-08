package one

import common.GenericParser

data class Bag(val calories: List<Int>) {
    fun totalCalories(): Int { return calories.sum() }

    companion object {
        fun parseBag(elfsString: String): Bag {
            val individualCaloriesStr = elfsString.split("\n")
            val individualCalories = individualCaloriesStr.mapNotNull { it.toIntOrNull() }
            return Bag(individualCalories)
        }
    }
}

data class Elves(val bags: List<Bag>) {
    fun topN(n: Int): Elves {
        return Elves(bags.sortedByDescending { it.totalCalories() }.take(n))
    }

    fun topNTotalCalories(n: Int): Int = topN(n).bags.sumOf { it.totalCalories() }

    companion object {
        fun parseBags(input: String): Elves {
            val rows = GenericParser.parseRows(input, { Bag.parseBag(it) }, "\n\n")
            return Elves(rows)
        }
    }
}

object CalorieCounter {
    fun findMaxCalories(input: String): Int {
        return topCalories(input, 1)
    }

    fun topCalories(input: String, n: Int): Int {
        val elves = Elves.parseBags(input)
        return elves.topNTotalCalories(n)
    }
}