fun main() {
    fun mapToElvesBags(input: String) = input.split("\n\n")
        .map { elfBag -> elfBag.lines().sumOf { calories -> calories.toInt() } }

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

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))

    check(part1(input) == 69177)
    check(part2(input) == 207456)
}
