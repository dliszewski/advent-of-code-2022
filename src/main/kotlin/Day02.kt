fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.split(" ") }
            .map { (opponentCode, myCode) -> HandShape.fromOpponentCode(opponentCode) to HandShape.fromElfCode(myCode) }
            .sumOf { (opponentHandShape, myHandShape) -> HandScorer.score(opponentHandShape, myHandShape) }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split(" ") }
            .map { (opponentCode, command) -> HandShape.fromOpponentCode(opponentCode) to command }
            .sumOf { (opponentHandShape, command) ->
                HandScorer.score(
                    opponentHandShape,
                    HandShape.decodeHand(opponentHandShape, command)
                )
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputLines("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInputLines("Day02")
    println(part1(input))
    println(part2(input))

    check(part1(input) == 11873)
    check(part2(input) == 12014)
}

object HandScorer {

    fun score(opponentHand: HandShape, myHandShape: HandShape): Int {
        return myHandShape.score + resultScore(opponentHand, myHandShape)
    }

    private fun resultScore(opponentHand: HandShape, myHandShape: HandShape): Int {
        return when (opponentHand) {
            myHandShape -> 3
            HandShape.defeats[myHandShape] -> 6
            else -> 0
        }
    }

}

enum class HandShape(val opponentCode: String, val elfCode: String, val score: Int) {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3);

    companion object {
        val defeats = mapOf(
            ROCK to SCISSORS,
            PAPER to ROCK,
            SCISSORS to PAPER
        )

        fun fromOpponentCode(code: String) = values().first { it.opponentCode == code }
        fun fromElfCode(code: String) = values().first { it.elfCode == code }
        fun decodeHand(opponentHand: HandShape, command: String): HandShape {
            return when (command) {
                "X" -> defeats[opponentHand]!!
                "Y" -> opponentHand
                "Z" -> defeats.entries.first { it.value == opponentHand }.key
                else -> throw IllegalStateException()
            }
        }
    }
}