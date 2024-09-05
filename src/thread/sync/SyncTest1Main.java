package thread.sync;

public class SyncTest1Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("결과: " + counter.getCount());
    }

    static class Counter {
        private int count = 0;
        public synchronized void increment() {
            count = count + 1; // 코드는 한줄이지만 3가지로 나뉜다. 1.count 값을 읽는다 2. 읽는 count에 +1를 더한다 3.더한 값을 다시 count에 넣는다 -> 임계영역의 부부니다
        }
        public synchronized int getCount() {
            return count;
        }
    }
}
