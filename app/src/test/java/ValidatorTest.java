import hexlet.code.NumberSchema;
import hexlet.code.Validator;
import hexlet.code.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    StringSchema stringSchema;
    NumberSchema numberSchema;

    @BeforeEach
    public void setUp() {
        Validator v = new Validator();
        stringSchema = v.string();
        numberSchema = v.number();
    }

    @Test
    public void testWithNullAndEmpty() {
        assertFalse(stringSchema.isValid(5)); // false

        assertTrue(stringSchema.isValid(null)); // true
        assertTrue(stringSchema.isValid("")); // true
        assertTrue(numberSchema.isValid(null)); // true
    }

    @Test
    public void testRequired() {
        stringSchema.required();

        assertFalse(stringSchema.isValid(null)); // false
        assertFalse(stringSchema.isValid("")); // false
        assertFalse(stringSchema.isValid(5)); // false
        assertTrue(stringSchema.isValid("what does the fox say")); // true
        assertTrue(stringSchema.isValid("hexlet")); // true
    }

    @Test
    public void testMinLength() {
        stringSchema.minLength(4);
        assertTrue(stringSchema.isValid("what does the fox say")); // true
        assertFalse(stringSchema.isValid("hex")); // false
    }

    @Test
    public void testContains() {
        assertTrue(stringSchema.contains("wh").isValid("what does the fox say")); // true
        assertTrue(stringSchema.contains("what").isValid("what does the fox say")); // true
        assertFalse(stringSchema.contains("what the").isValid("what does the fox say")); // false

        assertFalse(stringSchema.isValid("what does the fox say")); // false
    }

    @Test
    public void testRequired1() {
        numberSchema.required();

        assertFalse(numberSchema.isValid(null)); // false
    }

    @Test
    public void testPositive() {
        numberSchema.positive();

        assertTrue(numberSchema.isValid(10)); // true
        assertFalse(numberSchema.isValid(-10)); // false
        assertFalse(numberSchema.isValid(0)); // false
    }

    @Test
    public void testRange() {
        numberSchema.range(5, 10);

        assertTrue(numberSchema.isValid(5)); // true
        assertTrue(numberSchema.isValid(10)); // true
        assertFalse(numberSchema.isValid(4)); // false
        assertFalse(numberSchema.isValid(11)); // false
    }
}
