package utils

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.pow
import kotlin.math.sqrt

data class Vec2(val x: BigInteger, val y: BigInteger )

fun Vec2.area(other: Vec2): BigInteger =
        (this.x - other.x + 1.toBigInteger()) * (this.y - other.y + 1.toBigInteger())

fun String.toVec2() =
    this.split(",").let { Vec2(it.get(0).toBigInteger(), it.get(1).toBigInteger()) }

