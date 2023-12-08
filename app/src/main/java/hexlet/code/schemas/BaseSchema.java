package hexlet.code.schemas;


public abstract class BaseSchema {
    public boolean required = false;

    public void required() {
        this.required = true;
    }

    public abstract boolean isValid(Object value);
}
