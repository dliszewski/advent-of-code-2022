class Day03 {

    fun part1(input: List<String>): Int {
        return input
            .map { Rucksack(it) }
            .flatMap { it.getCompartmentIntersection() }
            .sumOf { it.toPriorityScore() }
    }

    fun part2(input: List<String>): Int {
        return input
            .chunked(3)
            .map { (elf1, elf2, elf3) -> Triple(Rucksack(elf1), Rucksack(elf2), Rucksack(elf3)) }
            .map { it.first.getCompartmentIntersectionFrom(it.second, it.third).single() }
            .sumOf { it.toPriorityScore() }
    }

    class Rucksack(private val content: String) {

        fun getCompartmentIntersection(): Set<Char> {
            val splitIndex = if (content.length % 2 == 0) content.length / 2 else content.length / 2 + 1
            val first = content.take(splitIndex).toSet()
            val second = content.substring(splitIndex).toSet()
            return first.intersect(second)
        }

        fun getCompartmentIntersectionFrom(other: Rucksack, another: Rucksack): Set<Char> {
            return content.toSet()
                .intersect(other.content.toSet())
                .intersect(another.content.toSet())
        }
    }

    private fun Char.toPriorityScore(): Int {
        return when {
            //  Lowercase item types a through z have priorities 1 through 26.
            isLowerCase() -> this - 'a' + 1
            // Uppercase item types A through Z have priorities 27 through 52.
            isUpperCase() -> this - 'A' + 27
            else -> error("Wrong input")
        }
    }
}