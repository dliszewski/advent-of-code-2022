import kotlin.math.abs

class Day15 {

    fun part1(input: List<String>, lookupRow: Int): Int {
        val sensors = mapToSensors(input)

        val minRange = sensors.minOf { sensor ->
            minOf(
                sensor.position.x,
                sensor.beacon.x,
                sensor.position.x - sensor.distanceToBeacon()
            )
        }

        val maxRange = sensors.maxOf { sensor ->
            maxOf(
                sensor.position.x,
                sensor.beacon.x,
                sensor.position.x + sensor.distanceToBeacon()
            )
        }

        return (minRange..maxRange).count { x ->
            val point = Point(x, lookupRow)
            sensors.any { sensor -> sensor.beacon != point && sensor.position.distance(point) <= sensor.distanceToBeacon() }
        }
    }


    fun part2(input: List<String>, limit: Int): Long {
        val sensors = mapToSensors(input)
        val pointNotCovered = findPositionNotCoveredByBeacon(limit, sensors)
        return pointNotCovered.x.toLong() * 4000000 + pointNotCovered.y.toLong()
    }

    private fun findPositionNotCoveredByBeacon(limit: Int, sensors: List<Sensor>): Point {
        for (y in 0..limit) {
            var point = Point(0, y)
            while (point.x <= limit) {
                val coveredBySensor = pointCoveredBySensor(sensors, point)
                if (coveredBySensor == null) {
                    return point
                } else {
                    val maxCovered = coveredBySensor.maxDistanceCovered(point.y)
                    point = Point(maxCovered.x + 1, maxCovered.y)
                }
            }
        }
        error("Should find one...")
    }

    private fun pointCoveredBySensor(sensors: List<Sensor>, point: Point): Sensor? {
        return sensors.find { it.isInRange(point) }
    }

    private fun mapToSensors(input: List<String>) =
        input.map { it.split(": closest beacon is at ") }
            .map { (point1, point2) ->
                Sensor(
                    point1.substringAfter("Sensor at ").mapToPoint(),
                    point2.mapToPoint()
                )
            }

    data class Sensor(val position: Point, val beacon: Point) {

        fun distanceToBeacon(): Int {
            return position.distance(beacon)
        }

        fun isInRange(other: Point): Boolean {
            return position.distance(other) <= position.distance(beacon)
        }

        fun maxDistanceCovered(row: Int): Point {
            return Point(position.x + abs(distanceToBeacon() - abs(row - position.y)), row)
        }
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













