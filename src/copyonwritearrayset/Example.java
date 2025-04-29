package copyonwritearrayset;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
//        set.add(1);
//        set.add(2);
//        set.add(3);
//
//        CopyOnWriteArraySet<Integer> set1 = new CopyOnWriteArraySet<>();
//        set1.add(3);
//        set1.add(2);
//        set1.add(1);
//        System.out.println(set.equals(set1));
////        IntStream.range(1, 1000).forEach(set::add);
////        ExecutorService service = Executors.newFixedThreadPool(5);
////        for (int i = 0; i < 5; i++) {
////            int finalI = i;
////            service.submit(
////                    () -> set.remove(finalI)
////            );
////        }
////
////        service.shutdown();
////        service.awaitTermination(5000, TimeUnit.MILLISECONDS);
////        System.out.println(set);
//        for (int i = 1; i <= 5; i++) {
//            set.add(i);
//        }
////        for (int value : set) {
////            System.out.println(value);
////        }
//        Iterator<Integer> iterator = set.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//            iterator.remove();
//        }
//        for (int value : set) {
//            set.add(value + 100);
//
//            System.out.println(value);
//        }
//        for (Integer i : set) {
//            System.out.println(i);
//        }


        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                set.add(i);
            }
        };
        Runnable runnable1 = () -> {
            for (int i = 1000; i < 1100; i++) {
                set.add(i);
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable1);
        thread1.start();

        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(set.size());
    }
}
