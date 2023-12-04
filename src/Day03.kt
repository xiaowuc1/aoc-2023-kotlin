fun main() {

    fun part1(input: List<String>): Int {
        var r = input.size
        var c = input[0].length
        var ret = 0
        for(i in 0 until r) {
            var j = 0
            while(j < c) {
                if(!input[i][j].isDigit()) {
                    j += 1
                    continue
                }
                var k = j
                var numval = 0
                while(k < c && input[i][k].isDigit()) {
                    numval = 10 * numval + (input[i][k++] - '0')
                }
                var adj = false
                for(a in maxOf(0, j-1) until minOf(c, k+1)) {
                    if(i-1 >= 0 && !input[i-1][a].isDigit() && input[i-1][a] != '.') {
                        adj = true
                    }
                    if(!input[i][a].isDigit() && input[i][a] != '.') {
                        adj = true
                    }
                    if(i+1 < r && !input[i+1][a].isDigit() && input[i+1][a] != '.') {
                        adj = true
                    }
                }
                if(adj) {
                    ret += numval
                }
                j = k
            }
        }
        return ret
    }

    fun part2(input: List<String>): Int {
        var r = input.size
        var c = input[0].length
        var ret = 0
        var adjs = ArrayList<ArrayList<Int>>()
        for(i in 0 until r*c) {
            adjs.add(ArrayList<Int>())
        }
        for(i in 0 until r) {
            var j = 0
            while(j < c) {
                if(!input[i][j].isDigit()) {
                    j += 1
                    continue
                }
                var k = j
                var numval = 0
                while(k < c && input[i][k].isDigit()) {
                    numval = 10 * numval + (input[i][k++] - '0')
                }
                for(a in maxOf(0, j-1) until minOf(c, k+1)) {
                    if(i-1 >= 0 && input[i-1][a] == '*') {
                        adjs[(i-1)*c+a].add(numval)
                    }
                    if(input[i][a] == '*') {
                        adjs[i*c+a].add(numval)
                    }
                    if(i+1 < r && input[i+1][a] == '*') {
                        adjs[(i+1)*c+a].add(numval)
                    }
                }
                j = k
            }
        }
        for (i in 0 until adjs.size) {
            if(adjs[i].size == 2) {
                ret += adjs[i][0] * adjs[i][1]
            }
        }
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}