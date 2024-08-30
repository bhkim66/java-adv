package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        t.start();

        sleep(1000);

        task.flag = false;
        log("falg = " + task.flag + ", count = " + task.count + " main");
    }

    static class MyTask implements Runnable {
//        boolean runFlag = true;
        long count;
        volatile boolean flag = true;
//        volatile boolean flag = true;
        @Override
        public void run() {
            log("task 시작");
            while (flag) {
                count++;
                if(count % 100_000_000 == 0) {
                    log("falg = " + flag + ", count = " + count + " in while()");
                }
            }
            log("falg = " + flag + ", count = " + count + " 종료");;
        }
    }
}
