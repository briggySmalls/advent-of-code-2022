package two

import io.kotest.core.spec.style.WordSpec
import io.kotest.data.blocking.forAll
import io.kotest.matchers.shouldBe
import utils.ResourceReader

class ChoiceSpec : WordSpec({
    "ChoiceSpec.score" should {
        "score draws correctly" {
            forAll<Choice> { c ->
                c.score(c) shouldBe c.score + 3
            }
        }
    }
})