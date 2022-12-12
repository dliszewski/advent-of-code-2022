import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day12Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day12_test")

            // when
            val answer = Day12().part1(input)

            // then
            assertThat(answer).isEqualTo(31)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day12")

            // when
            val answer = Day12().part1(input)

            // Assert
            assertThat(answer).isEqualTo(380)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day12_test")

            // when
            val answer = Day12().part2(input)

            // then
            assertThat(answer).isEqualTo(29)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day12")

            // when
            val answer = Day12().part2(input)

            // Assert
            assertThat(answer).isEqualTo(375)
        }
    }
}