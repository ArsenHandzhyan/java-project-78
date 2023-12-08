package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private int sizeMap = 0;

    public void sizeof(int size) {
        this.sizeMap = size;
    }


    @Override
    public boolean isValid(Object value) {
        Map<?, ?> map = (Map<?, ?>) value;
        if (value == null) {
            return !required;
        }
        if (sizeMap == 0) {
            return true;
        }
        return map.size() == sizeMap;
    }
}
