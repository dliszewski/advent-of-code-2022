class Day11 {

    fun part1(input: String): Long {
        val monkeys = input.split("\n\n").map { it.toMonkey() }.toTypedArray()
        val rounds = 20
        val stressReducerFormula: (Long) -> Long = { it / 3 }
        repeat(rounds) { round ->
            monkeys.forEach {
                it.handleItems(stressReducerFormula).forEach {
                        (item, monkey) -> monkeys[monkey].passItem(item)
                }
            }
        }
        return monkeys.map { it.handledItemsCount }.sortedDescending().take(2).reduce { acc, i -> acc * i }
    }

    fun part2(input: String): Long {
        val monkeys = input.split("\n\n").map { it.toMonkey() }.toTypedArray()
        val rounds = 10000
        val lcm = monkeys.map { it.divisor.toLong() }.reduce(Long::times)
        val stressReducerFormula: (Long) -> Long = { it % lcm }
        repeat(rounds) {
            monkeys.forEach {
                it.handleItems(stressReducerFormula).forEach { (itemToPass, monkeyNumber) ->
                    monkeys[monkeyNumber].passItem(itemToPass)
                }
            }
        }
        val map = monkeys.map { it.handledItemsCount }
        return map.sortedDescending().take(2).reduce { acc, i -> acc * i }
    }

    data class Monkey(
        private val items: MutableList<Long>,
        private val newStressLevelFormula: (Long) -> Long,
        val divisor: Int,
        private val passToOnSuccess: Int,
        private val passToOnFailure: Int,
        var handledItemsCount: Long = 0L
    ) {
        fun handleItems(stressReducerFormula: (Long) -> Long): List<Pair<Long, Int>> {
            val handledItems = items.map {
                handledItemsCount++
                handleItem(it, stressReducerFormula)
            }
            items.clear()
            return handledItems
        }

        private fun handleItem(item: Long, stressReducerFormula: (Long) -> Long): Pair<Long, Int> {
            val newStressLevel = newStressLevelFormula(item)
            val levelAfterBoredMonkey = stressReducerFormula(newStressLevel)
            return levelAfterBoredMonkey to if (levelAfterBoredMonkey % divisor == 0L) passToOnSuccess else passToOnFailure
        }

        fun passItem(item: Long) {
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
        .map { it.toLong() }
        .toMutableList()

    private fun String.toDivisor() = trim().substringAfter("Test: divisible by ").toInt()
    private fun String.toSuccessMonkey() = trim().substringAfter("If true: throw to monkey ").toInt()
    private fun String.toFailureMonkey() = trim().substringAfter("If false: throw to monkey ").toInt()

    private fun String.mapToOperation(): (Long) -> Long {
        val (sign, value) = trim().substringAfter("Operation: new = old ").split(" ")
        return when (sign) {
            "*" -> { old -> old * if (value == "old") old else value.toLong() }
            "+" -> { old -> old + if (value == "old") old else value.toLong() }
            else -> error("wrong operation $this")
        }
    }
}







