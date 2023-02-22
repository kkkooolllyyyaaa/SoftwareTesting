package task2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.stream.IntStream
import kotlin.math.abs

class OurBucketSortMapTest {
    @Test
    fun `getSorted capacity is huge`() {
        val map = OurBucketSortMap(10)
        map.put(1, 2)
        map.put(3, 4)
        map.put(4, 5)
        map.put(0, 1)

        assertThat(map.getSortedFull().toString()).isEqualTo(
            "[(0, 1), (1, 2), (3, 4), (4, 5)]"
        )
    }

    @Test
    fun `getSorted when only one bucket`() {
        val map = OurBucketSortMap(1)
        map.put(4, 4)
        map.put(3, 3)
        map.put(1, 1)
        map.put(2, 2)

        assertThat(map.getSortedFull().toString()).isEqualTo(
            "[(1, 1), (2, 2), (3, 3), (4, 4)]"
        )
    }

    @Test
    fun `getSortedKeys hard test`() {
        val capacity = 1000
        val map = OurBucketSortMap(capacity)
        val alreadySorted = mutableSetOf<Int>()

        IntStream.range(0, 30).forEach {
            val calculated = (abs(kotlin.random.Random.nextInt() + it)) % capacity
            alreadySorted.add(calculated)
            map.put(calculated, 0)
        }

        assertThat(
            alreadySorted.toList().sorted()
        ).isEqualTo(
            map.getSortedKeys()
        )
    }

    @Test
    fun `getSorted get value works`() {
        val map = OurBucketSortMap(10)
        map.put(1, 2)
        assertThat(map.get(1)).isEqualTo(2)

        map.put(1, 3)
        assertThat(map.get(1)).isEqualTo(3)

        map.put(1, 4)
        assertThat(map.get(1)).isEqualTo(4)
    }
}
