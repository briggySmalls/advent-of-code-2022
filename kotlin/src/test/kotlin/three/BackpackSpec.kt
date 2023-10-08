package three

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.Codepoint
import io.kotest.property.arbitrary.charArray
import io.kotest.property.arbitrary.of
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import io.kotest.property.forAll
import three.Item.Companion.allValidChars
import three.ThreeTestData.lowercase
import three.ThreeTestData.uppercase
import utils.ResourceReader

class BackpackSpec : WordSpec({
    val validCodepoints = Arb.of(allValidChars.map { Codepoint(it.code) })
    val example = ResourceReader.read("/three/example.txt")
    val test = ResourceReader.read("/three/test.txt")

    "Backpack" should {
        "split into two pockets" {
            val b = Backpack.parse("abcdefgh")
            b.first shouldBe(listOf(Item('a'), Item('b'), Item('c'), Item('d')))
            b.second shouldBe(listOf(Item('e'), Item('f'), Item('g'), Item('h')))
        }

        "pockets always sum together" {
            checkAll(Arb.string(0, 100, validCodepoints)) { i ->
                val b = Backpack.parse(i)
                val merged = b.first + b.second
                val expected = i.map { Item(it )}
                merged shouldBe(expected)
            }
        }

        "match example intersection" {
            val bs = Backpack.parseMultiple(example)
            val inBoth = bs.map { it.inBoth }
            val expected = listOf(
                setOf('p'),
                setOf('L'),
                setOf('P'),
                setOf('v'),
                setOf('t'),
                setOf('s')
            ).map { it.map{ Item(it) } }
            inBoth shouldBe(expected)
        }

        "match example priority" {
            val bs = Backpack.parseMultiple(example)
            val priorities = bs.map { it.prioritiesInBoth }
            val expected = listOf(
                setOf(16),
                setOf(38),
                setOf(42),
                setOf(22),
                setOf(20),
                setOf(19)
            )
            priorities shouldBe(expected)
        }

        "match priority sum" {
            val bs = Backpack.parseMultiple(example)
            val priorities = bs.map { it.sumOfPrioritiesInBoth }.sum()
            priorities shouldBe(157)
        }

        "total in test" {
            val bs = Backpack.parseMultiple(test)
            val sum = bs.map{ it.sumOfPrioritiesInBoth }.sum()
            sum shouldBe 7980
        }
    }

    "Group" should {
        "from example groups" {
            val bs = Backpack.parseMultiple(example)
            val gs = Group.fromBackpacks(bs)
            val badges = gs.map { it.badge() }.filterNotNull()
            val priorities = badges.map {it.priority}
            priorities.sum() shouldBe 70
        }

        "from test groups" {
            val bs = Backpack.parseMultiple(test)
            val gs = Group.fromBackpacks(bs)
            val badges = gs.map { it.badge() }.filterNotNull()
            val priorities = badges.map {it.priority}
            priorities.sum() shouldBe 2881
        }
    }
})