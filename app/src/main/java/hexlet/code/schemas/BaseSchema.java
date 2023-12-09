package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema {
    boolean isPositive = false;
    public boolean required = false;
    int rangeFrom;
    int rangeUpTo;
    int minLength = 0;
    int mapSize = 0;
    String contains = "";
    final Map<String, BaseSchema> schemas = new HashMap<>();

    public void sizeof(int sizeMap) {
        this.mapSize = sizeMap;
    }

    public void shape(Map<String, BaseSchema> schemasIn) {
        this.schemas.putAll(schemasIn);
    }

    public BaseSchema positive() {
        this.isPositive = true;
        return this;
    }

    public void range(int minIn, int maxIn) {
        this.rangeFrom = minIn;
        this.rangeUpTo = maxIn;
    }

    public void minLength(int minLengthIn) {
        this.minLength = minLengthIn;
    }

    public BaseSchema contains(String containsIn) {
        this.contains = containsIn;
        return this;
    }

    public BaseSchema required() {
        this.required = true;
        return this;
    }

    public abstract boolean isValid(Object value);
}
