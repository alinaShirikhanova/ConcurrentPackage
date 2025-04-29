package blocking.queue.synchronous.queue;

import java.util.concurrent.SynchronousQueue;

public class DirectHandoffExample {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        // Продьюсер
        Thread producer = new Thread(() -> {
            String[] items = { "A", "B", "C" };
            try {
                for (String item : items) {
                    System.out.println("Producer: готовлю " + item);
                    queue.put(item);  // блокируется, пока consumer не возьмёт
                    System.out.println("Producer: передал " + item);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Консьюмер
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Получаю...");
                    String item = queue.take();  // блокируется, пока producer не положит
                    System.out.println("Consumer: получил " + item);
                    Thread.sleep(500); // эмуляция обработки
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}