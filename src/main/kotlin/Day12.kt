import java.util.*

class Day12 {

    fun part1(input: List<String>): Int {
        val area = mapToArea(input)
        return area.traverseFromStart()
    }

    fun part2(input: List<String>): Int {
        return mapToArea(input).traverseFromEnd()
    }

    private fun mapToArea(input: List<String>): Area {
        var finishPoint: Point? = null
        var startingPoint: Point? = null
        val field: Map<Point, Int> = input.flatMapIndexed { y, row ->
            row.mapIndexed { x, heightCode ->
                val point = Point(x, y)
                val height = heightCode.decodeHeight()
                when (heightCode) {
                    'E' -> point to height.also { finishPoint = point }
                    'S' -> point to height.also { startingPoint = point }
                    else -> point to height
                }
            }
        }.toMap()
        return Area(startingPoint!!, finishPoint!!, field)
    }

    private fun Char.decodeHeight(): Int {
        return when (this) {
            'S' -> 'a' - 'a'
            'E' -> 'z' - 'a'
            in 'a'..'z' -> this - 'a'
            else -> error("Wrong level value $this")
        }
    }

    data class Area(
        val startingPoint: Point,
        val destination: Point,
        val field: Map<Point, Int>
    ) {
        fun traverseFromStart(): Int {
            val canMove: (Int, Int) -> Boolean = { nextPlace, currentPlace -> nextPlace - currentPlace <= 1 }
            val isDestination: (Path) -> Boolean = { it.point == destination }
            return traverse(canMove, isDestination, startingPoint)
        }

        fun traverseFromEnd(): Int {
            val canMove: (Int, Int) -> Boolean = { nextPlace, currentPlace -> currentPlace - nextPlace <= 1 }
            val isDestination: (Path) -> Boolean = { field[it.point] == 0 }
            return traverse(canMove, isDestination, destination)
        }

        private fun traverse(
            canMove: (Int, Int) -> Boolean,
            isDestination: (Path) -> Boolean,
            startingPoint: Point
        ): Int {
            val toBeEvaluated = PriorityQueue<Path>().apply { add(Path(startingPoint, 0)) }
            val visited = mutableSetOf<Point>()

            while (toBeEvaluated.isNotEmpty()) {
                val currentPlace = toBeEvaluated.poll()
                if (isDestination(currentPlace)) {
                    return currentPlace.distanceSoFar
                }
                if (currentPlace.point !in visited) {
                    visited.add(currentPlace.point)
                    currentPlace.point
                        .neighbors()
                        .filter { nextPlace -> nextPlace in field }
                        .filter { nextPlace -> canMove(field.getValue(nextPlace), field.getValue(currentPlace.point)) }
                        .map { nextPlace -> Path(nextPlace, currentPlace.distanceSoFar + 1) }
                        .forEach { path -> toBeEvaluated.offer(path) }
                }
            }
            error("No path found to destination")
        }
    }

    data class Path(val point: Point, val distanceSoFar: Int) : Comparable<Path> {
        override fun compareTo(other: Path): Int {
            return distanceSoFar - other.distanceSoFar
        }
    }

    data class Point(val x: Int, val y: Int) {
        fun neighbors(): List<Point> {
            return listOf(
                Point(x, y + 1),
                Point(x, y - 1),
                Point(x + 1, y),
                Point(x - 1, y)
            )
        }
    }
}









