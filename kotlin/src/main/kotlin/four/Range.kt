package four

data class Range(val from: Int, val to: Int) {
    private val intRange: IntRange by lazy { from..to }

    fun completelyEncloses(other: Range): EnclosesStatus {
        val intersect = intRange.intersect(other.intRange)
        return when (intersect) {
            intRange.toSet() -> EnclosesStatus.LEFT
            other.intRange.toSet() -> EnclosesStatus.RIGHT
            else -> EnclosesStatus.NEITHER
        }
    }

    fun partiallyEncloses(other: Range): Boolean {
        val intersect = intRange.intersect(other.intRange)
        return intersect.size > 0
    }

    companion object {
        private fun extractInt(groups: MatchGroupCollection?, groupName: String): Int? {
            return groups?.get(groupName)?.value?.toInt()
        }

        fun parse(input: String): Range? {
            val match = Regex("(?<first>\\d+)-(?<second>\\d+)").matchEntire(input)
            val groups = match?.groups
            val first = extractInt(groups, "first")
            val second = extractInt(groups, "second")
            return if (first != null && second != null) Range(first, second)
            else null
        }
    }
}