import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day09Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day09_test")

            // when
            val answer = Day09().part1(input)

            // then
            assertThat(answer).isEqualTo(13)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day09")

            // when
            val answer = Day09().part1(input)

            // Assert
            assertThat(answer).isEqualTo(5878)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day09_test")

            // when
            val answer = Day09().part2(input)

            // then
            assertThat(answer).isEqualTo(1)
        }

        @Test
        fun `Matches example 2`() {
            // given
            val input = """
                R 5
                U 8
                L 8
                D 3
                R 17
                D 10
                L 25
                U 20
            """.trimIndent().lines()

            // when
            val answer = Day09().part2(input)

            // then
            assertThat(answer).isEqualTo(36)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day09")

            // when
            val answer = Day09().part2(input)

            // Assert
            assertThat(answer).isEqualTo(2405)
        }
    }
}