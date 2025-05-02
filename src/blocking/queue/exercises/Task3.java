package blocking.queue.exercises;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Задержка выполнения: Используйте DelayQueue
 * для планирования действий. Реализуйте класс
 * DelayedTask, который выводит сообщение после
 * задержки (реализуйте методы getDelay() и
 * compareTo()). Положите в очередь несколько
 * задач с разными задержками (например, 2 сек, 5 сек, 1 сек)
 * и извлекайте их вызовом take(). Убедитесь, что задачи
 * становятся доступными строго по истечении их задержки
 * (самая короткая задержка – первая доступная).
 */
class DelayedTask implements Delayed {
    private long startTime;
    private long delay;

    public DelayedTask(long delay) {
        this.startTime = System.currentTimeMillis() + delay;
        this.delay = delay;
    }


    @Override
    public long getDelay(TimeUnit unit) {
        return startTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(startTime, ((DelayedTask)o).startTime);
    }

    @Override
    public String toString() {
        return "DelayedTask{" +
                "startTime=" + startTime +
                ", delay=" + delay +
                '}';
    }
}
public class Task3 {
    public static void main(String[] args) {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        queue.put(new DelayedTask(10000));
        queue.put(new DelayedTask(2000));
        queue.put(new DelayedTask(3000));

        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
