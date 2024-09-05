package thread.sync;

public class WithdrawTask implements Runnable {
    private BackAccount account;
    private int amount;

    public WithdrawTask(BackAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        account.withDraw(amount);
    }
}
