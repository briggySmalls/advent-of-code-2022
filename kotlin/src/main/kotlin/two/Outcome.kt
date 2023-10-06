package two

enum class Outcome(val score: Int, val answerCode: String) {
    WIN(6, "Z"), LOSS(0, "X"), DRAW(3, "Y");

    companion object {
        fun fromAnswer(str: String): Outcome? = values().firstOrNull { it.answerCode == str }
    }
}