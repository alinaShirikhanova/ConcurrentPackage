package blocking.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Delayed {
    private final long startTime;

    public DelayedTask(long delayMillis) {
        this.startTime = System.currentTimeMillis() + delayMillis;
    }

    public long getDelay(TimeUnit unit) {
        return startTime - System.currentTimeMillis();
    }

    public int compareTo(Delayed o) {
        return Long.compare(this.startTime, ((DelayedTask) o).startTime);
    }
}

class Main {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();
        delayQueue.put(new DelayedTask(5000)); // будет доступен только через ~5 секунд
        System.out.println(delayQueue.take());

    }
}