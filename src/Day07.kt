fun main() {

    fun getFrequencyCount(s: String, joker: Boolean): ArrayList<Int> {
        var dp = HashMap<Char, Int>()
        var jokercount = 0
        for(i in 0..4) {
            var x = s[i]
            if(joker && x == 'J') {
                jokercount++
            }
            else {
                dp.put(x, dp.getOrDefault(x, 0) + 1)
            }
        }
        var ret = ArrayList<Int>()
        for(x in dp.keys) {
            ret.add(dp[x]!!)
        }
        ret.sort()
        if(ret.size == 0) {
            ret.add(5)
        }
        else {
            ret[ret.size-1] += jokercount
        }
        return ret
    }

    fun getPriority(s: String, joker: Boolean): Int {
        var dp = getFrequencyCount(s, joker)
        if(dp.size == 1 && dp[0] == 5) {
            return 6
        }
        if(dp.size == 2 && dp[1] == 4) {
            return 5
        }
        if(dp.size == 2 && dp[1] == 3) {
            return 4
        }
        if(dp[dp.size-1] == 3) {
            return 3
        }
        if(dp[dp.size-1] == 2 && dp[dp.size-2] == 2) {
            return 2
        }
        if(dp[dp.size-1] == 2) {
            return 1
        }
        return 0
    }

    fun getCardValue(alpha: String, c: Char): Int {
        var ret = 0
        while(alpha[ret] != c) {
            ret += 1
        }
        return ret
    }

    fun solve(alpha: String, input: List<String>, joker: Boolean): Long {
        var sinput = input.sortedWith <String> (object : Comparator<String> {
            override fun compare(a: String, b: String): Int {
                var aprio = getPriority(a, joker)
                var bprio = getPriority(b, joker)
                if(aprio != bprio) {
                    return aprio - bprio
                }
                for(i in 0..4) {
                    aprio = getCardValue(alpha, a[i])
                    bprio = getCardValue(alpha, b[i])
                    if(aprio != bprio) {
                        return bprio - aprio
                    }
                }
                return 0
            }
        })
        var ret: Long = 0
        for(i in 0 until sinput.size) {
            var line = sinput[i].split(" ")
            ret += (i+1) * line[1].toLong()
        }
        return ret
    }

    fun part1(input: List<String>): Long {
        return solve("AKQJT98765432", input, false)
    }

    fun part2(input: List<String>): Long {
        return solve("AKQT98765432J", input, true)
    }

    // test if implementation meets criteria from the description, like:
    part1(readInput("Day07")).println()
    part2(readInput("Day07")).println()
}
