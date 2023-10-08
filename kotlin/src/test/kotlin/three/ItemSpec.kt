package three

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.char
import io.kotest.property.checkAll
import three.ThreeTestData.lowercase
import three.ThreeTestData.uppercase

class ItemSpec : WordSpec({
    "Item#parse" should {
        "correctly parse valid chars" {
            checkAll(Arb.char(lowercase, uppercase)) { c ->
                Item.parse(c) shouldNotBe(null)
            }
        }
    }

    "Item.priority" should {
        "be in correct range" {
            checkAll(Arb.char(lowercase)) { c ->
                Item.parse(c)?.priority?.let { it shouldBeInRange(1..26) }
            }
        }
    }
})