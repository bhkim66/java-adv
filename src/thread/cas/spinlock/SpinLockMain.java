package thread.cas.spinlock;

import static util.MyLogger.log;

public class SpinLockMain {
    public static void main(String[] args) {
//        SpinLockbad spinLock = new SpinLockbad();
        SpinLock spinLock = new SpinLock();

        Runnable task = () -> {
            spinLock.lock();
            try {
                // critical section
                log("비즈니스 로직 실행");
//sleep(1); // 오래 걸리는 로직에서 스핀 락 사용X
            } finally {
                spinLock.unLock();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
