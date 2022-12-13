package solutions.year2022

import DataFetcher
import solutions.Solution

class Day13 (override var dataFetcher: DataFetcher) : Solution {
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
            .map { Triple(it[0].pack(), it[1].pack(), it ) }
        var foo3 = foo2.mapIndexed { index, triple ->
            println()
            println("== Pair ${index + 1} ==")
            Pair(index + 1, triple.compare())
        }
        return foo3.filter { it.second!! }.sumOf { it.first }.toString()
    }

    override fun partTwoResult(): String {
        var foo = inputAsList
        return foo.toString()
    }
private fun Triple<Pack, Pack, List<String>>.verifyInput(){
    if (this.first.toString() == this.third[0]){
        println("verified OK")
        println(this.first.toString())
    } else {
        println("FAIL format")
        println(this.first.toString())
        println(this.third[0])
    }
    if (this.second.toString() == this.third[1]){
        println("verified OK")
        println(this.second.toString())
    } else {
        println("FAIL format")
        println(this.second.toString())
        println(this.third[1])
    }

}
    private fun Triple<Pack, Pack, List<String>>.compare(level: String = ""): Boolean? {
        var left = this.first
        var right = this.second
        if (left.value != null && right.children.isNotEmpty()) {
            print(level.toString())
            print("- Compare ")
            println("${left.value} VS ${right.children}")
            print(level.toString())
            print("- ")
            println("Mixed types; convert left to [${left.value}] and retry comparison")
            val newPack = Pack()
            newPack.children.add(Pack(left.value!!))
            newPack.parant = left.parant
            left = newPack
        } else if (left.children.isNotEmpty() && right.value != null) {
            print(level.toString())
            print("- Compare ")
            println("${left.children} VS ${right.value}")
            print(level.toString())
            print("- ")
            println("Mixed types; convert right to [${right.value}] and retry comparison")
            val newPack = Pack()
            newPack.children.add(Pack(right.value!!))
            newPack.parant = right.parant
            right = newPack
        }
        if (left.value != null && right.value != null) {

            print(level.toString())
            print("- Compare ")
            println("${left.value} VS ${right.value}")
            if (left.value!! < right.value!!) {
                print(level.toString())
                print("- ")
                println("Left side is smaller, so inputs are in the right order")
                return true
            }
            if (left.value!! > right.value!!) {
                print(level.toString())
                print("- ")
                println("Right side is smaller, so inputs are not in the right order")
                return false
            }

        }
        if (left.value == null && right.value == null) {
            if (level == "") {
                this.verifyInput()
            } else {
                print(level.toString())
                print("- Compare ")
                println("${left} VS ${right}")
            }
            if (left.children.isEmpty() && right.children.isNotEmpty()) {
                print(level.toString())
                print("- ")
                println("Left side ran out of items, so inputs are in the right order")
                return true
            }

            if (left.children.isNotEmpty() && right.children.isEmpty()) {
                print(level.toString())
                print("- ")
                println("Right side ran out of items, so inputs are not in the right order")
                return false
            }

            var leftCount = left.children.count()
            var rightCount = right.children.count()
            var longest = if (leftCount < rightCount) rightCount else leftCount
            (0 until longest).forEach { index ->
                if (leftCount == index && leftCount < rightCount) {
                    print(level.toString())
                    print("- Compare ")
                    println("left run out of items")
                    println("in the right order")
                    return true
                }
                if (rightCount == index && leftCount > rightCount) {
                    print(level.toString())
                    print("- Compare ")
                    println("right run out of items")
                    println("in NOT the right order")
                    return false
                }
                var result = Triple(left.children[index], right.children[index], this.third).compare("$level ")
                if (result != null) {

                    return result
                }
            }
        }
        return null
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
}


class Pack() {
    var parant: Pack? = null
    var value: Int? = null
    var children: MutableList<Pack> = mutableListOf()

    constructor(value: Int) : this() {
        this.value = value
    }

    override fun toString(): String {
        if (children.isNotEmpty()) {
            val array = children.map { it.toString() }.joinToString(",", "", "")
            return "[${array}]"
        }
        if (value == null)
            return "[]"
        return value.toString()
    }
}

