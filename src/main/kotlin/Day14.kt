class Day14 {

    fun part1(input: List<String>): Int {
        val sandPoint = Point(500, 0)
        val rocks = input.flatMap {
            it.split(" -> ").windowed(2).flatMap { (line1, line2) ->
                mapToPoints(line1, line2)
            }
        }.toSet()
        val cave = Cave(rocks, sandPoint)
        return cave.dropSand()
    }

    fun part2(input: List<String>): Int {
        return 2
    }

    private fun mapToPoints(line1: String, line2: String): List<Point> {
        val (p1x, p1y) = line1.split(",").map { it.toInt() }
        val (p2x, p2y) = line2.split(",").map { it.toInt() }
        val xRange = if (p1x <= p2x) p1x..p2x else p2x..p1x
        val yRange = if (p1y <= p2y) p1y..p2y else p2y..p1y
        return (xRange).flatMap { x -> (yRange).map { y -> Point(x, y) } }
    }


    data class Point(val x: Int, val y: Int) {
        fun moveDown(): List<Point> {
            return listOf(
                Point(x, y + 1),
                Point(x - 1, y + 1),
                Point(x + 1, y + 1)
            )
        }
    }

    class Cave(val rocks: Set<Point>, val sandPoint: Point) {
        val sand = mutableSetOf<Point>()
        val lowestRock = rocks.map { it.y }.max()

        fun dropSand(): Int {
            var nextSpot = findLandingPlace(sandPoint)
            while (nextSpot != null) {
                sand.add(nextSpot)
                val np = findLandingPlace(sandPoint)
                nextSpot = np
            }
            return sand.size
        }

        private fun findLandingPlace(current: Point): Point? {
            if (current.y > lowestRock) return null
            val nextPoint = current.moveDown().firstOrNull { it !in rocks && it !in sand }
            return when (nextPoint) {
                null -> current
                else -> findLandingPlace(nextPoint)
            }
        }
    }

}













