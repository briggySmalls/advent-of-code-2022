package one

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import utils.ResourceReader

class CalorieCounterSpec : WordSpec({
    val example = ResourceReader.read("/one/calories-example.txt")
    val test = ResourceReader.read("/one/calories.txt")

    "CalorieCounter.findMaxCalories" should {
        "work for example" {
            CalorieCounter.findMaxCalories(example) shouldBe 24000
        }

        "work for challenge" {
            val result = CalorieCounter.findMaxCalories(test)
            result shouldBe 71924
        }
    }

    "CalorieCounter.topNCalories" should  {
        "work for example" {
            val result = CalorieCounter.topCalories(example, 3)
            result shouldBe 45000
        }

        "work for challenge" {
            val result = CalorieCounter.topCalories(test, 3)
            result shouldBe 210406
        }
    }
})