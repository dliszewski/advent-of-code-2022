import java.util.function.IntFunction

class Day11 {

    fun part1(input: String): Int {
        val monkeys = input.split("\n\n").map { it.toMonkey() }.toTypedArray()
        val rounds = 20
        repeat(rounds) { round ->
            monkeys.forEach {
                it.handleItems().forEach { (item, monkey) ->
                    monkeys[monkey].passItem(item)
                }
            }
            println("End round $round")
        }
        return monkeys.map { it.handledItemsCount }.sortedDescending().take(2).reduce { acc, i -> acc * i }
    }

    fun part2(input: List<String>): Int {
        return 2
    }

    data class Monkey(
        private val items: MutableList<Int>,
        private val newStressLevelFormula: IntFunction<Int>,
        private val divisor: Int,
        private val passToOnSuccess: Int,
        private val passToOnFailure: Int
    ) {
        var handledItemsCount = 0
        fun handleItems(): List<Pair<Int, Int>> {
            val handledItems = items.map {
                handledItemsCount++
                handleItem(it)
            }
            items.clear()
            return handledItems
        }

        private fun handleItem(item: Int): Pair<Int, Int> {
            val newStressLevel = newStressLevelFormula.apply(item)
            val levelAfterBoredMonkey = newStressLevel / 3
            return levelAfterBoredMonkey to if (levelAfterBoredMonkey % divisor == 0) passToOnSuccess else passToOnFailure
        }

        fun passItem(item: Int) {
            items.add(item)
        }
    }

    private fun String.toMonkey(): Monkey {
        val lines = split("\n")
        val monkeyNumber = lines[0].toMonkeyNumber()
        val items = lines[1].toItems()
        val operation = lines[2].mapToOperation()
        val divisor = lines[3].toDivisor()
        val passToOnSuccess = lines[4].toSuccessMonkey()
        val passToOnFailure = lines[5].toFailureMonkey()
        return Monkey(items, operation, divisor, passToOnSuccess, passToOnFailure)
    }

    private fun String.toMonkeyNumber() = substringAfter("Monkey ").dropLast(1).toInt()
    private fun String.toItems() = trim()
        .substringAfter("Starting items: ")
        .split(", ")
        .map { it.toInt() }
        .toMutableList()

    private fun String.toDivisor() = trim().substringAfter("Test: divisible by ").toInt()
    private fun String.toSuccessMonkey() = trim().substringAfter("If true: throw to monkey ").toInt()
    private fun String.toFailureMonkey() = trim().substringAfter("If false: throw to monkey ").toInt()

    private fun String.mapToOperation(): IntFunction<Int> {
        val (sign, value) = trim().substringAfter("Operation: new = old ").split(" ")
        return when (sign) {
            "*" -> IntFunction { i: Int -> if (value == "old") i * i else i * value.toInt() }
            "+" -> IntFunction { i: Int -> if (value == "old") i + i else i + value.toInt() }
            else -> error("wrong operation $this")
        }
    }
}







