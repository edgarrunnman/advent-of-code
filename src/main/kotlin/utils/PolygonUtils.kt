package utils

import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Polygon
import org.locationtech.jts.geom.PrecisionModel
import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.hypot
import kotlin.plus

private val gf = GeometryFactory(PrecisionModel())


fun String.toCoordinate(): Coordinate =
    this.split(",").let { Coordinate(it.get(0).toDouble(), it.get(1).toDouble()) }

fun Coordinate.area(other: Coordinate): BigInteger =
    (abs(this.x.toInt() - other.x.toInt()).toBigInteger() + 1.toBigInteger()) * (abs(
        this.y
            .toInt() - other.y.toInt()
    ).toBigInteger() + 1.toBigInteger())


fun polygonFromBreakpoints(points: List<Coordinate>): Polygon {
    require(points.size >= 3) { "Need at least 3 points" }
    val ringCoords =
        if (points.first() == points.last()) points
        else points + points.first()

    val shell = gf.createLinearRing(ringCoords.toTypedArray())
    require(shell.isValid) { "Invalid shell ring (self-intersection etc.)" }

    return gf.createPolygon(shell)
}

fun polygonFromRectangle(a: Coordinate, b: Coordinate): Polygon =
    rectangleFromTwoPoints(a, b).let { polygonFromBreakpoints(it) }

fun rectangleFromTwoPoints(a: Coordinate, b: Coordinate): List<Coordinate> =
    listOf(
        a,
        Coordinate(b.x, a.y),
        b,
        Coordinate(a.x, b.y)
    )

fun isInsideOrOnBoundary(container: Polygon, other: Geometry): Boolean =
    container.covers(other) // covers is usually friendlier than contains
