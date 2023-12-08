package hexlet.code;

import hexlet.code.schemas.BaseSchema;

import java.util.Optional;

public class StringSchema extends BaseSchema {
    private int minLengthField = 0;
    private String containsField = "";

    public void minLength(int minLength) {
        this.minLengthField = minLength;
    }

    public StringSchema contains(String contains) {
        this.containsField = contains;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }
        return Optional.of(value)
                .filter(v -> v instanceof String) // Check for null first
                .map(String::valueOf)
                .filter(s -> !required || !s.trim().isEmpty())
                .filter(s -> s.length() >= minLengthField)
                .filter(s -> s.contains(containsField))
                .isPresent();
    }
}
