package hexlet.code;

public class NumberSchema {
    private boolean required = false;
    private boolean positived = false;
    private int min = 0;
    private int max = 0;

    public void required() {
        this.required = true;
    }

    public void positive() {
        this.positived = true;
    }

    public void range(int minNumber, int maxNumber) {
        this.min = minNumber;
        this.max = maxNumber;
    }

    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }
        if (!(value instanceof Integer)) {
            return false;
        }
        return isValid(Integer.parseInt(value.toString()));
    }

    public boolean isValid(int value) {
        if (positived) {
            return value > 0;
        }
        return value >= min && value <= max;
    }
}
