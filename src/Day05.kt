class IntervalMeta(val dest: Long, val start: Long, val size: Long) {
}

class Interval(val lhs: Long, val rhs: Long) {
}

fun main() {

    fun dfs(maps: ArrayList<ArrayList<IntervalMeta>>, idx: Int, currInterval: Interval): Long {
        if(idx == maps.size) {
            return currInterval.lhs
        }
        var ret = Long.MAX_VALUE
        var intervals = ArrayList<Interval>()
        intervals.add(currInterval)
        for(meta in maps[idx]) {
            val shift = meta.dest - meta.start
            var nintervals = ArrayList<Interval>()
            for(i in intervals) {
                var candInterval = Interval(
                    maxOf(i.lhs, meta.start),
                    minOf(i.rhs, meta.start + meta.size - 1)
                )
                if(candInterval.lhs > candInterval.rhs) {
                    nintervals.add(i)
                    continue
                }
                ret = minOf(ret, dfs(maps, idx+1, Interval(shift + candInterval.lhs, shift + candInterval.rhs)))
                if(candInterval.lhs > i.lhs) {
                    nintervals.add(Interval(i.lhs, candInterval.lhs - 1))
                }
                if(candInterval.rhs < i.rhs) {
                    nintervals.add(Interval(candInterval.rhs+1, i.rhs))
                }
            }
            intervals = nintervals
        }
        for(i in intervals) {
            ret = minOf(ret, dfs(maps, idx+1, i))
        }
        return ret
    }

    fun part1(input: List<String>): Long {
        var seeds = ArrayList<Long>()
        var seedStr = input[0].split("\\s+".toRegex())
        for(i in 1 until seedStr.size) {
            seeds.add(seedStr[i].toLong())
        }
        input.removeFirst();
        input.removeFirst();
        var maps = ArrayList<ArrayList<IntervalMeta>>()
        while(input.size > 0) {
            var intervals = ArrayList<IntervalMeta>()
            input.removeFirst();
            while(input.size > 0) {
                if(input[0].length == 0) {
                    input.removeFirst();
                    break;
                }
                var line = input[0].split(" ");
                input.removeFirst();
                intervals.add(IntervalMeta(line[0].toLong(), line[1].toLong(), line[2].toLong()))
            }
            maps.add(intervals)
        }
        var ret = Long.MAX_VALUE
        for(seed in seeds) {
            ret = minOf(ret, dfs(maps, 0, Interval(seed, seed)))
        }
        return ret
    }

    fun part2(input: List<String>): Long {
        var seeds = ArrayList<Long>()
        var seedStr = input[0].split("\\s+".toRegex())
        for(i in 1 until seedStr.size) {
            seeds.add(seedStr[i].toLong())
        }
        input.removeFirst();
        input.removeFirst();
        var maps = ArrayList<ArrayList<IntervalMeta>>()
        while(input.size > 0) {
            var intervals = ArrayList<IntervalMeta>()
            input.removeFirst();
            while(input.size > 0) {
                if(input[0].length == 0) {
                    input.removeFirst();
                    break;
                }
                var line = input[0].split(" ");
                input.removeFirst();
                intervals.add(IntervalMeta(line[0].toLong(), line[1].toLong(), line[2].toLong()))
            }
            maps.add(intervals)
        }
        var ret = Long.MAX_VALUE
        var i = 0
        while(i < seeds.size) {
            ret = minOf(ret, dfs(maps, 0, Interval(seeds[i], seeds[i] + seeds[i+1] - 1)))
            i += 2
        }
        return ret
    }

    // test if implementation meets criteria from the description, like:
    part1(readInput("Day05")).println()
    part2(readInput("Day05")).println()
}