package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema {
    boolean isPositive = false;
    public boolean required = false;
    int rangeFrom = 0;
    int rangeUpTo = 0;
    int minLength = 0;
    String contains = "";
    int size = 0;
    final Map<String, BaseSchema> schemas = new HashMap<>();

    public void sizeof(int sizeMap) {
        this.size = sizeMap;
    }

    public void shape(Map<String, BaseSchema> schemasIn) {
        this.schemas.putAll(schemasIn);
    }

    public BaseSchema positive() {
        this.isPositive = true;
        return this; // This change allows method chaining.
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
        return this; // This change allows method chaining.
    }

    public BaseSchema required() {
        this.required = true;
        return this;
    }

    public abstract boolean isValid(Object value);
}
