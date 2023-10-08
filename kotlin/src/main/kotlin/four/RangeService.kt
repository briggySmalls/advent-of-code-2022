package four

import common.GenericParser

object RangeService {
    private fun parseRow(input: String): Pair<Range, Range> =
        input.split(",").map { Range.parse(it)!! }.zipWithNext().single()

    fun parse(input: String): List<Pair<Range, Range>> =
        GenericParser.parseRows(input, { parseRow(it) })

    fun countOverlaps(ranges: List<Pair<Range, Range>>): Int {
        val encloseStatuses = ranges.map { it.first.encloses(it.second) }
        return encloseStatuses.count { it != EnclosesStatus.NEITHER }
    }
}