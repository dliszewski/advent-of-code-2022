import kotlin.math.abs

class Day15 {

    fun part1(input: List<String>, lookupRow: Int): Int {
        val sensorBeaconPairs = mapToSensorBeaconPairs(input)

        val minRange = sensorBeaconPairs.minOf { (sensor, beacon) ->
            minOf(
                sensor.x,
                beacon.x,
                sensor.x - sensor.distance(beacon)
            )
        }

        val maxRange = sensorBeaconPairs.maxOf { (sensor, beacon) ->
            maxOf(
                sensor.x,
                beacon.x,
                sensor.x + sensor.distance(beacon)
            )
        }

        return (minRange..maxRange).count { x ->
            val point = Point(x, lookupRow)
            sensorBeaconPairs.any { (sensor, beacon) ->
                beacon != point && sensor.distance(point) <= sensor.distance(beacon)
            }
        }
    }


    fun part2(input: List<String>): Int {
        return 2
    }

    private fun mapToSensorBeaconPairs(input: List<String>) =
        input.map { it.split(": closest beacon is at ") }
            .map { (point1, point2) ->
                point1.substringAfter("Sensor at ").mapToPoint() to point2.mapToPoint()
            }

    data class Point(val x: Int, val y: Int) {

        fun distance(other: Point): Int {
            return abs(other.x - x) + abs(other.y - y)
        }
    }

    private fun String.mapToPoint(): Point {
        val (x, y) = split(", ")
        return Point(x.substringAfter("x=").toInt(), y.substringAfter("y=").toInt())
    }
}













