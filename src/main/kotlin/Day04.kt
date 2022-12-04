class Day04 {

    fun part1(input: List<String>): Int {
        return input
            .map { it.toRanges() }
            .count { (range1, range2) -> range1 includeRange range2 }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.toRanges() }
            .count { (range1, range2) -> range1 overlapRange range2 }
    }

    private fun String.toRanges(): Pair<IntRange, IntRange> {
        val (range1, range2) = split(",")
        return Pair(range1.toRange(), range2.toRange())
    }

    private infix fun IntRange.includeRange(other: IntRange): Boolean {
        return (first <= other.first && last >= other.last)
                || (other.first <= first && other.last >= last)
    }

    private infix fun IntRange.overlapRange(other: IntRange): Boolean {
        return (first <= other.last && other.first <= last)
                || (first <= other.last && other.first <= last)
    }

    private fun String.toRange(): IntRange {
        val (from, to) = split("-")
        return from.toInt()..to.toInt()
    }
}