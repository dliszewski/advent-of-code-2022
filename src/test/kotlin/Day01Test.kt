import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day01Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInput("Day01_test")

            // when
            val answer = Day01().part1(input)

            // then
            assertThat(answer).isEqualTo(24000)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInput("Day01")

            // when
            val answer = Day01().part1(input)

            // Assert
            assertThat(answer).isEqualTo(69177)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInput("Day01_test")

            // when
            val answer = Day01().part2(input)

            // then
            assertThat(answer).isEqualTo(45000)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInput("Day01")

            // when
            val answer = Day01().part2(input)

            // Assert
            assertThat(answer).isEqualTo(207456)
        }
    }
}