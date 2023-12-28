package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    private Predicate<Integer> sizeCondition = num -> true;
    private final Map<String, BaseSchema> schemas = new HashMap<>();
    private int requiredSize = 0;

    @Override
    public BaseSchema required() {
        sizeCondition = Objects::nonNull;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (!(value instanceof Map<?, ?> map)) {
            return sizeCondition.test((Integer) value);
        }
        return sizeCondition.test(map.size()) && hasValidSize(map) && areSchemasValid(map);
    }

    private boolean hasValidSize(Map<?, ?> map) {
        return requiredSize == 0 || map.size() == requiredSize;
    }

    private boolean areSchemasValid(Map<?, ?> map) {
        for (var entry : schemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema schema = entry.getValue();
            Object valueToValidate = map.get(key);
            if (!schema.isValid(valueToValidate)) {
                return false;
            }
        }
        return true;
    }

    public void sizeof(int size) {
        this.requiredSize = size;
    }

    public void shape(Map<String, BaseSchema> schemasToValidate) {
        schemas.putAll(schemasToValidate);
    }
}
