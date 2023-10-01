package one

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class CalorieCounterSpec : WordSpec({
    "CalorieCounter.findMaxCalories" should {
        "work" {
            CalorieCounter.findMaxCalories("""
                1000
                2000
                3000

                4000

                5000
                6000

                7000
                8000
                9000

                10000
            """.trimIndent()) shouldBe 24000
        }
    }
})