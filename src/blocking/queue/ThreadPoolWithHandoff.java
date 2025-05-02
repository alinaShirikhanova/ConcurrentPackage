package blocking.queue;

import java.util.concurrent.*;

public class ThreadPoolWithHandoff {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,                // corePoolSize
                5,                // maximumPoolSize
                60, TimeUnit.SECONDS,
                new SynchronousQueue<>(), // без буфера
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        // Отправим 7 задач
        for (int i = 1; i <= 7; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " выполняется в " + Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
