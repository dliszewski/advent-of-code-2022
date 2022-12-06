class Day06 {

    fun part1(input: String): Int {
        return findStartOfPacketIndex(input, 4)
    }

    private fun findStartOfPacketIndex(input: String, uniqueChars: Int): Int {
        val windows = input.windowed(uniqueChars)
        val indexOfFirst = windows.indexOfFirst { it.chars().distinct().count().toInt() == uniqueChars }
        return if (indexOfFirst != -1) {
            indexOfFirst + uniqueChars
        } else {
            indexOfFirst
        }
    }

    fun part2(input: String): Int {
        return findStartOfPacketIndex(input, 14)
    }

}