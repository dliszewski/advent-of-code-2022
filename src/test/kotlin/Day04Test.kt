import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day04Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day04_test")

            // when
            val answer = Day04().part1(input)

            // then
            assertThat(answer).isEqualTo(2)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day04")

            // when
            val answer = Day04().part1(input)

            // Assert
            assertThat(answer).isEqualTo(524)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day04_test")

            // when
            val answer = Day04().part2(input)

            // then
            assertThat(answer).isEqualTo(4)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day04")

            // when
            val answer = Day04().part2(input)

            // Assert
            assertThat(answer).isEqualTo(798)
        }
    }
}