import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day08Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day08_test")

            // when
            val answer = Day08().part1(input)

            // then
            assertThat(answer).isEqualTo(21)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day08")

            // when
            val answer = Day08().part1(input)

            // Assert
            assertThat(answer).isEqualTo(1785)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // given
            val input = readInputLines("Day08_test")

            // when
            val answer = Day08().part2(input)

            // then
            assertThat(answer).isEqualTo(24933642)
        }

        @Test
        fun `Actual answer`() {
            // given
            val input = readInputLines("Day08")

            // when
            val answer = Day08().part2(input)

            // Assert
            assertThat(answer).isEqualTo(1111607)
        }
    }
}