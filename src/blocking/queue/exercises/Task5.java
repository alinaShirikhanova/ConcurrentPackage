package blocking.queue.exercises;

import java.util.concurrent.ArrayBlockingQueue;

public class Task5 {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        new Thread(
                () ->
                {
                    for (int i = 0; i < 1000; i++) {
                        try {
                            System.out.println("Кладу: " + i);
                            queue.put(i);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();

        new Thread(
                () ->
                {
                    for (int i = 0; i < 1000; i++) {
                        try {
                            System.out.println("Беру: " + queue.take());
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();

    }
}
