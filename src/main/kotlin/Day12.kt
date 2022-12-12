import java.util.*

class Day12 {

    fun part1(input: List<String>): Int {
        return mapToArea(input).traverse()
    }

    fun part2(input: List<String>): Int {
        return mapToArea(input, true).traverse()
    }

    private fun mapToArea(input: List<String>, addOtherStartingPoints: Boolean = false): Area {
        var finishPoint = Point(0, 0)
        val startingPoints = mutableSetOf<Point>()
        val field = input.mapIndexed { y, row ->
            row.mapIndexed { x, heightCode ->
                if (heightCode == 'E') {
                    finishPoint = Point(x, y)
                }
                if (heightCode == 'S') {
                    startingPoints.add(Point(x, y))
                }
                if (addOtherStartingPoints && heightCode == 'a') {
                    startingPoints.add(Point(x, y))
                }
                heightCode.decodeHeight()
            }.toIntArray()
        }.toTypedArray()
        return Area(
            field.first().size - 1,
            field.size - 1,
            startingPoints,
            finishPoint,
            field
        )
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
        val width: Int,
        val height: Int,
        val startingPoints: Set<Point>,
        val destination: Point,
        val filed: Array<IntArray>
    ) {
        fun traverse(): Int {
            val startingPaths = startingPoints.map { Path(it, 0, 0) }
            val toBeEvaluated = PriorityQueue<Path>().apply { addAll(startingPaths) }
            val visited = mutableSetOf<Point>()

            while (toBeEvaluated.isNotEmpty()) {
                val thisPlace = toBeEvaluated.poll()
                if (thisPlace.point == destination) {
                    return thisPlace.distanceSoFar
                }
                if (thisPlace.point !in visited) {
                    visited.add(thisPlace.point)
                    thisPlace.point
                        .neighbors()
                        .filter { it.x in (0..width) && it.y in (0..height) }
                        .map { Point(it.x, it.y) to filed[it.y][it.x] }
                        .filter { (_, height) -> height - thisPlace.height <= 1 }
                        .map { (point, height) -> Path(point, height, thisPlace.distanceSoFar + 1) }
                        .forEach { path -> toBeEvaluated.offer(path) }
                }
            }
            error("No path found to destination")
        }
    }

    data class Path(val point: Point, val height: Int, val distanceSoFar: Int) : Comparable<Path> {
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









