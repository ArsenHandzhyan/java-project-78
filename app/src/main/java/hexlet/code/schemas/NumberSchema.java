package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    @Override
    public NumberSchema required() {
        addCheck("required", object -> object instanceof Integer);
        addCheck("nonNull", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> {
            if (value == null) {
                return true;
            }
            if (value instanceof Integer) {
                int intValue = (int) value;
                return intValue > 0;
            }
            return false;
        });
        return this;
    }

    public void range(int minIn, int maxIn) {
        addCheck("range", value -> {
            if (value instanceof Integer) {
                int intValue = (int) value;
                return intValue >= minIn && intValue <= maxIn;
            }
            return false;
        });
    }
}
