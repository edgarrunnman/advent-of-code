package utils

import java.math.BigInteger

fun <T : Comparable<T>> ClosedRange<T>.isOutside(other: ClosedRange<T>, touching: Boolean = false) =
    if (touching)
        start >= other.endInclusive || endInclusive <= other.start
    else
        start > other.endInclusive || endInclusive < other.start


fun <T : Comparable<T>> ClosedRange<T>.isWithin(other: ClosedRange<T>, touching: Boolean = false) =
    if (touching)
        start >= other.start && endInclusive <= other.endInclusive
    else
        start > other.start && endInclusive < other.endInclusive

fun <T : Comparable<T>> ClosedRange<T>.isOverlappingToRight(
    other: ClosedRange<T>,
    touching: Boolean = false
) =
    if (touching)
        start >= other.start && start <= other.endInclusive && endInclusive >= other.endInclusive
    else
        start > other.start && start < other.endInclusive && endInclusive > other.endInclusive

fun <T : Comparable<T>> ClosedRange<T>.isOverlappingToLeft(other: ClosedRange<T>, touching: Boolean = false) =
    if (touching)
        start <= other.start && endInclusive >= other.start && endInclusive <= other.endInclusive
    else
        start < other.start && endInclusive > other.start && endInclusive < other.endInclusive

fun <T : Comparable<T>> ClosedRange<T>.isOverlappingBoth(other: ClosedRange<T>, touching: Boolean = false) =
    if (touching)
        start <= other.start && endInclusive >= other.endInclusive
    else
        start < other.start && endInclusive > other.endInclusive

enum class RangeRelationship {
    OUTSIDE,
    WITHIN,
    OVERLAPPING_LEFT,
    OVERLAPPING_LEFT_TOUCH,
    OVERLAPPING_RIGHT,
    OVERLAPPING_RIGHT_TOUCH,
    OVERLAPPING_BOTH
}

fun <T : Comparable<T>> ClosedRange<T>.relation(
    other: ClosedRange<T>,
): RangeRelationship =
    when {
        this.isOutside(other, false) -> RangeRelationship.OUTSIDE
        this.isWithin(other, true) -> RangeRelationship.WITHIN
        this.isOverlappingToRight(other, true) -> RangeRelationship.OVERLAPPING_RIGHT_TOUCH
        this.isOverlappingToLeft(other, true) -> RangeRelationship.OVERLAPPING_LEFT_TOUCH
        this.isOverlappingToRight(other, false) -> RangeRelationship.OVERLAPPING_RIGHT
        this.isOverlappingToLeft(other, false) -> RangeRelationship.OVERLAPPING_LEFT
        this.isOverlappingBoth(other, false) -> RangeRelationship.OVERLAPPING_BOTH
        else -> error("BUG with input $this and $other")

    }

fun normalizeRanges(inputRanges: List<ClosedRange<BigInteger>>): List<ClosedRange<BigInteger>> {

    val notOverlapping = inputRanges.filterNot { range ->
        inputRanges.filterNot { it == range }.any { !range.isOutside(it) }
    }.distinct()

    val overlapping = inputRanges.filter { range ->
        inputRanges.filterNot { it == range }.any { !range.isOutside(it) }
    }.toMutableList()

    val finalRanges: MutableList<ClosedRange<BigInteger>> = mutableListOf()

    while (overlapping.isNotEmpty()) {
        val testRanges = mutableListOf(overlapping.removeLast())
        overlapping.forEach { nextRange ->
            testRanges.filter { true }.forEach { testRange ->
                when (testRange.relation(nextRange)) {
                    RangeRelationship.OUTSIDE -> Unit

                    RangeRelationship.WITHIN -> {
                        testRanges.remove(testRange)
                    }

                    RangeRelationship.OVERLAPPING_LEFT,
                    RangeRelationship.OVERLAPPING_LEFT_TOUCH -> {
                        testRanges.remove(testRange)
                        testRanges.add(testRange.start..nextRange.start - 1.toBigInteger())
                    }

                    RangeRelationship.OVERLAPPING_RIGHT,
                    RangeRelationship.OVERLAPPING_RIGHT_TOUCH -> {
                        testRanges.remove(testRange)
                        testRanges.add(nextRange.endInclusive + 1.toBigInteger()..testRange.endInclusive)
                    }

                    RangeRelationship.OVERLAPPING_BOTH -> {
                        testRanges.remove(testRange)
                        testRanges.add(testRange.start..nextRange.start - 1.toBigInteger())
                        testRanges.add(nextRange.endInclusive + 1.toBigInteger()..testRange.endInclusive)
                    }
                }
            }


        }
        finalRanges.addAll(testRanges)
    }
    return notOverlapping + finalRanges
}
