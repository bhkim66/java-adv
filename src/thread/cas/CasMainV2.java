package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV2 {
        public static void main(String[] args) {
            AtomicInteger atomicInteger = new AtomicInteger(0);
            System.out.println("start value = " + atomicInteger.get());

            int resultValue1 = incrementAndGet(atomicInteger);
            System.out.println("resultValue1 : "  + resultValue1);

            int resultValue2 = incrementAndGet(atomicInteger);
            System.out.println("resultValue2 : "  + resultValue2);
        }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get(); // thread1 : 0
            log("getValue: " + getValue);
            // thread2 value -> 1, compareAndSet이 값이 다르기 때문에 false를 반환
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result: " + result);
        } while (!result);
        return getValue + 1;
    }
}
