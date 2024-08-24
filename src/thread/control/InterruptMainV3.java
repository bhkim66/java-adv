package thread.control;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class InterruptMainV3 {
    public static void main(String[] args) {
        MyWork myWork = new MyWork();
        Thread thread = new Thread(myWork, "work");
        thread.start();

        sleep(100);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt();
        log("work 인터럽트 상태1 = " + Thread.currentThread().isInterrupted());

    }
    
    static class MyWork implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {  // 인터럽트 상태 변경 x
                log("작업중");
            }
            log("work 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
            log("작업 종료");
            log("자원 정리");
        }
    }
}
