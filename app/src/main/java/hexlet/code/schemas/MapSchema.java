package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    private Predicate<Integer> condition = num -> true;
    private final Map<String, BaseSchema> schemas = new HashMap<>();
    private int mapSize = 0;

    @Override
    public BaseSchema required() {
        condition = condition.and(Objects::nonNull);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return this.condition.test(null);
        }

        Map<?, ?> map = (Map<?, ?>) value;

        boolean schemaValid = isValidSchema(map);
        if (!schemaValid) {
            return false;
        }

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
        if (mapSize == 0) {
            return true;
        }
        return map.size() == mapSize;
    }

    public void sizeof(int sizeMap) {
        this.mapSize = sizeMap;
    }

    public void shape(Map<String, BaseSchema> schemasIn) {
        this.schemas.putAll(schemasIn);
    }
}
