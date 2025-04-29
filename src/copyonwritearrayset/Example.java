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
//        IntStream.range(1, 1000).forEach(set::add);
//        ExecutorService service = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 5; i++) {
//            int finalI = i;
//            service.submit(
//                    () -> set.remove(finalI)
//            );
//        }
//
//        service.shutdown();
//        service.awaitTermination(5000, TimeUnit.MILLISECONDS);
//        System.out.println(set);
        for (int i = 1; i <= 5; i++) {
            set.add(i);
        }
//        for (int value : set) {
//            System.out.println(value);
//        }
        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.remove();
        }
//        for (int value : set) {
//            set.add(value + 100);
//
//            System.out.println(value);
//        }
//        for (Integer i : set) {
//            System.out.println(i);
//        }
    }
}
