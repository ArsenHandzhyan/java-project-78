package hexlet.code.schemas;


public abstract class BaseSchema {
    public boolean required = false;

    public BaseSchema required() {
        this.required = true;
        return this;
    }

    public abstract boolean isValid(Object value);
}
