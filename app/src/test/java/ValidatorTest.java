import hexlet.code.Validator;
import hexlet.code.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    StringSchema schema;

    @BeforeEach
    public void setUp()  {
        Validator v = new Validator();
        schema = v.string();
    }

    @Test
    public void testWithNullAndEmpty() {
        assertTrue(schema.isValid("")); // true
        assertTrue(schema.isValid(null)); // true
    }

    @Test
    public void testRequired() {
        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("")); // false
        assertFalse(schema.isValid(5)); // false
        assertTrue(schema.isValid("what does the fox say")); // true
        assertTrue(schema.isValid("hexlet")); // true
    }

    @Test
    public void testMinLength() {
        schema.minLength(4);
        assertTrue(schema.isValid("what does the fox say")); // true
        assertFalse(schema.isValid("hex")); // false
    }

    @Test
    public void testContains() {
        assertTrue(schema.contains("wh").isValid("what does the fox say")); // true
        assertTrue(schema.contains("what").isValid("what does the fox say")); // true
        assertFalse(schema.contains("what the").isValid("what does the fox say")); // false

        assertFalse(schema.isValid("what does the fox say")); // false
    }
}
