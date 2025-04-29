package copyonwritearrayset;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.IntStream;

/**
 * Задача 2. Итерация и модификация.
 * Покажите на примере, что итератор CopyOnWriteArraySet
 * не отражает изменения после его создания. Создайте итератор,
 * потом в другом потоке добавьте элемент в множество, и в основном
 * потоке продолжите итерацию старого итератора.
 */
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class Task2 {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        set.add("X");
        set.add("Y");

        Iterator<String> iterator = set.iterator();

        // Поток добавляет элемент после создания итератора
        new Thread(() -> {
            set.add("Z");
            System.out.println("Добавлен элемент Z");
        }).start();

        // Позволим новому потоку выполниться
        Thread.sleep(100);

        // Продолжаем итерацию старого итератора
        while (iterator.hasNext()) {
            System.out.println("Итератор видит: " + iterator.next());
        }
        // Проверяем содержимое множества после итерации
        System.out.println("Итоговое множество: " + set);
    }
}
