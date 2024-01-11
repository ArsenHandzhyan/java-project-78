package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected Map<String, Predicate<Object>> checks = new LinkedHashMap<>();

    public abstract BaseSchema required();

    protected final void addCheck(String checkName, Predicate<Object> check) {
        checks.put(checkName, check);
    }

    public final boolean isValid(Object value) {
        for (Map.Entry<String, Predicate<Object>> entry : checks.entrySet()) {
            Predicate<Object> check = entry.getValue();
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
