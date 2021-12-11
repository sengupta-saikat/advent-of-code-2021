
fun main() {
    fun part1(input: List<String>): Int {
        val columns = input[0].indices
        val gammaRate = columns
            .map { input.countBitsInColumn(it) }
            .joinToString("") { (zeroes, ones) ->
                if (zeroes > ones) "0" else "1"
            }
        val epsilonRate = gammaRate.invertBinaryString()
        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    fun part2(input: List<String>): Int {
        fun rating(ratingType: RatingType): String {
            val columns = input[0].indices
            val candidates = input.toMutableList()
            for (column in columns) {
                val (zeroes, ones) = candidates.countBitsInColumn(column)
                val mostCommonBit = if (zeroes > ones) '0' else '1'
                candidates.removeIf {
                    when (ratingType) {
                        RatingType.OXYGEN -> it[column] == mostCommonBit
                        RatingType.CO2 -> it[column] != mostCommonBit
                    }
                }
                if (candidates.size == 1) break
            }
            return candidates.first()
        }
        return rating(RatingType.OXYGEN).toInt(2) * rating(RatingType.CO2).toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

private enum class RatingType {
    OXYGEN,
    CO2
}

private fun String.invertBinaryString() = this
    .asIterable()
    .joinToString("") { if (it == '0') "1" else "0" }

private fun List<String>.countBitsInColumn(column: Int): BitCount {
    var zeroes = 0
    var ones = 0
    for (line in this) {
        if (line[column] == '0') zeroes++ else ones++
    }
    return BitCount(zeroes, ones)
}

data class BitCount(val zeroes: Int, val ones: Int)

