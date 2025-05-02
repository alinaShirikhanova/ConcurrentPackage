package blocking.queue.exercises;

import java.util.concurrent.SynchronousQueue;

/**
 * Пары потоков и SynchronousQueue: Создайте два потока
 * и SynchronousQueue<String>. Первый поток раз в
 * несколько секунд генерирует строку с текущим временем
 * и вызывает put(). Второй поток вызывает take() и сразу
 * выводит полученную строку. Объясните, как SynchronousQueue
 * обеспечивает передачу «из рук в руки» – без буферизации:
 * попытка put() будет ждать, пока другой поток не вызовет
 * take(), и наоборот.
 */
public class Task4 {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(
                () ->
                {
                    try {
                        for (int i = 0; i < 5; i++) {
                            System.out.println("Кладу: " + i);
                            queue.put("element " + i);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
        ).start();

        new Thread(
                () ->
                {
                    try {
                        for (int i = 0; i < 5; i++) {

                            System.out.println("Беру: " + queue.take());
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).start();
    }
}
