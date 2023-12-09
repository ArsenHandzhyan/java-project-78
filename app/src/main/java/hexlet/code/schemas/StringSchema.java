package hexlet.code.schemas;

import java.util.Optional;

public class StringSchema extends BaseSchema {

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }
        return Optional.of(value)
                .filter(v -> v instanceof String)
                .map(String::valueOf)
                .filter(s -> !required || !s.trim().isEmpty())
                .filter(s -> s.length() >= minLength)
                .filter(s -> s.contains(contains))
                .isPresent();
    }
}
