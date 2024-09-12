package thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicInteger implements IncrementInteger{
    AtomicInteger myAtomicInteger = new AtomicInteger(0);
    @Override
    public synchronized void increment() {
        myAtomicInteger.incrementAndGet();
    }

    @Override
    public synchronized int get() {
        return myAtomicInteger.get();
    }
}
