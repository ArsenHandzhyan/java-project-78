package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema {
    // Fields
    protected boolean isPositive = false;
    protected boolean required = false;
    protected int rangeFrom;
    protected int rangeUpTo;
    protected int minLength = 0;
    protected int mapSize = 0;
    protected String contains = "";
    protected final Map<String, BaseSchema> schemas = new HashMap<>();

    // Public methods with comments explaining intended usage
    public final void sizeof(int sizeMap) {
        // This method sets the expected size of a map.
        this.mapSize = sizeMap;
    }

    public final void shape(Map<String, BaseSchema> schemasIn) {
        // This method defines a set of expected keys and their corresponding validation schemas for a map.
        this.schemas.putAll(schemasIn);
    }

    public final BaseSchema positive() {
        // This method specifies that a number must be positive.
        this.isPositive = true;
        return this;
    }

    public final void range(int minIn, int maxIn) {
        // This method sets the expected range for a number.
        this.rangeFrom = minIn;
        this.rangeUpTo = maxIn;
    }

    public final void minLength(int minLengthIn) {
        // This method sets the minimum length for a string.
        this.minLength = minLengthIn;
    }

    public final BaseSchema contains(String containsIn) {
        // This method specifies a substring that a string must contain.
        this.contains = containsIn;
        return this;
    }

    public final BaseSchema required() {
        // This method specifies that a field is required.
        this.required = true;
        return this;
    }

    public abstract boolean isValid(Object value);
}
