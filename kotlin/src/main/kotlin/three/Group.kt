package three

data class Group(val backpacks: List<Backpack>) {
    fun badge(): Item? {
        val inAll = backpacks.map{ it.items.toSet() }.reduce { a, b -> a.intersect(b) }
        return inAll.firstOrNull()
    }

    companion object {
        fun fromBackpacks(backpacks: List<Backpack>): List<Group> =
            backpacks.chunked(3).map { Group(it) }
    }
}