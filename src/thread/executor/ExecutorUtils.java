package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if(executorService instanceof ThreadPoolExecutor poolExecutor) { // == ThreadPoolExecutor abc = (ThreadPoolExecutor) executorService 와 같은 구문
            int pool = poolExecutor.getCorePoolSize();
            int active = poolExecutor.getActiveCount();
            int queuedTasks = poolExecutor.getQueue().size();
            long completedTask = poolExecutor.getCompletedTaskCount();
            log("[pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTasks + " , completedTask= " + completedTask);
        } else {
            log(executorService);
        }
    }
}
