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
        val sandPoint = Point(500, 0)
        val rocks = input.flatMap {
            it.split(" -> ").windowed(2).flatMap { (line1, line2) ->
                mapToPoints(line1, line2)
            }
        }.toSet()
        val cave = Cave(rocks, sandPoint, true)
        return cave.dropSandWithFloor()
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

    class Cave(rocks: Set<Point>, val sandPoint: Point, withFloor: Boolean = false) {
        val rocksAndFloor = mutableSetOf<Point>()
        val sand = mutableSetOf<Point>()
        var lowestRock = 0

        init {
            rocksAndFloor.addAll(rocks)
            lowestRock = if (withFloor) {
                val offset = rocks.map { it.y }.max()
                val floorLevel = rocks.map { it.y }.max() + 2
                val minX = rocks.map { it.x }.min() - offset
                val maxX = rocks.map { it.x }.max() + offset
                val floor = (minX..maxX).map {
                    Point(it, floorLevel)
                }
                rocksAndFloor.addAll(floor)
                floorLevel
            } else {
                rocks.map { it.y }.max()
            }

        }

        fun dropSand(): Int {
            var nextSpot = findLandingPlace(sandPoint)
            while (nextSpot != null) {
                sand.add(nextSpot)
                val np = findLandingPlace(sandPoint)
                nextSpot = np
            }
            draw()
            return sand.size
        }

        fun dropSandWithFloor(): Int {
            var nextSpot = findLandingPlace(sandPoint)
            while (nextSpot != null && nextSpot != sandPoint) {
                sand.add(nextSpot)
                val np = findLandingPlace(sandPoint)
                nextSpot = np
            }
            draw()
            return sand.size + 1
        }

        private fun findLandingPlace(current: Point): Point? {
            if (current.y > lowestRock) return null
            val nextPoint = current.moveDown().firstOrNull { it !in rocksAndFloor && it !in sand }
            return when (nextPoint) {
                null -> current
                else -> findLandingPlace(nextPoint)
            }
        }

        private fun draw() {
            val all = mutableListOf<Point>().apply {
                addAll(rocksAndFloor)
                addAll(sand)
            }
            val minX = all.map { it.x }.min()
            val maxX = all.map { it.x }.max()
            val maxY = all.map { it.y }.max()
            for (y in 0..maxY) {
                for (x in minX..maxX) {
                    when (Point(x, y)) {
                        Point(500, 0) -> print('+')
                        in rocksAndFloor -> print('#')
                        in sand -> print('o')
                        else -> print('.')
                    }
                    if (x == maxX) {
                        println()
                    }
                }
            }

        }
    }

}













