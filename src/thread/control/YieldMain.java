package thread.control;

public class YieldMain {
    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for(int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                for(int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + "-" + j);
                    Thread.yield();
                }
            });
            thread.start();
        }
    }
}
