package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV3 {
    public static void main(String[] args) {
        log("Main Thread start");

        //람다 사용
        Thread thread = new Thread(() -> log("run()"));
        thread.start();

        log("Main Thread end");

    }
}
