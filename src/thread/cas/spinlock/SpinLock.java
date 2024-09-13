package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLock {
    private final AtomicBoolean lock = new AtomicBoolean(false);


    public void lock() {
        log("락 획득 시도");
        while (!lock.compareAndSet(false, true)) {
            // 락 획득할 때 까지 스핀 대기(바븐 대기) 한다
            log("획득 실패 - 스핀 대기");
        }
        log("락 획득 완료");
    }

    public void unLock() {
        lock.set(false); // 원자적인 연산 -> 여러 스레드가 함께 실행해도 문제가 발생하지 않는다
        log(" 락 반납 완료");

    }
}
