package thread.start;

import util.MyLogger;

import static util.MyLogger.*;

public class HelloThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " : main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + " : start() 호출 전");

        helloThread.start();
        System.out.println(Thread.currentThread().getName() + " : start() 호출 후");

        log("main() end");
    }
}
