package thread.start;

public class HelloRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("runnable run()");
    }
}
