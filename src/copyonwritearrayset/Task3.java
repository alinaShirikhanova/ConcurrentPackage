package copyonwritearrayset;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Удаление через итератор.
 *  Попытайтесь удалить элемент с помощью Iterator.remove()
 *  при обходе CopyOnWriteArraySet. Какой результат?
 */
public class Task3 {
    public static void main(String[] args) {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        set.add("Hello");
        set.add("World");

        Iterator<String> it = set.iterator();
        it.next(); // получить первый элемент

        try {
            it.remove(); // попытка удалить через итератор
        } catch (UnsupportedOperationException ex) {
            System.out.println("Операция remove() не поддерживается: " + ex);
        }
    }
}

