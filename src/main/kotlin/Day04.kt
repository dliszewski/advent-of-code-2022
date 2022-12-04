class Day04 {

    fun part1(input: List<String>): Int {
        return input
            .map { it.toRanges() }
            .count { (range1, range2) -> range1.includeRange(range2) }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.toRanges() }
            .count { (range1, range2) -> range1.overlapRange(range2) }
    }

    private fun String.toRanges(): Pair<IntRange, IntRange> {
        val ranges = split(",")
        return Pair(ranges[0].toRange(), ranges[1].toRange())
    }

    private fun IntRange.includeRange(other: IntRange): Boolean {
        return contains(other.first) && contains(other.last)
                || other.contains(this.first) && other.contains(this.last)
    }

    private fun IntRange.overlapRange(other: IntRange): Boolean {
        return contains(other.first) || contains(other.last)
                || other.contains(this.first) || other.contains(this.last)
    }

    private fun String.toRange(): IntRange {
        val range = split("-")
        val from = range[0].toInt()
        val to = range[1].toInt()
        return IntRange(from, to)
    }
}