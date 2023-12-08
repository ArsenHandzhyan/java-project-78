package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {
    private int size = 0;
    private final Map<String, BaseSchema> schemas = new HashMap<>();

    public void sizeof(int sizeMap) {
        this.size = sizeMap;
    }

    public void shape(Map<String, BaseSchema> schemasIn) {
        this.schemas.putAll(schemasIn);
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }
        Map<?, ?> map = (Map<?, ?>) value;
        if (!schemas.isEmpty()) {
            for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema schema = entry.getValue();
                Object valueForSchema = map.get(key);
                if (!schema.isValid(valueForSchema)) {
                    return false;
                }
            }
        }
        if (size == 0) {
            return true;
        }
        return map.size() == size;
    }
}
