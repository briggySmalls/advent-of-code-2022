package two

object Parser {
    fun parse(input: String): List<Pair<Choice, Choice>> {
        val rows = input.split("\n")
        val pairs = rows.map { parseRow(it) }
        return pairs.map { it!! }
    }

    private fun parseRow(input: String): Pair<Choice, Choice>? {
        val matchResult = Regex("(?<input>A|B|C) (?<answer>X|Y|Z)").matchEntire(input)
        return matchResult?.groups?.let{ parseGroups(it) }
    }

    private fun parseGroups(groups: MatchGroupCollection): Pair<Choice, Choice>? {
        val i = groups["input"]?.value?.let { Choice.fromInput(it) }
        val a = groups["answer"]?.value?.let { Choice.fromAnswer(it) }

        return if (i != null && a != null) Pair(i, a)
            else {
                println("Unexpected values")
                null
        }
    }
}