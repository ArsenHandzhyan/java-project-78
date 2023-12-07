package hexlet.code;

public class StringSchema {

    private boolean required = false;
    private int minLengthField = 0;
    private String containsField = "";

    public void required() {
        this.required = true;
    }

    public void minLength(int minLength) {
        this.minLengthField = minLength;
    }

    public StringSchema contains(String contains) {
        this.containsField = contains;
        return this;
    }

    public boolean isValid(Object value) {
        if (!(value instanceof String)) {
            return false;
        }
        return isValid(String.valueOf(value));
    }

    public boolean isValid(String value) {
        if (value == null || value.trim().isEmpty()) {
            return !required;
        }

        if (value.length() < minLengthField) {
            return false;
        }

        return value.contains(containsField);
    }
}
