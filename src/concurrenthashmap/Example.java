package concurrenthashmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Example {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
//        map.put("1", 1);
//        map.compute("1", (key, value) -> null);
        map.merge("1", 3, Integer::sum);
        System.out.println(map);
        map.put(null, null);
        map.mappingCount();
        map.remove("1", 3);

    }
}
