import kotlin.test.Test
import kotlin.test.assertEquals

class TestClient {
    @Test
    fun testGreet() {
        assertEquals("world", greet())
        assertEquals("world 1", greet())
    }

    @Test
    fun test2() {
        assertEquals(1, 2)
    }
} 
