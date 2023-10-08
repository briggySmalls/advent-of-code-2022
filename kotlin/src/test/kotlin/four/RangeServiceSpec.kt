package four

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import utils.ResourceReader

class RangeServiceSpec : WordSpec({
    val example = ResourceReader.read("/four/example.txt")
    val test = ResourceReader.read("/four/test.txt")

  "RangeServiceSpec" should {
      "count overlaps in example" {
          val ranges = RangeService.parse(example)
          RangeService.countOverlaps(ranges) shouldBe 2
      }

      "count overlaps in test" {
          val ranges = RangeService.parse(test)
          RangeService.countOverlaps(ranges) shouldBe 588
      }
  }
})