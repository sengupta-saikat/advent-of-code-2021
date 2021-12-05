fun main() {
    fun part1(input: List<Int>): Int {
        var counter = 0
        for (i in 0 until input.size - 1) if (input[i + 1] - input[i] > 0) counter++
        return counter
    }

    fun part1Idiomatic(input: List<Int>): Int {
        return input.windowed(2).count { (a, b) -> b > a }
    }

    fun part2(input: List<Int>): Int {
        var counter = 0
        for (i in 0 until input.size - 3) {
            val window1 = input[i + 2] + input[i + 1] + input[i]
            val window2 = input[i + 3] + input[i + 2] + input[i + 1]
            if (window2 - window1 > 0) counter++
        }
        return counter
    }

    fun part2Idiomatic(input: List<Int>): Int {
        return input.windowed(3).windowed(2).count { (a, b) -> b.sum() > a.sum() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsInt("Day01_test")
    check(part1(testInput) == 7)
    check(part1Idiomatic(testInput) == 7)
    check(part2(testInput) == 5)
    check(part2Idiomatic(testInput) == 5)

    val input = readInputAsInt("Day01")
    // println(part1(input))
    println(part1Idiomatic(input))
    // println(part2(input))
    println(part2Idiomatic(input))
}
