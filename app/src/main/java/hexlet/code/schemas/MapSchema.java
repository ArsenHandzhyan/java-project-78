package hexlet.code.schemas;

import java.util.*;

public final class MapSchema extends BaseSchema {
    private int requiredSize = 0;

    @Override
    public BaseSchema required() {
        addCheck("required", value -> {
            if (value == null) {
                return false;
            }

            return value instanceof Map<?, ?>;
        });

        return this;
    }

    public void sizeof(int size) {
        this.requiredSize = size;
        addCheck("sizeof", value -> {
            if (!(value instanceof Map<?, ?> map)) {
                return false;
            }
            return hasValidSize(map);
        });
    }

    private boolean hasValidSize(Map<?, ?> map) {
        return requiredSize == 0 || map.size() == requiredSize;
    }

    public void shape(Map<String, BaseSchema> schemasToValidate) {
        addCheck("shape", value -> {
            if (!(value instanceof Map<?, ?> map)) {
                return false;
            }

            for (var entry : schemasToValidate.entrySet()) {
                String key = entry.getKey();
                BaseSchema schema = entry.getValue();
                Object valueToValidate = map.get(key);
                if (!schema.isValid(valueToValidate)) {
                    return false;
                }
            }
            return true;
        });
    }
}
