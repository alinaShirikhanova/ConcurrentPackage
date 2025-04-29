package copyonwritearrayset;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Реализуйте простую модель “издатель-подписчик”:
 * создайте интерфейс Listener с методом onEvent().
 * Затем создайте CopyOnWriteArraySet<Listener>
 * и добавьте туда несколько слушателей. В главном
 * потоке вызовите метод onEvent() у всех слушателей,
 * а в другом потоке во время рассылки добавьте нового
 * слушателя. Убедитесь, что рассылка не учитывает
 * «позднего» слушателя (он будет вызван только в
 * последующих рассылках).
 */
interface Listener {
    void onEvent(String message);
}

public class Task5 {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet<Listener> listeners = new CopyOnWriteArraySet<>();

        // Изначальные слушатели
        listeners.add(msg -> System.out.println("Listener1 received: " + msg));
        listeners.add(msg -> System.out.println("Listener2 received: " + msg));

        // Поток, который добавляет слушателя во время рассылки
        new Thread(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            listeners.add(msg -> System.out.println("LateListener received: " + msg));
            System.out.println("LateListener подписался");
        }).start();

        // Рассылка события текущим слушателям
        System.out.println("Отправляем событие...");
        for (Listener listener : listeners) {
            listener.onEvent("Hello Subscribers!");
        }

        // Подождём немного и сделаем ещё одну рассылку
        Thread.sleep(100);
        System.out.println("Отправляем событие повторно...");
        for (Listener listener : listeners) {
            listener.onEvent("Hello again!");
        }
    }
}
