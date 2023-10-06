package two

enum class Choice(val inputCode: String, val answerCode: String, val score: Int) {
    ROCK("A", "X", 1) {
        override val beats: Choice get() = SCISSORS
        override val loses: Choice get() = PAPER
    },
    PAPER("B", "Y", 2) {
        override val beats: Choice get() = ROCK
        override val loses: Choice get() = SCISSORS
    },
    SCISSORS("C", "Z", 3) {
        override val beats: Choice get() = PAPER
        override val loses: Choice get() = ROCK
    };

    fun score(other: Choice): Int = outcome(other).score + this.score

    private fun outcome(other: Choice): Outcome =
        if (this == other) Outcome.DRAW
        else if (this.beats == other) Outcome.WIN
        else Outcome.LOSS

    abstract val beats: Choice
    abstract val loses: Choice

    companion object {
        fun fromInput(str: String): Choice? = values().firstOrNull { it.inputCode == str }
        fun fromAnswer(str: String): Choice? = values().firstOrNull { it.answerCode == str }
        fun findChoiceForOutcome(opponent: Choice, outcome: Outcome): Choice =
            when (outcome) {
                Outcome.DRAW -> opponent
                Outcome.WIN -> opponent.loses
                Outcome.LOSS -> opponent.beats
            }
    }
}