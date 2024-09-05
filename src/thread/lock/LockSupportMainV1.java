package thread.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new ParkTest(), "Thread-1");
        thread1.start();

        sleep(100);
        log("Thread-1 state : " + thread1.getState());

        log("main -> unpark(Thread-1");
//        LockSupport.unpark(thread1); // 대상 스레드를 인자값으로 넘김
        thread1.interrupt(); // 인터럽트를 걸어서 깨울수 있음 WAITING 상태이기 때문에
    }



    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");
            LockSupport.park();
            log("park 종료, state : " + Thread.currentThread().getState());
            log("인터럽트 상태 : " + Thread.currentThread().isInterrupted());
        }
    }

}
