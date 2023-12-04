fun main() {

    fun part1(input: List<String>): Int {
        var ret = 0
        var idx = 0
        for(s in input) {
            idx += 1
            var sidx = 2
            var tokens = s.split(" ")
            var good = true
            while(sidx < tokens.size) {
                var amt = tokens[sidx++].toInt()
                var color = tokens[sidx++]
                if(color[0] == 'r' && amt > 12) {
                    good = false
                }
                if(color[0] == 'g' && amt > 13) {
                    good = false
                }
                if(color[0] == 'b' && amt > 14) {
                    good = false
                }
            }
            if(good) {
                ret += idx
            }
        }
        return ret
    }

    fun part2(input: List<String>): Int {
        var ret = 0
        var idx = 0
        for(s in input) {
            idx += 1
            var sidx = 2
            var tokens = s.split(" ")
            var r = 0
            var g = 0
            var b = 0
            while(sidx < tokens.size) {
                var amt = tokens[sidx++].toInt()
                var color = tokens[sidx++]
                if(color[0] == 'r') {
                    r = maxOf(r, amt)
                }
                if(color[0] == 'g') {
                    g = maxOf(g, amt)
                }
                if(color[0] == 'b') {
                    b = maxOf(b, amt)
                }
            }
            ret += r*g*b
        }
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}