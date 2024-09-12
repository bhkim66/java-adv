package thread.cas.increment;

public class IncrementPerformance {
    public static final long COUNT = 100_000_000;
    public static void main(String[] args) {
        test(new BasicInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) {
        long startMs = System.currentTimeMillis();
        for (long i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }
        long endMs = System.currentTimeMillis();
        System.out.println(incrementInteger.getClass().getSimpleName() + ": ms=" + (endMs - startMs));
    }
}
