package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BackAccountV4 implements BackAccount{
    private int balance;

    private final Lock lock = new ReentrantLock();

    public BackAccountV4(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withDraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());
        // 잔고가 출금액 보다 적으면, 진행 안됨

        if(!lock.tryLock()) {
            log("[진입 실패] : " + getClass().getSimpleName());
            return false;
        }

        try {
            lock.lock(); // ReentrantLock 이용해서 lock 걸기

            // 임계영역, this 인스턴스 참조를 통해 lock을 획득
            log("[검증 시작] : " + amount + ", 잔액 : " + balance);
            if(balance < amount) {
                log("[검증 실패] 출금액 : " + amount + ", 잔액 : " + balance);
                return false;
            }
            log("[검증 완료] 츨금액 : " + amount + ", 잔액 : " + balance);
            sleep(1000);
            balance -= amount;
            log("[출금 완료] 츨금액 : " + amount + ", 잔액 : " + balance);
        } finally {
            lock.unlock(); // unlock이 무조건 실행되어야 하기 때문에 finally 처리
        }

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
