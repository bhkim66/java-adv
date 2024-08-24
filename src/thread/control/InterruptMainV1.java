package thread.control;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class InterruptMainV1 {
    public static void main(String[] args) {
        MyTask1 myTask1 = new MyTask1();
        Thread thread = new Thread(myTask1, "work");
        thread.start();

        sleep(4000);
        log("run flag = false");
        myTask1.runFlag = false;
    }
    
    static class MyTask1 implements Runnable {
        volatile boolean runFlag = true;
        @Override
        public void run() {
            while (runFlag) {
                log("작업중");
                sleep(3000);
            }
            log("작업 끝");
            log("자원 반납");
        }
    }
}
