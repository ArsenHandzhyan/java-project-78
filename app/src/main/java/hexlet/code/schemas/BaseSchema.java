package hexlet.code.schemas;


public abstract class BaseSchema {
    public boolean required = false;

    public BaseSchema required() {
        this.required = true;
        return this; // This change allows method chaining.
    }

    public abstract boolean isValid(Object value);

    public boolean contains(String value, String substring) {
        return value != null && value.contains(substring);
    }
}
