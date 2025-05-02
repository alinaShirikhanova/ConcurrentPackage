package blocking.queue;

import java.util.List;
import java.util.concurrent.*;

public class Example {
    public static void main(String[] args) throws InterruptedException {
//        BlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        new Thread(
//                () -> {
//                    try {
//                        Thread.sleep(5000);
//                        queue.take();
//                        System.out.println(queue);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//        ).start();
//        System.out.println("Попытка положить элемент в очередь...");
//        queue.put(100);
//        System.out.println(queue);
//        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
//
//        Runnable producer = () -> {
//            try {
//                for (int i = 0; i < 20; i++) {
//                    queue.put(i); // если очередь полна, поток ждёт, пока не освободится место
//                    System.out.println("Произведено: " + i);
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        };
//
//        Runnable consumer = () -> {
//            try {
//                for (int i = 0; i < 20; i++) {
//                    Integer item = queue.take(); // ждём, пока появится элемент
//                    System.out.println("Потреблено: " + item);
//                    // Здесь могла бы быть обработка item...
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        };
//
//// Запуск потоков
//        new Thread(producer).start();
//        new Thread(consumer).start();


        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(3);
        deque.putFirst("A"); // вставка в начало
        deque.putLast("B");  // вставка в конец
        deque.putFirst("C");
        deque.putFirst("R");
        System.out.println(deque.takeLast());  // извлекает "B" (последний элемент)
    }
}
