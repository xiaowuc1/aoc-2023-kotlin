fun main() {

    fun part1(input: List<String>): Int {
        var ret = 0
        for(s in input) {
            var l = s.split("\\s+".toRegex())
            var winners = HashSet<Int>()
            var iswinner = true
            var amt = 0
            for(sidx in 2 until l.size) {
                if(l[sidx] == "") {
                    continue
                }
                if(l[sidx] == "|") {
                    iswinner = false
                }
                else {
                    if(iswinner) {
                        winners.add(l[sidx].toInt())
                    }
                    else {
                        if(winners.contains(l[sidx].toInt())) {
                            amt++
                        }
                    }
                }
            }
            if(amt > 0) {
                ret += 1 shl (amt - 1);
            }
        }
        return ret
    }

    fun part2(input: List<String>): Int {
        var ret = 0
        var cards = ArrayList<Int>()
        for(i in 0 until input.size) {
            cards.add(1)
        }
        for(i in 0 until input.size) {
            var s = input[i]
            var l = s.split("\\s+".toRegex())
            var winners = HashSet<Int>()
            var iswinner = true
            var amt = 0
            for(sidx in 2 until l.size) {
                if(l[sidx] == "|") {
                    iswinner = false
                }
                else {
                    if(iswinner) {
                        winners.add(l[sidx].toInt())
                    }
                    else {
                        if(winners.contains(l[sidx].toInt())) {
                            amt++
                        }
                    }
                }
            }
            for(j in 0 until amt) {
                cards[i+j+1] += cards[i]
            }
        }
        for(x in cards) {
            ret += x
        }
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}