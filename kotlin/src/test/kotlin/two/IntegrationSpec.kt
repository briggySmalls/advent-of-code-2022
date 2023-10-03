package two

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import utils.ResourceReader

class IntegrationSpec : WordSpec({
    val example = ResourceReader.read("/two/example.txt")
    val test = ResourceReader.read("/two/input-1.txt")

    fun parseAndSum(input: String): Int {
        val parsed = Parser.parse(input)
        val scores = parsed.map { (input, answer) -> answer.score(input) }
        return scores.sum()
    }

    "Example" should {
        "sum scores correctly" {
            parseAndSum(example) shouldBe 15
        }
    }

    "Test" should {
        "sum scores correctly" {
            parseAndSum(test) shouldBe 14827
        }
    }
})