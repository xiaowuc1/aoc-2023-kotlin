fun main() {

    fun gcd(a: Long, b: Long): Long {
        if(b == 0L) {
            return a
        }
        return gcd(b, a%b)
    }

    fun part1(input: List<String>): Long {
        var instructions = input[0]
        var edges = HashMap<String, List<String>>()
        for(i in 2 until input.size) {
            var line = input[i].split(" ")
            var node = line[0]
            var lhs = line[2]
            lhs = lhs.substring(1, lhs.length - 1)
            var rhs = line[3]
            rhs = rhs.substring(0, rhs.length-1)
            edges.put(node, listOf(lhs, rhs))
        }
        var ret: Long = 0
        var curr = "AAA"
        while(curr != "ZZZ") {
            var go = instructions[(ret % instructions.length).toInt()]
            var idx = if(go == 'L') 0 else 1
            curr = edges[curr]!![idx]
            ret++
        }
        return ret
    }

    fun part2(input: List<String>): Long {
        var instructions = input[0]
        var edges = HashMap<String, List<String>>()
        for(i in 2 until input.size) {
            var line = input[i].split(" ")
            var node = line[0]
            var lhs = line[2]
            lhs = lhs.substring(1, lhs.length - 1)
            var rhs = line[3]
            rhs = rhs.substring(0, rhs.length-1)
            edges.put(node, listOf(lhs, rhs))
        }
        var ret: Long = 1
        for(cand in edges.keys) {
            if(cand[cand.length-1] != 'A') continue
            var len: Long = 0
            var now = cand
            while(now[now.length-1] != 'Z') {
                var go = instructions[(len % instructions.length).toInt()]
                var idx = if(go == 'L') 0 else 1
                now = edges[now]!![idx]
                len += 1
            }
            ret = ret / gcd(ret, len) * len
        }
        return ret
    }

    // test if implementation meets criteria from the description, like:
    part1(readInput("Day08")).println()
    part2(readInput("Day08")).println()
}
