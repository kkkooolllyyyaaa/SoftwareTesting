package task2

import org.junit.jupiter.api.Test

class OurHashMapTest {
    @Test
    fun test1(){
        val map = OurHashMap()
        map.put(1,1)
        map.put(3,3)
        map.put(4,4)
        map.put(0,0)
        for(i in map.buckets){
            println(i!!.`val`)
        }
    }
}