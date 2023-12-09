fun main() {

    fun eval(init: ArrayList<Long>): Long {
        var ret = ArrayList<ArrayList<Long>>()
        ret.add(init)
        while(true) {
            var valid = true
            for(x in ret.last()) {
                valid = valid && x == 0L
            }
            if(valid) {
                ret.last().add(0)
                break
            }
            var diff = ArrayList<Long>()
            for(i in 0 until ret.last().size-1) {
                diff.add(ret.last()[i+1] - ret.last()[i])
            }
            ret.add(diff)
        }
        for(i in 1 until ret.size) {
            var j = ret.size - i - 1
            ret[j].add(ret[j].last() + ret[j+1].last())
        }
        return ret[0].last()
    }

    fun part1(input: List<String>): Long {
        var ret = 0L
        for(x in input) {
            var line = x.split(" ")
            var init = ArrayList<Long>()
            for(x in line) {
                init.add(x.toLong())
            }
            ret += eval(init)
        }
        return ret
    }

    fun part2(input: List<String>): Long {
        var ret = 0L
        for(x in input) {
            var line = x.split(" ")
            var init = ArrayList<Long>()
            for(x in line) {
                init.add(x.toLong())
            }
            init.reverse()
            ret += eval(init)
        }
        return ret
    }

    // test if implementation meets criteria from the description, like:
    part1(readInput("Day09")).println()
    part2(readInput("Day09")).println()
}
