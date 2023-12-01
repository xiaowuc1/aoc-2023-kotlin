fun main() {

    fun p1(s: String): Int {
        var digits = ""
        for (x in s) {
            if (Character.isDigit(x)) {
                digits += x
            }
        }
        return 10 * (digits[0] - '0') + (digits[digits.length-1] - '0')
    }

    fun p2(s: String): Int {
        var digits = ""
        val words = arrayOf<String>("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        var i = 0
        while (i < s.length) {
            var found = false
            for (idx in 0 until words.size) {
                if (i + words[idx].length <= s.length && s.substring(i, i + words[idx].length) == words[idx]) {
                    digits += Character.toString('1' + idx)
                    found = true
                    break
                }
            }
            if (Character.isDigit(s[i])) {
                digits += s[i]
            }
            i += 1
        }
        return 10 * (digits[0] - '0') + (digits[digits.length-1] - '0')
    }

    fun part1(input: List<String>): Int {
        var ret = 0
        for (s in input) {
            ret += p1(s)
        }
        return ret
    }

    fun part2(input: List<String>): Int {
        var ret = 0
        for (s in input) {
            ret += p2(s)
        }
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
