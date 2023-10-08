package four

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import utils.ResourceReader

class RangeServiceSpec : WordSpec({
    val exampleRanges = RangeService.parse(ResourceReader.read("/four/example.txt"))
    val testRanges = RangeService.parse(ResourceReader.read("/four/test.txt"))

  "RangeServiceSpec" should {
      "count complete enclosures in example" {
          RangeService.countCompletelyEncloses(exampleRanges) shouldBe 2
      }

      "count complete enclosures in test" {
          RangeService.countCompletelyEncloses(testRanges) shouldBe 588
      }

      "count partial enclosures in example" {
          RangeService.countPartiallyEncloses(exampleRanges) shouldBe 4
      }

      "count partial enclosures in test" {
          RangeService.countPartiallyEncloses(testRanges) shouldBe 911
      }
  }
})