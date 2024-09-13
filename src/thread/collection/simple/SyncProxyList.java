package thread.collection.simple;

import java.util.Arrays;

public class SyncProxyList implements SimpleList{
    private SimpleList target;

    public SyncProxyList(SimpleList list) {
        this.target = list;
    }

    @Override
    public synchronized int size() {
        return target.size();
    }

    @Override
    public synchronized void add(Object o) {
        target.add(o);
    }

    @Override
    public Object get(int index) {
        return target.get(index);
    }

    @Override
    public String toString() {
        return target.toString() + " by " + this.getClass().getSimpleName();
    }
}
