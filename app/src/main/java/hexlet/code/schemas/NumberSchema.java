package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private boolean isPositive = false;
    private int min = 0;
    private int max = 0;

    public void positive() {
        this.isPositive = true;
    }

    public void range(int minNumber, int maxNumber) {
        this.min = minNumber;
        this.max = maxNumber;
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
        if (isPositive) {
            return intValue > 0;
        }
        return intValue >= min && intValue <= max;
    }
}
