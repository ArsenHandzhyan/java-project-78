package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    private Predicate<Integer> condition = num -> true;
    private int rangeFrom = 0;
    private int rangeUpTo = 0;

    @Override
    public BaseSchema required() {
        condition = condition.and(Objects::nonNull);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (!(value instanceof Integer intValue)) {
            return condition.test(null) && value == null;
        }
        return condition.test(intValue) && isInsideRange(intValue);
    }

    private boolean isInsideRange(Integer intValue) {
        if (rangeFrom == 0 && rangeUpTo == 0) {
            return true;
        }
        return intValue >= rangeFrom && intValue <= rangeUpTo;
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
