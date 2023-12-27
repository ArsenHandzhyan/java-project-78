package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    private Predicate<String> condition = str -> true;

    @Override
    public StringSchema required() {
        condition = condition.and(str -> str != null && !str.isEmpty());
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return this.condition.test(null);
        }
        if (!(value instanceof String)) {
            return false;
        }
        return this.condition.test((String) value);
    }

    public void minLength(int minLength) {
        condition = condition.and(str -> str.length() >= minLength);
    }

    public StringSchema contains(String substring) {
        condition = condition.and(str -> str.contains(substring));
        return this;
    }
}
