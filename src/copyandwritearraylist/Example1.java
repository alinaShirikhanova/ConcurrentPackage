package copyandwritearraylist;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Example1 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
//        Collections.synchronizedList();
        list.add(12);
        list.add(100);
        list.add(12);
        list.add(12);
        list.add(12);
        list.add(12);
        list.add(12);
        list.add(12);
        list.get(1);

        Iterator<Integer> iterator = list.iterator();
        list.remove(1);
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.remove();
            list.remove(0);
        }
        ConcurrentHashMap.KeySetView<Object, Boolean> objects = ConcurrentHashMap.newKeySet();
    }
}
