package copyonwritearrayset;

import java.util.concurrent.CopyOnWriteArraySet;

public class Task4 {
    public static void main(String[] args) {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        boolean added = set.add(null);  // пытаемся добавить null
        System.out.println("Добавлен null? " + added);
        System.out.println("Содержит null? " + set.contains(null));
        System.out.println("Множество: " + set);
    }
}

