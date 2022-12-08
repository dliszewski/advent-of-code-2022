class Day07 {

    fun part1(input: List<String>): Long {
        val hashMapOf = directoriesMap(input)
        return hashMapOf.values.filter { it <= 100_000 }.sum()
    }

    private fun directoriesMap(input: List<String>): MutableMap<String, Long> {
        val hashMapOf = mutableMapOf<String, Long>()
        var path = ""

        for (line in input) {
            when (line.getTyp()) {
                CommandType.COMMAND_CD_ROOT -> path = ""
                CommandType.COMMAND_CD_UP -> {
                    path = path.substringBeforeLast("/")
                }
                CommandType.COMMAND_CD -> {
                    val tempPath = line.substringAfter("$ cd ")
                    path = "$path/$tempPath"
                }
                CommandType.COMMAND_DIR -> ""
                CommandType.DIR -> ""
                CommandType.SIZE -> {
                    val tempSize = line.substringBefore(" ").toInt()
                    var tempDir = path
                    while (true) {
                        val previous = hashMapOf.getOrDefault(tempDir, 0)
                        hashMapOf[tempDir] = previous + tempSize
                        if (tempDir.isEmpty()) break
                        tempDir = tempDir.substringBeforeLast("/", "")
                    }
                }
            }

        }
        return hashMapOf
    }

    private enum class CommandType { COMMAND_CD_ROOT, COMMAND_CD, COMMAND_CD_UP, COMMAND_DIR, DIR, SIZE }

    private fun String.getTyp(): CommandType {
        return when {
            this.startsWith("$ cd /") -> CommandType.COMMAND_CD_ROOT
            this.startsWith("$ cd ..") -> CommandType.COMMAND_CD_UP
            this.startsWith("$ cd ") -> CommandType.COMMAND_CD
            this.startsWith("$ ls") -> CommandType.COMMAND_DIR
            this.startsWith("dir ") -> CommandType.DIR
            else -> CommandType.SIZE
        }

    }

    fun part2(input: List<String>): Long {
        val total = 70_000_000
        val required = 30_000_000
        val directoriesMap = directoriesMap(input)
        val used = directoriesMap.getOrDefault("", 0)
        val unusedSpace = total - used
        val deficit = required - unusedSpace

        return directoriesMap.values.filter { it >= deficit }.min()
    }

}

