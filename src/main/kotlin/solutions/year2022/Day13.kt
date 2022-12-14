package solutions.year2022

import DataFetcher
import solutions.Solution

class Day13(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 13
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        var foo = input.split("\n\n")
        var foo2 = foo.map { string -> string.split("\n") }
            .map { Pair(it[0].pack(), it[1].pack()) }
        var foo3 = foo2.mapIndexed { index, pari ->
            Pair(index + 1, pari.first.compareTo(pari.second))
        }
        return foo3.filter { it.second != -1 }.sumOf { it.first }.toString()
    }

    override fun partTwoResult(): String {
        var foo0 = input.replace("\n\n", "\n")
        var foo = foo0.split("\n").filter { it.isNotEmpty() }.map { it.pack() }.toMutableList()
        var sep1 = "[[2]]".pack()
        foo.add(sep1)
        var sep2 = "[[6]]".pack()
        foo.add(sep2)
        var foo2 = foo.sortedBy { it }.reversed()
        var foo3 = (foo2.indexOf(sep1) + 1) * (foo2.indexOf(sep2) + 1)
        return foo3.toString()
    }

    private fun String.pack(): Pack {
        var pack = Pack()
        var currentPack = pack
        var input = this
        var index = 0
        while (index < input.count()) {
            var char = input[index]
            when (char) {
                in ('0'..'9') -> {
                    var number = "\\d+".toRegex().find(this.slice(index until this.count()))?.value?.toInt() as Int
                    var newPack = Pack(number)
                    newPack.parant = currentPack
                    currentPack.children.add(newPack)
                    index += number.toString().count()
                }

                '[' -> {
                    var newPack = Pack()
                    newPack.parant = currentPack
                    currentPack.children.add(newPack)
                    currentPack = newPack
                    index++
                }

                ']' -> {
                    currentPack = currentPack.parant!!
                    index++
                }

                else -> {
                    index++
                }
            }
        }
        return currentPack.children.first()
    }

    class Pack() : Comparable<Pack> {
        var parant: Pack? = null
        var value: Int? = null
        var children: MutableList<Pack> = mutableListOf()

        constructor(value: Int) : this() {
            this.value = value
        }

        override fun compareTo(other: Pack): Int {

            var left = this
            var right = other
            if (left.value != null && right.value == null) {
                val newPack = Pack()
                newPack.children.add(Pack(left.value!!))
                newPack.parant = left.parant
                left = newPack
            } else if (left.value == null && right.value != null) {
                val newPack = Pack()
                newPack.children.add(Pack(right.value!!))
                newPack.parant = right.parant
                right = newPack
            }
            if (left.value != null && right.value != null) {


                if (left.value!! < right.value!!) {
                    return 1
                }
                if (left.value!! > right.value!!) {
                    return -1
                }

            }

            if (left.value == null && left.children.isEmpty() && right.value != null) {
                return 1
            }

            if (left.value != null && right.value == null && right.children.isEmpty()) {
                return 1
            }

            if (left.value == null && right.value == null) {

                if (left.children.isEmpty() && right.children.isNotEmpty()) {
                    return 1
                }

                if (left.children.isNotEmpty() && right.children.isEmpty()) {
                    return -1
                }

                var leftCount = left.children.count()
                var rightCount = right.children.count()
                var longest = if (leftCount < rightCount) rightCount else leftCount
                (0 until longest).forEach { index ->

                    if (leftCount == index && leftCount == rightCount) {

                    } else {

                        if (leftCount == index && leftCount < rightCount) {
                            return 1
                        }
                        if (rightCount == index && leftCount > rightCount) {
                            return -1
                        }
                    }

                    val result = left.children[index].compareTo(right.children[index])
                    if (result != 0) {

                        return result
                    }
                }
            }
            return 0
        }
    }
}




