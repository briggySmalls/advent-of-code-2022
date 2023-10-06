package two

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import utils.ResourceReader

class IntegrationSpec : WordSpec({
    val example = ResourceReader.read("/two/example.txt")
    val test = ResourceReader.read("/two/input-1.txt")

    fun sumScores(input: String): Int {
        val parsed = Parser.parse(input)
        val scores = parsed.map { (input, answer) -> answer.score(input) }
        return scores.sum()
    }

    fun chooseAndSumScores(input: String): Int {
        val parsed = Parser.parseWithOutcomes(input)
        val choices = parsed.map { (input, outcome) ->
            val myChoice = Choice.findChoiceForOutcome(input, outcome)
            Pair(input, myChoice)
        }
        val scores = choices.map { (input, answer) ->
            answer.score(input)
        }
        return scores.sum()
    }

    "Example" should {
        "sum scores correctly" {
            sumScores(example) shouldBe 15
        }

        "choose based on outcome correctly" {
            chooseAndSumScores(example) shouldBe 12
        }
    }

    "Test" should {
        "sum scores correctly" {
            sumScores(test) shouldBe 14827
        }

        "choose based on outcome correctly" {
            chooseAndSumScores(test) shouldBe 13889
        }
    }
})