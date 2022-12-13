import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day13Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInput("Day13_test")

            // when
            val answer = Day13().part1(input)

            // then
            assertThat(answer).isEqualTo(13)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInput("Day13")

            // when
            val answer = Day13().part1(input)

            // Assert
            assertThat(answer).isEqualTo(6072)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInput("Day13_test")

            // when
            val answer = Day13().part2(input)

            // then
            assertThat(answer).isEqualTo(29)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInput("Day13")

            // when
            val answer = Day13().part2(input)

            // Assert
            assertThat(answer).isEqualTo(375)
        }
    }
}