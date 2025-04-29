package copyonwritearrayset;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Задача 1. Демонстрация потокобезопасности при чтении и записи.
 * Напишите программу, где один поток добавляет элементы в CopyOnWriteArraySet,
 * а другой одновременно читает множество, печатая его содержимое. Убедитесь,
 * что при этом не возникает исключений и что читающий поток видит корректные
 * состояния (до и после добавления).
 */
public class Task1 {
    public static void main(String[] args) {
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 100; i++) {
                            System.out.printf("Читатель видит: %s%n", set);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).start();

        new Thread(
                () -> {
                    try {
                        for (int i = 100; i < 200; i++) {
                            set.add(i);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).start();
    }
}
