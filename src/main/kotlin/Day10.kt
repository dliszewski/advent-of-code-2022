import java.lang.StringBuilder

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

    fun part2(input: List<String>): String {
        val crtCyclesToCheck = (40..220 step 40).toList()
        var spritePosition = 0
        val instructions = listOf(1) + input.flatMap { it.toOperation() }
        val crtScreen = StringBuilder()

        instructions.reduceIndexed { cycle, acc, instruction ->
            crtScreen.append( if((cycle-1) % 40 in spritePosition-1..spritePosition+1) '#' else '.')
            if (cycle in crtCyclesToCheck) {
                crtScreen.appendLine()
            }
            spritePosition = acc + instruction
            acc + instruction
        }

        val screen =  crtScreen.toString()
        println("====== Result ======")
        println(screen)
        println("====================")
        return screen
    }

    private fun String.toOperation(): List<Int> {
        return when {
            this == "noop" -> listOf(0)
            this.startsWith("addx") -> listOf(0, this.substringAfter("addx ").toInt())
            else -> error("wrong operation $this")
        }
    }

}


