import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day14Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day14_test")

            // when
            val answer = Day14().part1(input)

            // then
            assertThat(answer).isEqualTo(24)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day14")

            // when
            val answer = Day14().part1(input)

            // Assert
            assertThat(answer).isEqualTo(672)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day14_test")

            // when
            val answer = Day14().part2(input)

            // then
            assertThat(answer).isEqualTo(140)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day14")

            // when
            val answer = Day14().part2(input)

            // Assert
            assertThat(answer).isEqualTo(22184)
        }
    }
}