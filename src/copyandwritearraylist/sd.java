package copyandwritearraylist;

import java.util.concurrent.CopyOnWriteArrayList;

public class sd {
    public static void main(String[] args) throws InterruptedException {
        testConcurrentReadWrite();
    }

    public static void testConcurrentReadWrite() throws InterruptedException {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);

        Thread writer = new Thread(() -> {
            for (int i = 2; i <= 1000; i++) list.add(i);
        });
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.forEach(x -> {}); // просто читаем
            }
        });
        writer.start(); reader.start();
        writer.join(); reader.join();
        System.out.println(list);

    }
}
