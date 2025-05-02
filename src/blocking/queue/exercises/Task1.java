package blocking.queue.exercises;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Простая очередь производитель–потребитель:
 * Напишите программу, где один поток последовательно
 * добавляет числа в ArrayBlockingQueue ёмкости 10,
 * а другой поток забирает их и выводит на экран.
 * Проверьте, что при переполненной очереди метод put()
 * блокируется до освобождения места (например, замедлите
 * потребителя или уберите его на время, и убедитесь, что
 * производитель «ждёт»).
 */
public class Task1 {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        new Thread(
                () ->
                {
                    for (int i = 0; i < 100; i++) {
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
                ()->
                {
                    for (int i = 0; i < 100; i++) {
                        try {
                            System.out.println("Беру...");
                            System.out.println(queue.take());
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();
    }
}
