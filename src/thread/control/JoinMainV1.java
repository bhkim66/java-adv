package thread.control;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV1 {
    public static void main(String[] args) throws InterruptedException {
        log("Start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "sum1");
        Thread thread2 = new Thread(task2, "sum2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);

        int sumAll = task1.result + task2.result;
        log("SumAll result = " + sumAll);

        log("End");
    }

    static class SumTask implements Runnable {
        int startValue;
        int endValue;
        int result;

        SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업시작");
            sleep(2000);
            int sum = 0;
            for(int i = startValue; i <= endValue; i++) {
                startValue += i;
            }
            result = startValue;
            log("작업 끗 = " + result);

        }
    }
}
