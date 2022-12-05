import kotlin.collections.HashMap

class Day05 {

    fun part1(input: String): String {
        return getTopCrates(input, CrateMoverModel.CrateMover9000)
    }

    fun part2(input: String): String {
        return getTopCrates(input, CrateMoverModel.CrateMover9001)
    }

    private fun getTopCrates(input: String, modelType: CrateMoverModel): String {
        val (cranes, instructions) = input.split("\n\n")
        val cranesNumber = cranes.parseCranesNumber()
        val boxes = cranes.lines().dropLast(1).flatMap { line -> line.mapToBoxes(cranesNumber) }

        val craneOperator = CraneOperator(cranesNumber, modelType, boxes)

        val craneInstructions = instructions.parseToCommands()
        craneOperator.executeCommands(craneInstructions)
        return craneOperator.getTopCrates()
    }

    private fun String.parseCranesNumber() = lines()
        .last()
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }
        .count()

    private fun String.parseToCommands() = lines().map {
        val (p1, p2) = it.split(" from ")
        val moveQuantity = p1.replace("move ", "").toInt()
        val (from, to) = p2.split(" to ")
        CraneCommand(moveQuantity, from.toInt(), to.toInt())
    }



    data class Box(val index: Int, val content: String)
    data class CraneCommand(val quantity: Int, val from: Int, val to: Int)
    enum class CrateMoverModel { CrateMover9000, CrateMover9001 }

    private fun String.mapToBoxes(size: Int): List<Box> {
        return (1..size)
            .map { craneNumber -> Box(craneNumber, this.parseBoxFromCraneNumber(craneNumber)) }
            .filter { it.content.isNotBlank() }
    }

    private fun String.parseBoxFromCraneNumber(n: Int): String {
        val craneRow = n - 1
        return substring(4 * craneRow + 1, 4 * craneRow + 2)
    }

    private class CraneOperator(numberOfCranes: Int, private val modelType: CrateMoverModel, boxes: List<Box>) {
        private val boxStacks: HashMap<Int, ArrayDeque<String>> = HashMap()

        init {
            (1..numberOfCranes).forEach { boxStacks[it] = ArrayDeque() }
            boxes.forEach { putBoxInCrane(it) }
        }

        private fun putBoxInCrane(box: Box) {
            boxStacks[box.index]?.addFirst(box.content)
        }

        fun executeCommands(commands: List<CraneCommand>) {
            commands.forEach { command ->
                val toMove = (1..command.quantity).map { boxStacks[command.from]?.removeLast()!! }
                when (modelType) {
                    CrateMoverModel.CrateMover9000 -> {
                        boxStacks[command.to]?.addAll(toMove)
                    }
                    CrateMoverModel.CrateMover9001 -> {
                        boxStacks[command.to]?.addAll(toMove.reversed())
                    }
                }
            }

        }

        fun getTopCrates(): String {
            return boxStacks.values.joinToString(separator = "") { it.last() }
        }
    }


}