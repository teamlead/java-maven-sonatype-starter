package pro.teamlead.demo.sonatype;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class IntAdderTest {

    @Test
    void testSumWithNormalValues() {
        // Test normal addition
        assertEquals(5, IntAdder.sum(2, 3), "Sum of 2 and 3 should be 5");
        assertEquals(-1, IntAdder.sum(-3, 2), "Sum of -3 and 2 should be -1");
        assertEquals(0, IntAdder.sum(-3, 3), "Sum of -3 and 3 should be 0");
    }

    @Test
    void testSumWithZero() {
        // Test addition involving zero
        assertEquals(2, IntAdder.sum(2, 0), "Sum of 2 and 0 should be 2");
        assertEquals(-3, IntAdder.sum(0, -3), "Sum of 0 and -3 should be -3");
        assertEquals(0, IntAdder.sum(0, 0), "Sum of 0 and 0 should be 0");
    }

    @Test
    void testSumWithPositiveOverflow() {
        // Test positive overflow
        assertThrows(ArithmeticException.class, () -> IntAdder.sum(Integer.MAX_VALUE, 1),
                "Sum of Integer.MAX_VALUE and 1 should throw ArithmeticException due to positive overflow");
    }

    @Test
    void testSumWithNegativeOverflow() {
        // Test negative overflow
        assertThrows(ArithmeticException.class, () -> IntAdder.sum(Integer.MIN_VALUE, -1),
                "Sum of Integer.MIN_VALUE and -1 should throw ArithmeticException due to negative overflow");
    }

    @Test
    void testComponentClassInstantiation() {
        Throwable exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<IntAdder> constructor = IntAdder.class.getDeclaredConstructor();
            constructor.setAccessible(true); // Makes the private constructor accessible
            constructor.newInstance(); // This line should throw InvocationTargetException
        }, "Utility class should not be instantiated").getCause();

        assertTrue(exception instanceof IllegalStateException, "Expected IllegalStateException to be thrown");
    }
}
