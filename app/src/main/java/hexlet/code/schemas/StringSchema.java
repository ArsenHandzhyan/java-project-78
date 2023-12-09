package hexlet.code.schemas;

import java.util.Optional;

public final class StringSchema extends BaseSchema {

    @Override
    public boolean isValid(Object value) {
        // This method checks if a string is valid based on requiredness, minimum length, and presence of a substring.
        if (value == null) {
            return !required;
        }

        // Use Optional to chain conditions and improve readability
        return Optional.of(value)
                .filter(v -> v instanceof String) // Check if value is a string
                .map(String::valueOf) // Convert to string
                .filter(s -> !required || !s.trim().isEmpty()) // Check requiredness and emptiness
                .filter(s -> s.length() >= minLength) // Check minimum length
                .filter(s -> s.contains(contains)) // Check for substring
                .isPresent(); // Check if any value survived the filters
    }
}
