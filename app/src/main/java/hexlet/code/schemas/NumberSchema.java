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
        if (isPositive) {
            return intValue > 0;
        }
        return intValue >= rangeFrom && intValue <= rangeUpTo;
    }
}
