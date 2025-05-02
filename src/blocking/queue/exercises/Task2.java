package blocking.queue.exercises;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Очередь с приоритетом: Создайте класс Task
 * с полем priority (целое число) и реализуйте
 * в нём Comparable<Task>, чтобы приоритет влиял
 * на порядок элементов. Затем создайте
 * PriorityBlockingQueue<Task> и добавьте несколько
 * задач с разными приоритетами. Проверьте, что take()
 * всегда возвращает задачу с наименьшим (или наибольшим,
 * в зависимости от реализации) приоритетом в первую очередь.
 * Попробуйте варьировать скорость потребления, чтобы увидеть,
 * что очередь никогда не блокирует вставку (она не имеет
 * ограничения размера).
 */

class Task implements Comparable<Task> {
    private int priority;

    public Task(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(priority, o.priority);
    }

    @Override
    public String toString() {
        return "Task{" +
                "priority=" + priority +
                '}';
    }
}

public class Task2 {
    public static void main(String[] args) {
        BlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        queue.add(new Task( 10));
        queue.add(new Task( 1));
        queue.add(new Task( 5));

        // Потребляем задачи
        while (!queue.isEmpty()) {
            Task t = queue.poll(); // не блокирует
            System.out.println("Took: " + t);
            // можно искусственно замедлить или ускорить
        }
//        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();
//        new Thread(
//                () ->
//                {
//                    for (int i = 100; i > 0; i--) {
//                        System.out.println("Кладу: " + i);
//                        queue.put(new Task(i));
//                    }
//                }
//        ).start();
//
//        new Thread(
//                () ->
//
//                {
//                    for (int i = 0; i < 100; i++) {
//                        try {
//                            System.out.println("Беру..." + queue.take());
//
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//        ).start();
    }
}
