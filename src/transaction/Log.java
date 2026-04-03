package transaction;

import java.util.ArrayList;
import java.util.List;

public class Log<T> {
    private final List<T> actions = new ArrayList<>();

    public void add(T action) {
        actions.add(action);
    }

    public T first() {
        return actions.isEmpty() ? null : actions.get(0);
    }

    public int size() {
        return actions.size();
    }

    public boolean isEmpty() {
        return actions.isEmpty();
    }

    public List<T> getActions() {
        return actions;
    }

    public void printAll() {
        actions.stream().forEach(System.out::println);
    }
}