package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema {
    protected boolean isPositive = false;
    protected boolean required = false;
    protected int rangeFrom;
    protected int rangeUpTo;
    protected int minLength = 0;
    protected int mapSize = 0;
    protected String contains = "";
    protected final Map<String, BaseSchema> schemas = new HashMap<>();

    public final void sizeof(int sizeMap) {
        this.mapSize = sizeMap;
    }

    public final void shape(Map<String, BaseSchema> schemasIn) {
        this.schemas.putAll(schemasIn);
    }

    public final BaseSchema positive() {
        this.isPositive = true;
        return this;
    }

    public final void range(int minIn, int maxIn) {
        this.rangeFrom = minIn;
        this.rangeUpTo = maxIn;
    }

    public final void minLength(int minLengthIn) {
        this.minLength = minLengthIn;
    }

    public final BaseSchema contains(String containsIn) {
        this.contains = containsIn;
        return this;
    }

    public final BaseSchema required() {
        this.required = true;
        return this;
    }

    public abstract boolean isValid(Object value);
}
