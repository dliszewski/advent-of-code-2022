import kotlin.math.abs

class Day09 {

    fun part1(input: List<String>): Int {
        val movement = input.map { it.split(" ") }
            .flatMap { (direction, step) -> (1..step.toInt()).map { direction.toDirection() } }
        return simulateMoves(movement, 2).size
    }

    fun part2(input: List<String>): Int {
        val movement = input.map { it.split(" ") }
            .flatMap { (direction, step) -> (1..step.toInt()).map { direction.toDirection() } }
        return simulateMoves(movement, 10).size
    }

    private fun simulateMoves(moves: List<Direction>, knotsNumber: Int): Set<Point> {
        val knots = MutableList(knotsNumber) { Point(0, 0) }
        val visitedTail = mutableSetOf<Point>()
        visitedTail.add(knots.last())

        moves.forEach { direction ->
            knots[0] = knots[0].move(direction)
            for ((headIndex, tailIndex) in knots.indices.zipWithNext()) {
                val head = knots[headIndex]
                val tail = knots[tailIndex]
                if (!tail.isAdjacentTo(head)) {
                    knots[tailIndex] = tail.moveTowards(head)
                }
            }
            visitedTail.add(knots.last())
        }

        return visitedTail
    }

    private fun Point.move(direction: Direction): Point {
        return when (direction) {
            Direction.UP -> Point(x, y + 1)
            Direction.DOWN -> Point(x, y - 1)
            Direction.LEFT -> Point(x - 1, y)
            Direction.RIGHT -> Point(x + 1, y)
        }
    }

    private fun Point.isAdjacentTo(other: Point): Boolean {
        return abs(x - other.x) <= 1 && abs(y - other.y) <= 1
    }

    private fun Point.moveTowards(head: Point): Point {
        return Point(
            x + (head.x - x).coerceIn(-1..1),
            y + (head.y - y).coerceIn(-1..1)
        )
    }

    data class Point(val x: Int, val y: Int)

    enum class Direction { UP, DOWN, LEFT, RIGHT }

    private fun String.toDirection(): Direction {
        return when (this) {
            "U" -> Direction.UP
            "D" -> Direction.DOWN
            "L" -> Direction.LEFT
            "R" -> Direction.RIGHT
            else -> error("wrong direction $this")
        }
    }

}

