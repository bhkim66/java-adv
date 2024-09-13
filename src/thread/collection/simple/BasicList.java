package thread.collection.simple;

import java.util.Arrays;

public class BasicList implements SimpleList{
    private final int DEFAULT_CAPACITY = 5;
    private Object[] e;
    private int size = 0;

    public BasicList() {
        e = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Object o) {
        e[size] = o;
        size++;
    }

    @Override
    public Object get(int index) {
        return e[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(e, size)) + " size = " + size + " capacity = " + e.length;
    }
}
