package three

data class Item(val char: Char) {
    val priority: Int by lazy { priorities.getValue(char) }

    companion object {
        val lowercaseRng = 'a'..'z'
        val uppercaseRng = 'A'..'Z'
        val allValidChars = lowercaseRng + uppercaseRng
        private val priorities: Map<Char, Int> = allValidChars.withIndex().associate { it.value to it.index + 1 }

        fun parse(char: Char): Item? =
            when (char) {
                in allValidChars -> Item(char)
                else -> null
            }
    }
}