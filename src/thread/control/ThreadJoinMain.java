package thread.control;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadJoinMain {
    public static void main(String[] args) {
        log("start");
        Thread thread1 = new Thread(new MyThread(), "thread-1");
        Thread thread2 = new Thread(new MyThread(), "thread-2");

        thread1.start();
        thread2.start();
        log("End");
    }


    static class MyThread implements Runnable {
        @Override
        public void run() {
            log("Thread1 run()");
            sleep(2000);
            log("Thread1 end()");
        }
    }
}
