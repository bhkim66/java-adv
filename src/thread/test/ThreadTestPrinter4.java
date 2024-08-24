package thread.test;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadTestPrinter4 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread thread = new Thread(printer, "myPrinter1");
        thread.start();

        Scanner scanner = new Scanner(System.in);
        log("입력하세요. 종료(q)");
        while (true) {
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("q")) {
                log("종료");
                thread.interrupt();
                break;
            }

            printer.inputPrinter(input);
        }

    }

    static class Printer implements Runnable {
        Queue<String> jobQue = new ConcurrentLinkedDeque<>();

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                if(jobQue.isEmpty()) {
                    Thread.yield(); // 다른 스레드에게 cpu 자원을 양보함
                    continue;
                }
                String job = jobQue.poll();
                log("작업 중인 문서 : " + job + " 남은 문서 : " + jobQue);
                sleep(3000);
                log("작업완료");
            }
        }

        public void inputPrinter(String input) {
            jobQue.add(input);
        }
    }
}
