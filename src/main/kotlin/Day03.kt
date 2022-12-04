class Day03 {

    fun part1(input: List<String>): Int {
        return input
            .map { Rucksack(it) }
            .map { it.getCompartmentIntersection() }
            .map { it.first() }
            .sumOf { it.toPriorityScore() }
    }

    fun part2(input: List<String>): Int {
        return input
            .windowed(3, 3)
            .map { (elf1, elf2, elf3) -> Triple(Rucksack(elf1), Rucksack(elf2), Rucksack(elf3)) }
            .map { it.first.getCompartmentIntersectionFrom(it.second, it.third) }
            .map { it.first() }
            .sumOf { it.toPriorityScore() }
    }

    class Rucksack(val content: String) {

        fun getCompartmentIntersection(): Set<Char> {
            val splitIndex = if (content.length % 2 == 0) content.length / 2 else content.length / 2 + 1
            val first = content.take(splitIndex).toCharArray()
            val second = content.substring(splitIndex).toCharArray()
            return first.intersect(second.toList())
        }

        fun getCompartmentIntersectionFrom(other: Rucksack, another: Rucksack): Set<Char> {
            return content.toCharArray()
                .intersect(other.content.toCharArray().toList())
                .intersect(another.content.toCharArray().toList())
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