package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    @Override
    public boolean isValid(Object value) {
        // This method checks if a number is valid based on requiredness, positivity, and range.
        if (value == null) {
            return !required;
        }
        if (!(value instanceof Integer)) {
            return false;
        }
        int intValue = (int) value;
        if (isPositive && intValue <= 0) {
            return false;
        }
        return rangeFrom == 0 && rangeUpTo == 0 || (intValue >= rangeFrom && intValue <= rangeUpTo);
    }
}
