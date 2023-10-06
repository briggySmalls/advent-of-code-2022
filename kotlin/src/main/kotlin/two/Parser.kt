package two

object Parser {
    fun parse(input: String): List<Pair<Choice, Choice>> {
        val rows = input.split("\n")
        val pairs = rows.map { parseRow(it) { g -> parseGroups(g) } }
        return pairs.map { it!! }
    }

    fun parseWithOutcomes(input: String): List<Pair<Choice, Outcome>> {
        val rows = input.split("\n")
        val pairs = rows.map { parseRow(it) { g -> parseWithOutcomes(g) } }
        return pairs.map { it!! }
    }

    private fun <T>parseRow(input: String, f: (MatchGroupCollection) -> T): T? {
        val matchResult = Regex("(?<input>A|B|C) (?<answer>X|Y|Z)").matchEntire(input)
        return matchResult?.groups?.let{ f(it) }
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

    private fun parseWithOutcomes(groups: MatchGroupCollection): Pair<Choice, Outcome>? {
        val i = groups["input"]?.value?.let { Choice.fromInput(it) }
        val a = groups["answer"]?.value?.let { Outcome.fromAnswer(it) }

        return if (i != null && a != null) Pair(i, a)
        else {
            println("Unexpected values")
            null
        }
    }
}