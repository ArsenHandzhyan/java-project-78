package hexlet.code;

import hexlet.code.schemas.BaseSchema;

public class NumberSchema extends BaseSchema {
    private boolean required = false;
    private boolean positived = false;
    private int min = 0;
    private int max = 0;

    public void positive() {
        this.positived = true;
    }

    public void range(int minNumber, int maxNumber) {
        this.min = minNumber;
        this.max = maxNumber;
    }

    @Override
    public void required() {
        this.required = true;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }
        if (!(value instanceof Integer)) {
            return false;
        }
        int intValue = (int) value;
        if (positived) {
            return intValue > 0;
        }
        return intValue >= min && intValue <= max;
    }
}
