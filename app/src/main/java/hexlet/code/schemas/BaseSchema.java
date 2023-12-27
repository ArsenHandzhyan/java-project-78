package hexlet.code.schemas;

public abstract class BaseSchema {
    public abstract BaseSchema required();

    public abstract boolean isValid(Object value);
}
