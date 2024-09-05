package thread.sync;

public interface BackAccount {
    // 은행 거래
    boolean withDraw(int mount);

    // 잔액을 조회
    int getBalance();
}
