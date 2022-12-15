import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day15Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day15_test")

            // when
            val answer = Day15().part1(input, 10)

            // then
            assertThat(answer).isEqualTo(26)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day15")

            // when
            val answer = Day15().part1(input, 2000000)

            // Assert
            assertThat(answer).isEqualTo(4951427)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day15_test")

            // when
            val answer = Day15().part2(input)

            // then
            assertThat(answer).isEqualTo(93)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day15")

            // when
            val answer = Day15().part2(input)

            // Assert
            assertThat(answer).isEqualTo(26831)
        }
    }
}