package organization;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class GenericOrganization<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public boolean contains(T item){
        return items.contains(item);
    }

    public T get(int index) {
        return items.get(index);
    }

    public T first() {
        return items.isEmpty() ? null : items.get(0);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    public List<T> getItems() {
        return items;
    }

    public void forEachItem(Consumer<T> action) {
        for (T item : items) {
            action.accept(item);
        }
    }

    public List<T> filterItems(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public <R> List<R> mapItems(Function<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T item : items) {
            result.add(mapper.apply(item));
        }
        return result;
    }
}