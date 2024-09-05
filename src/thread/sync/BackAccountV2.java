package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BackAccountV2 implements BackAccount{
    private int balance;

    public BackAccountV2(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withDraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());
        // 잔고가 출금액 보다 적으면, 진행 안됨

        // 임계영역, this 인스턴스 참조를 통해 lock을 획득
        synchronized (this) {
            log("[검증 시작] : " + amount + ", 잔액 : " + balance);
            if(balance < amount) {
                log("[검증 실패] 출금액 : " + amount + ", 잔액 : " + balance);
                return false;
            }
            log("[검증 완료] 츨금액 : " + amount + ", 잔액 : " + balance);
            sleep(1000);
            balance -= amount;
            log("[출금 완료] 츨금액 : " + amount + ", 잔액 : " + balance);

        }
        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return 0;
    }
}
