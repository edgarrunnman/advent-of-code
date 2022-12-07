package solutions.year2022

import DataFetcher
import solutions.Solution

class Day7(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 7
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        inputAsList
            .fold(Directory("root", null)) { dir, cmd -> dir.exeCmd(cmd) }
            .getRoot()
            .getSizes()
            .filter { it <= 100000 }
            .sum().toString()

    override fun partTwoResult(): String =
        inputAsList
            .fold(Directory("root", null)) { dir, cmd -> dir.exeCmd(cmd) }
            .getRoot()
            .getSizes()
            .filterWithCollection { list, it -> it >= list.max() - 40000000 }
            .min().toString()


    private fun Directory.exeCmd(cmd: String): Directory =
        when {
            cmd.contains("dir") -> this.addDirectory(cmd.split(" ")[1])
            cmd.first() in ('0'..'9') -> this.updateSize(cmd.split(" ")[0].toInt())
            cmd.contains("$ cd ..") -> this.host!!
            "\\\$ cd [a-z]*".toRegex().matches(cmd) -> this.directories.first { it.name == cmd.split(" ")[2] }
            else -> this
        }

    private fun Directory.getRoot(): Directory =
        if (this.host == null) this
        else this.host!!.getRoot()

    private fun Directory.getSizes(): List<Int> =
        this.directories.fold(listOf<Int>() + this.getTotalSize()) { list, childDir -> list + childDir.getSizes() }

    private fun Directory.getTotalSize(): Int =
        size + directories.sumOf { it.getTotalSize() }

    private fun List<Int>.filterWithCollection(predicate: (List<Int>, Int) -> Boolean): List<Int> =
        this.filter { predicate(this, it) }

    private fun Directory.addDirectory(name: String): Directory {
        this.directories = this.directories + Directory(name, this)
        return this
    }

    private fun Directory.updateSize(size: Int): Directory {
        this.size = this.size + size
        return this
    }
}

data class Directory(var name: String, var host: Directory?) {
    var size: Int = 0
    var directories: List<Directory> = listOf()
}


