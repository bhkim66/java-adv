package thread.control;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class InterruptMainV2 {
    public static void main(String[] args) {
        MyWork myWork = new MyWork();
        Thread thread = new Thread(myWork, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt();
        log("work 인터럽트 상태1 = " + Thread.currentThread().isInterrupted());

    }
    
    static class MyWork implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    log("작업중");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                log("work 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                log("인터럽트 메세지 = " + e.getMessage());
                log("state = " + Thread.currentThread().getState());
            }
            log("작업 종료");
            log("자원 정리");
        }
    }
}
