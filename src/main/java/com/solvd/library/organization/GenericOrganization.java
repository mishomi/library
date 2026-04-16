package com.solvd.library.organization;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenericOrganization<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public boolean contains(T item) {
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
        items.forEach(action);
    }

    public List<T> filterItems(Predicate<T> predicate) {
        return items.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public <R> List<R> mapItems(Function<T, R> mapper) {
        return items.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}