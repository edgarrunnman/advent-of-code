package utils

import kotlin.math.pow

data class Vec3(val x: Double, val y: Double, val z: Double)

fun Vec3.distance(other: Vec3): Double =
    kotlin.math.sqrt(
        (this.x - other.x).pow(2)
                + (this.y - other.y).pow(2)
                + (this.z - other.z).pow(2)
    )

fun String.toVec3() =
    this.split(",").let { Vec3(it.get(0).toDouble(), it.get(1).toDouble(), it.get(2).toDouble()) }
