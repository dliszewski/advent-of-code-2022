import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day05Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInput("Day05_test")

            // when
            val answer = Day05().part1(input)

            // then
            assertThat(answer).isEqualTo("CMZ")
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInput("Day05")

            // when
            val answer = Day05().part1(input)

            // Assert
            assertThat(answer).isEqualTo("VRWBSFZWM")
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInput("Day05_test")

            // when
            val answer = Day05().part2(input)

            // then
            assertThat(answer).isEqualTo("MCD")
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInput("Day05")

            // when
            val answer = Day05().part2(input)

            // Assert
            assertThat(answer).isEqualTo("RBTWJWMCF")
        }
    }
}