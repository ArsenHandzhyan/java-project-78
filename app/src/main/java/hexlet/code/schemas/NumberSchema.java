package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    private Predicate<Integer> condition = num -> true;
    private int rangeFrom;
    private int rangeUpTo;


    @Override
    public BaseSchema required() {
        condition = condition.and(Objects::nonNull);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return condition.test(null);
        }
        if (!(value instanceof Integer)) {
            return false;
        }
        if (condition.test(null) && (Integer) value <= 0) {
            return false;
        }
        return rangeFrom == 0 && rangeUpTo == 0 || ((Integer) value >= rangeFrom && (Integer) value <= rangeUpTo);
    }

    public NumberSchema positive() {
        condition = condition.and(num -> num == null || num > 0);
        return this;
    }

    public void range(int minIn, int maxIn) {
        this.rangeFrom = minIn;
        this.rangeUpTo = maxIn;
    }
}
