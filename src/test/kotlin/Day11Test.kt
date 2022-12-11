import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day11Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInput("Day11_test")

            // when
            val answer = Day11().part1(input)

            // then
            assertThat(answer).isEqualTo(10605)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInput("Day11")

            // when
            val answer = Day11().part1(input)

            // Assert
            assertThat(answer).isEqualTo(76728)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInput("Day11_test")

            // when
            val answer = Day11().part2(input)

            // then
            assertThat(answer).isEqualTo(2713310158)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInput("Day11")

            // when
            val answer = Day11().part2(input)

            // Assert
            assertThat(answer).isEqualTo(21553910156)
        }
    }
}