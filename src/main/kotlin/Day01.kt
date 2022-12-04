class Day01 {

    fun part1(input: String): Int {
        val elvesBags = mapToElvesBags(input)
        return elvesBags.max()
    }

    fun part2(input: String): Int {
        return mapToElvesBags(input)
            .sorted()
            .takeLast(3)
            .sum()
    }

    private fun mapToElvesBags(input: String) = input.split("\n\n")
        .map { elfBag -> elfBag.lines().sumOf { calories -> calories.toInt() } }
}
