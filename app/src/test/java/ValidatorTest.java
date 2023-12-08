import hexlet.code.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    Validator v;
    StringSchema stringSchema;
    NumberSchema numberSchema;
    MapSchema mapSchema;

    @BeforeEach
    public void setUp() {
        v = new Validator();
        stringSchema = v.string();
        numberSchema = v.number();
        mapSchema = v.map();
    }

    @Test
    public void testWithNullAndEmpty() {
        assertFalse(stringSchema.isValid(5)); // false

        assertTrue(stringSchema.isValid(null)); // true
        assertTrue(stringSchema.isValid("")); // true
        assertTrue(numberSchema.isValid(null)); // true
        assertTrue(mapSchema.isValid(null)); // true
    }

    @Test
    public void testRequired() {
        stringSchema.required();
        assertFalse(stringSchema.isValid(null)); // false
        assertFalse(stringSchema.isValid("")); // false
        assertFalse(stringSchema.isValid(5)); // false
        assertTrue(stringSchema.isValid("what does the fox say")); // true
        assertTrue(stringSchema.isValid("hexlet")); // true

        numberSchema.required();
        assertFalse(numberSchema.isValid(null)); // false

        mapSchema.required();
        assertFalse(mapSchema.isValid(null)); // false
        assertTrue(mapSchema.isValid(new HashMap<>())); // true
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

    @Test
    public void testWithMapSize() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(mapSchema.isValid(data)); // true

        mapSchema.sizeof(2);
        assertFalse(mapSchema.isValid(data));  // false
        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data)); // true
    }

    @Test
    public void testWithMapValue() {
// shape позволяет описывать валидацию для значений каждого ключа объекта Map
// создаем набор схем для проверки каждого ключа проверяемого объекта
// Для значения каждого ключа - своя схема
        Map<String, BaseSchema> schemas = new HashMap<>();

// Определяем схемы валидации для значений свойств "name" и "age"
// Имя должно быть строкой, обязательно для заполнения
        schemas.put("name", stringSchema.required());
// Возраст должен быть положительным числом
        schemas.put("age", numberSchema.positive());
// Настраиваем схему `MapSchema`
// Передаем созданный набор схем в метод shape()

        mapSchema.shape(schemas);

        // Проверяем объекты
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(mapSchema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(mapSchema.isValid(human2)); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(mapSchema.isValid(human3)); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(mapSchema.isValid(human4)); // false
    }


}
