fun main() {

    fun eval(time: Long, distance: Long): Long {
        var ret: Long = 0
        for(i in 0 until time) {
            if(i * (time - i) > distance) {
                ret++
            }
        }
        return ret
    }


    fun part1(input: List<String>): Long {
        var a = input[0].split("\\s+".toRegex())
        var b = input[1].split("\\s+".toRegex())
        var ret: Long = 1
        for(i in 1 until a.size) {
            ret *= eval(a[i].toLong(), b[i].toLong())
        }
        return ret
    }

    fun part2(input: List<String>): Long {
        var a = input[0].split("\\s+".toRegex())
        var b = input[1].split("\\s+".toRegex())
        var aa = ""
        var bb = ""
        for(i in 1 until a.size) {
            aa += a[i]
            bb += b[i]
        }
        return eval(aa.toLong(), bb.toLong())
    }

    // test if implementation meets criteria from the description, like:
    part1(readInput("Day06")).println()
    part2(readInput("Day06")).println()
}
