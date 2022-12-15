class Day14 {

    fun part1(input: List<String>): Int {
        val rocks = mapToRocks(input)
        return Cave(rocks).dropSand()
    }

    fun part2(input: List<String>): Int {
        val rocks = mapToRocks(input)
        return Cave(rocks, true).dropSandWithFloor()
    }

    private fun mapToRocks(input: List<String>) = input.flatMap {
        it.split(" -> ")
            .windowed(2)
            .flatMap { (line1, line2) -> mapToPoints(line1, line2) }
    }.toSet()

    private fun mapToPoints(line1: String, line2: String): List<Point> {
        val (p1x, p1y) = line1.split(",").map { it.toInt() }
        val (p2x, p2y) = line2.split(",").map { it.toInt() }
        val xRange = if (p1x <= p2x) p1x..p2x else p2x..p1x
        val yRange = if (p1y <= p2y) p1y..p2y else p2y..p1y
        return (xRange).flatMap { x -> (yRange).map { y -> Point(x, y) } }
    }

    data class Point(val x: Int, val y: Int) {
        fun possibleMovesDown(): List<Point> {
            return listOf(
                Point(x, y + 1),
                Point(x - 1, y + 1),
                Point(x + 1, y + 1)
            )
        }
    }

    class Cave(rocks: Set<Point>, withFloor: Boolean = false) {
        private val sandPoint = Point(500, 0)
        private val rocksAndFloor = mutableSetOf<Point>()
        private val sand = mutableSetOf<Point>()
        private var lowestRock = 0

        init {
            rocksAndFloor.addAll(rocks)
            lowestRock = if (withFloor) {
                val floorLevel = rocks.map { it.y }.max() + 2
                val floor = createFloor(rocks, floorLevel)
                rocksAndFloor.addAll(floor)
                floorLevel
            } else {
                rocks.map { it.y }.max()
            }
        }

        private fun createFloor(rocks: Set<Point>, floorLevel: Int): List<Point> {
            val offset = rocks.map { it.y }.max()
            val minX = rocks.map { it.x }.min() - offset
            val maxX = rocks.map { it.x }.max() + offset
            return (minX..maxX).map { Point(it, floorLevel) }
        }

        fun dropSand(): Int {
            var nextSpot = findLandingPlace(sandPoint)
            while (nextSpot != null && nextSpot != sandPoint) {
                sand.add(nextSpot)
                nextSpot = findLandingPlace(sandPoint)
            }
            draw()
            return sand.size
        }

        fun dropSandWithFloor(): Int {
            return dropSand() + 1
        }

        private fun findLandingPlace(current: Point): Point? {
            if (current.y > lowestRock) return null
            val nextPoint = current.possibleMovesDown().firstOrNull { it !in rocksAndFloor && it !in sand }
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













