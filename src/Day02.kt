fun main() {
    fun part1(input: List<String>): Int {
        var (hpos, depth) = Pair(0, 0)
        input.forEach {
            val moves = it.substringAfter(" ").toInt()
            when {
                it.startsWith("forward") -> hpos += moves
                it.startsWith("up") -> depth -= moves
                it.startsWith("down") -> depth += moves
            }
        }
        return hpos * depth
    }

    fun part2(input: List<String>): Int {
        var (hpos, depth, aim) = Triple(0, 0, 0)
        input.forEach {
            val moves = it.substringAfter(" ").toInt()
            when {
                it.startsWith("forward") -> {
                    hpos += moves
                    depth += moves * aim
                }
                it.startsWith("up") -> aim -= moves
                it.startsWith("down") -> aim += moves
            }
        }
        return hpos * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
