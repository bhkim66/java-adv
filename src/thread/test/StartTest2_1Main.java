package thread.test;

import static util.MyLogger.log;

public class StartTest2_1Main {

    public static void main(String[] args) {
        CounterThread counterThread = new CounterThread();
        Thread thread = new Thread(counterThread, "counter");
        thread.start();
    }

    static class CounterThread implements Runnable {
        @Override
        public void run() {
            for(int i = 1; i <= 10; i++) {
                log("value : " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



}