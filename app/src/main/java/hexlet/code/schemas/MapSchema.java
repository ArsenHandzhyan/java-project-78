package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }

        Map<?, ?> map = (Map<?, ?>) value;

        // Extract method for schema validation
        boolean schemaValid = isValidSchema(map);
        if (!schemaValid) {
            return false;
        }

        // Extract method for size validation
        return isValidSize(map);
    }

    private boolean isValidSchema(Map<?, ?> map) {
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
        if (size == 0) {
            return true;
        }
        return map.size() == size;
    }
}
