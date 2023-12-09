package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    @Override
    public boolean isValid(Object value) {
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
        // Check range only if it was explicitly set
        return rangeFrom == 0 && rangeUpTo == 0 || (intValue >= rangeFrom && intValue <= rangeUpTo);
    }
}
