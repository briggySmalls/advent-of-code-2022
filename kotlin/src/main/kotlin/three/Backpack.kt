package three

import common.GenericParser

data class Backpack(val items: List<Item>) {
    private val halves = items.withIndex().partition { it.index < items.size / 2 }

    val first = halves.first.map { it.value }
    val second = halves.second.map { it.value }
    val inBoth by lazy { first.intersect(second) }
    val prioritiesInBoth by lazy { inBoth.map { it.priority } }
    val sumOfPrioritiesInBoth by lazy { prioritiesInBoth.sum() }

    companion object {
        fun parse(itemsString: String): Backpack =
            Backpack(itemsString.map { c -> Item(c) })

        fun parseMultiple(totalString: String): List<Backpack> =
            GenericParser.parseRows(totalString, {parse(it)})

    }
}