package organization;

import java.util.ArrayList;
import java.util.List;

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
}