
class Day10 {

    fun part1(input: List<String>): Int {
        val cyclesToCheck = (20..220 step 40).toList()
        var result = 0
        val instructions = listOf(1) + input.flatMap { it.toOperation() }
        instructions.reduceIndexed { cycle, acc, instruction ->
            if (cycle in cyclesToCheck) {
                result += cycle * acc
            }
            acc + instruction
        }
        return result
    }

    fun part2(input: List<String>): Int {
        return 2
    }

    private fun String.toOperation(): List<Int> {
        return when {
            this == "noop" -> listOf(0)
            this.startsWith("addx") -> listOf(0, this.substringAfter("addx ").toInt())
            else -> error("wrong operation $this")
        }
    }

}


