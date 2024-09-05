package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BackAccountV1 implements BackAccount{
    private int balance;

    public BackAccountV1(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withDraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());
        // 잔고가 출금액 보다 적으면, 진행 안됨

        log("[검증 시작] : " + amount + ", 잔액 : " + balance);
        if(balance < amount) {
            log("[검증 실패] 출금액 : " + amount + ", 잔액 : " + balance);
            return false;
        }
        log("[검증 완료] 츨금액 : " + amount + ", 잔액 : " + balance);
        sleep(1000);
        balance -= amount;
        log("[출금 완료] 츨금액 : " + amount + ", 잔액 : " + balance);

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        return 0;
    }
}
