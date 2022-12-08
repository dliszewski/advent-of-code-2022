class Day08 {

    fun part1(input: List<String>): Int {
        return TreeArea(input).getVisibleTrees()
    }

    fun part2(input: List<String>): Int {
        return TreeArea(input).getBestScore()
    }

    class TreeArea(input: List<String>) {

        private val trees: Array<Array<Tree>>

        private val size: Int

        init {
            val arr: Array<Array<Tree>> = parseTreeArea(input)
            this.trees = arr
            this.size = trees.size - 1
        }

        private fun parseTreeArea(input: List<String>): Array<Array<Tree>> {
            return input.mapIndexed { rowId, row ->
                row.mapIndexed { columnId, height ->
                    Tree(columnId, rowId, height.digitToInt())
                }.toTypedArray()
            }.toTypedArray()
        }

        fun getVisibleTrees(): Int {
            return trees.flatMap { row -> row.map { isTreeVisible(it) } }.count { it }
        }

        fun getBestScore(): Int {
            return trees.flatMap { row -> row.map { getTreeScore(it) } }.max()
        }

        private fun isTreeVisible(tree: Tree): Boolean = isVisibleVertically(tree) || isVisibleHorizontally(tree)

        private fun isVisibleVertically(tree: Tree): Boolean {
            return if (tree.rowId == 0 || tree.rowId == size) {
                true
            } else {
                val visibleTop = (tree.rowId - 1 downTo 0).all { tree.height > trees[it][tree.columnId].height }
                val visibleBottom = (tree.rowId + 1..size).all { tree.height > trees[it][tree.columnId].height }
                visibleTop || visibleBottom
            }
        }

        private fun isVisibleHorizontally(tree: Tree): Boolean {
            return if (tree.columnId == 0 || tree.columnId == size) {
                true
            } else {
                val visibleLeft = (tree.columnId - 1 downTo 0).all { tree.height > trees[tree.rowId][it].height }
                val visibleRight = (tree.columnId + 1..size).all { tree.height > trees[tree.rowId][it].height }
                visibleLeft || visibleRight
            }
        }

        private fun getTreeScore(tree: Tree): Int = getScoreVertically(tree) * getScoreHorizontally(tree)

        private fun getScoreVertically(tree: Tree): Int {
            return if (tree.rowId == 0 || tree.rowId == size) {
                1
            } else {
                val scoreTop = (tree.rowId - 1 downTo 0)
                    .map { trees[it][tree.columnId] }
                    .takeUntil { it.height >= tree.height }.count()
                val scoreBottom = (tree.rowId + 1..size)
                    .map { trees[it][tree.columnId] }
                    .takeUntil { it.height >= tree.height }.count()
                scoreTop * scoreBottom
            }
        }

        private fun getScoreHorizontally(tree: Tree): Int {
            return if (tree.columnId == 0 || tree.columnId == size) {
                1
            } else {
                val scoreLeft = (tree.columnId - 1 downTo 0)
                    .map { trees[tree.rowId][it] }
                    .takeUntil { it.height >= tree.height }.count()
                val scoreRight = (tree.columnId + 1..size)
                    .map { trees[tree.rowId][it] }
                    .takeUntil { it.height >= tree.height }.count()
                scoreLeft * scoreRight
            }
        }
    }

    data class Tree(val columnId: Int, val rowId: Int, val height: Int)

}

private fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = ArrayList<T>()
    for (item in this) {
        list.add(item)
        if (predicate(item))
            break
    }
    return list
}

