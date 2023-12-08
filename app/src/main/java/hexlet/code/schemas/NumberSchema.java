package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private boolean isPositive = false;
    private int min = 0;
    private int max = 0;

    public BaseSchema positive() {
        this.isPositive = true;
        return this; // This change allows method chaining.
    }

    public void range(int minIn, int maxIn) {
        this.min = minIn;
        this.max = maxIn;
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
