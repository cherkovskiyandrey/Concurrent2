package ru.sbrf;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SyncCollectionsWarning {
    private final List<String> names = Collections.synchronizedList(new LinkedList<>());

    // thread safe
    public void add(String name) {
        names.add(name);
    }

    //not thread save
    public String removeFirst() {
        if (names.size() > 0) {
            return names.remove(0);
        }
        return null;
    }

    @Override
    public String toString() {
        return "SyncCollectionsWarning{" +
                "names=" + Objects.toString(names) +
                '}';
    }
}
