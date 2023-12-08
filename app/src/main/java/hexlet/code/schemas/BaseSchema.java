package hexlet.code.schemas;


public abstract class BaseSchema {
    public abstract void required();

    public abstract boolean isValid(Object value);
}
