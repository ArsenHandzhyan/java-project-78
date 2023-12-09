package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    @Override
    public boolean isValid(Object value) {
        // This method checks if a map is valid based on its size and contained elements.
        if (value == null) {
            return !required;
        }

        Map<?, ?> map = (Map<?, ?>) value;

        // Extract methods for schema and size validation for clarity
        boolean schemaValid = isValidSchema(map);
        if (!schemaValid) {
            return false;
        }

        return isValidSize(map);
    }

    private boolean isValidSchema(Map<?, ?> map) {
        // This method checks if all keys in the map have valid values according to their associated schemas.
        for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema schema = entry.getValue();
            Object valueForSchema = map.get(key);
            if (!schema.isValid(valueForSchema)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidSize(Map<?, ?> map) {
        // This method checks if the map size matches the expected size.
        if (mapSize == 0) {
            return true;
        }
        return map.size() == mapSize;
    }
}
