class Day08 {

    fun part1(input: List<String>): Int {
        val size = input.first().length
        val arr: Array<Array<Tree>> = Array(size) { row -> Array(size) { col -> Tree(0, 0, 0) } }

        input.forEachIndexed { rowId, row ->
            row.forEachIndexed { columnId, column ->
                arr[rowId][columnId] = Tree(columnId, rowId, Integer.parseInt(column.toString()))
            }
        }

        val treeArea = TreeArea(arr)
        return treeArea.getVisibleTrees()
    }


    class TreeArea(private val trees: Array<Array<Tree>>) {

        private val size: Int = trees.size - 1

        fun getVisibleTrees(): Int {
            var count = 0
            trees.forEach { row -> row.forEach { if(isTreeVisible(it)) count++ } }
            return count
        }

        private fun isTreeVisible(tree: Tree): Boolean {
            val isVisibleVertically = isVisibleVertically(tree)
            val isVisibleHorizontally = isVisibleHorizontally(tree)
            return isVisibleVertically || isVisibleHorizontally
        }

        private fun isVisibleHorizontally(tree: Tree): Boolean {
            return if (tree.x == 0 || tree.x == size) {
                true
            } else {
                val visibleLeft = (0 until tree.x).all { tree.height > trees[tree.y][it].height }
                val visibleRight = (tree.x+1..size).all { tree.height > trees[tree.y][it].height }
                visibleLeft || visibleRight
            }
        }

        private fun isVisibleVertically(tree: Tree): Boolean {
            return if (tree.y == 0 || tree.y == size) {
                true
            } else {
                val visibleTop = (0 until tree.y).all { tree.height > trees[it][tree.x].height }
                val visibleBottom = (tree.y+1..size).all { tree.height > trees[it][tree.x].height }
                visibleTop || visibleBottom
            }
        }

    }
    data class Tree(val x: Int, val y: Int, val height: Int) {}

    fun part2(input: List<String>): Long {
        return 1
    }

}

