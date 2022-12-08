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
            val size = input.first().length
            val arr: Array<Array<Tree>> = parseTreeArea(size, input)
            this.trees = arr
            this.size = trees.size - 1
        }

        private fun parseTreeArea(
            size: Int,
            input: List<String>
        ): Array<Array<Tree>> {
            val arr: Array<Array<Tree>> = Array(size) { row -> Array(size) { col -> Tree(0, 0, 0) } }
            input.forEachIndexed { rowId, row ->
                row.forEachIndexed { columnId, column ->
                    arr[rowId][columnId] = Tree(columnId, rowId, Integer.parseInt(column.toString()))
                }
            }
            return arr
        }

        fun getVisibleTrees(): Int {
            return trees.flatMap { row -> row.map { isTreeVisible(it) } }.count { it }
        }

        fun getBestScore(): Int {
            return trees.flatMap { row -> row.map { getTreeScore(it) } }.max()
        }

        private fun isTreeVisible(tree: Tree): Boolean {
            val isVisibleVertically = isVisibleVertically(tree)
            val isVisibleHorizontally = isVisibleHorizontally(tree)
            return isVisibleVertically || isVisibleHorizontally
        }

        private fun isVisibleVertically(tree: Tree): Boolean {
            return if (tree.y == 0 || tree.y == size) {
                true
            } else {
                val visibleTop = (0 until tree.y).all { tree.height > trees[it][tree.x].height }
                val visibleBottom = (tree.y + 1..size).all { tree.height > trees[it][tree.x].height }
                visibleTop || visibleBottom
            }
        }

        private fun isVisibleHorizontally(tree: Tree): Boolean {
            return if (tree.x == 0 || tree.x == size) {
                true
            } else {
                val visibleLeft = (0 until tree.x).all { tree.height > trees[tree.y][it].height }
                val visibleRight = (tree.x + 1..size).all { tree.height > trees[tree.y][it].height }
                visibleLeft || visibleRight
            }
        }


        private fun getTreeScore(tree: Tree): Int {
            val isVisibleVertically = getScoreVertically(tree)
            val isVisibleHorizontally = getScoreHorizontally(tree)
            return isVisibleVertically * isVisibleHorizontally
        }

        private fun getScoreVertically(tree: Tree): Int {
            return if (tree.y == 0 || tree.y == size) {
                1
            } else {
                var scoreTop = 0
                for (y in (tree.y - 1) downTo 0) {
                    val tempTree = trees[y][tree.x]
                    scoreTop++
                    if (tempTree.height >= tree.height) {
                        break
                    }
                }

                var scoreBottom = 0
                for (y in (tree.y + 1)..size) {
                    val t = trees[y][tree.x]
                    scoreBottom++
                    if (t.height >= tree.height) {
                        break
                    }
                }
                scoreTop * scoreBottom
            }
        }

        private fun getScoreHorizontally(tree: Tree): Int {
            return if (tree.x == 0 || tree.x == size) {
                1
            } else {
                var scoreLeft = 0
                for (x in (tree.x - 1) downTo 0) {
                    val tempTree = trees[tree.y][x]
                    scoreLeft++
                    if (tempTree.height >= tree.height) {
                        break
                    }
                }

                var scoreRight = 0
                for (x in (tree.x+1)..size) {
                    val tempTree = trees[tree.y][x]
                    scoreRight++
                    if (tempTree.height >= tree.height) {
                        break
                    }
                }
                scoreLeft * scoreRight
            }
        }

    }

    data class Tree(val x: Int, val y: Int, val height: Int)

}

