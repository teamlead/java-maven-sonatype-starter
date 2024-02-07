package pro.teamlead.demo.sonatype;

/**
 * The {@code Component} class provides utility methods for arithmetic operations.
 * This class demonstrates how to handle arithmetic overflow by throwing an exception.
 */
public class IntAdder {

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * This constructor is private to prevent the instantiation of this utility class.
     * Instantiating utility classes, which are usually a collection of static methods, does not make sense.
     * Therefore, this constructor throws an exception to enforce the non-instantiability of the class.
     *
     * @throws IllegalStateException if this constructor is called, signaling incorrect usage of the class.
     */
    private IntAdder() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Sums two integers and checks for overflow.
     * <p>
     * If adding the two integers results in an overflow, this method throws an
     * {@link ArithmeticException}. This method demonstrates how to manually handle
     * overflow conditions in integer arithmetic, which is not automatically checked
     * by Java's standard arithmetic operations.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the sum of {@code a} and {@code b}
     * @throws ArithmeticException if the result overflows the range of integers
     */
    public static int sum(int a, int b) {

        // Check if the result overflows positive limit
        if (a > 0 && b > Integer.MAX_VALUE - a) {
            throw new ArithmeticException("Positive overflow in sum");
        }
        // Check if the result overflows negative limit
        if (a < 0 && b < Integer.MIN_VALUE - a) {
            throw new ArithmeticException("Negative overflow in sum");
        }
        return a + b;
    }
}
