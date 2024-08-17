package thread.test;

import static util.MyLogger.log;

public class StartTest4Main {

    public static void main(String[] args) {
        Thread threadA = new Thread(new newThread("A", 1000), "Thread-A");
        Thread threadB = new Thread(new newThread("B", 500), "Thread-B");

        threadA.start();
        threadB.start();
    }

    static class newThread implements Runnable {
        private String name;
        private int sleepTime;

        public newThread(String name , int sleepTime) {
            this.name = name;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            while (1 > 0) {
                log(name);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
