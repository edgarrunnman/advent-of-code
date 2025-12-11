package utils

data class Vec3(val x: Double, val y: Double, val z: Double)

fun Vec3.distance(other: Vec3): Double =
    kotlin.math.sqrt(
        (this.x - other.x).let { it * it } + (this.y - other.y).let { it * it } + (this.z - other.z).let { it * it }
    )

fun String.toVec3() =
    this.split(",").let { Vec3(it.get(0).toDouble(), it.get(1).toDouble(), it.get(2).toDouble()) }
