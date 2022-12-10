import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day10Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day10_test")

            // when
            val answer = Day10().part1(input)

            // then
            assertThat(answer).isEqualTo(13140)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day10")

            // when
            val answer = Day10().part1(input)

            // Assert
            assertThat(answer).isEqualTo(12560)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day10_test")

            // when
            val answer = Day10().part2(input)

            // then
            assertThat(answer).isEqualTo("""
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....
            """.trimIndent())
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day10")

            // when
            val answer = Day10().part2(input)

            // Assert
            assertThat(answer).isEqualTo("""
                ###..#....###...##..####.###...##..#....
                #..#.#....#..#.#..#.#....#..#.#..#.#....
                #..#.#....#..#.#..#.###..###..#....#....
                ###..#....###..####.#....#..#.#....#....
                #....#....#....#..#.#....#..#.#..#.#....
                #....####.#....#..#.#....###...##..####.
            """.trimIndent())
        }
    }
}