package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockbad {
    private volatile boolean lock = false;

    public void lock() {
        log("락 획득 시도");

        while (true) {
            if (!lock) {
                sleep(100);
                lock = true;
                break;
            } else {
                // 락 획득할 때 까지 스핀 대기(바븐 대기) 한다
                log("락 획득 실패 - 스핀 대기");
            }
        }
        log("락 획득 완료");
    }

    public void unLock() {
        lock = false; // 원자적인 연산 -> 여러 스레드가 함께 실행해도 문제가 발생하지 않는다
        log(" 락 반납 완료");

    }
}
