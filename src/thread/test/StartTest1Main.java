package thread.test;

import static util.MyLogger.log;

public class StartTest1Main {

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }

    static class MyThread extends Thread {
        public void run() {
            log("run() A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
