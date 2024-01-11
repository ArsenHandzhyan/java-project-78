package hexlet.code.schemas;

import java.util.Objects;

public final class StringSchema extends BaseSchema {

    @Override
    public BaseSchema required() {
        addCheck("nonNull", Objects::nonNull);
        addCheck("nonEmptyString", value -> {
            if (value instanceof String str) {
                return !str.isEmpty();
            }
            return false;
        });
        return this;
    }

    public void minLength(int minLength) {
        addCheck("minLength", value -> {
            if (value instanceof String str) {
                return str.length() >= minLength;
            }
            return false;
        });
    }

    public BaseSchema contains(String substring) {
        addCheck("contains", value -> {
            if (value instanceof String str) {
                return str.contains(substring);
            }
            return false;
        });
        return this;
    }
}
