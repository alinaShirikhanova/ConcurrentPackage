package concurrenthashmap;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class MapPerformanceTest {

    // Функция для измерения времени выполнения операций put и get с использованием нескольких потоков
    private long timeElapseForGetPut(Map<String, Object> map) throws InterruptedException {
        final int iterations = 500_000;
        final int threadCount = 10; // Количество потоков

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // Заполнение карты с использованием нескольких потоков
        long startTime = System.nanoTime();
        for (int i = 0; i < threadCount; i++) {
            final int threadId = i;
            CountDownLatch finalLatch = latch;
            executor.submit(() -> {
                for (int j = threadId * (iterations / threadCount); j < (threadId + 1) * (iterations / threadCount); j++) {
                    map.put("key" + j, new Object());
                }
                finalLatch.countDown();
            });
        }

        latch.await(); // Дождаться завершения всех операций
        long endTime = System.nanoTime();
        long putTime = endTime - startTime;

        // Получение значений из карты с использованием нескольких потоков
        startTime = System.nanoTime();
        latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadId = i;
            CountDownLatch finalLatch1 = latch;
            executor.submit(() -> {
                for (int j = threadId * (iterations / threadCount); j < (threadId + 1) * (iterations / threadCount); j++) {
                    map.get("key" + j);
                }
                finalLatch1.countDown();
            });
        }

        latch.await(); // Дождаться завершения всех операций
        endTime = System.nanoTime();
        long getTime = endTime - startTime;

        executor.shutdown();

        // Возвращаем среднее время
        return (putTime + getTime) / (2 * iterations);
    }

    // Тест, который сравнивает производительность разных карт
    public void givenMaps_whenGetPut500KTimes_thenConcurrentMapFaster() throws Exception {
        Map<String, Object> hashtable = new Hashtable<>();
        Map<String, Object> synchronizedHashMap =
                Collections.synchronizedMap(new HashMap<>());
        Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

        long hashtableAvgRuntime = timeElapseForGetPut(hashtable);
        long syncHashMapAvgRuntime = timeElapseForGetPut(synchronizedHashMap);
        long concurrentHashMapAvgRuntime = timeElapseForGetPut(concurrentHashMap);

        System.out.println("Hashtable avg time: " + hashtableAvgRuntime);
        System.out.println("SynchronizedHashMap avg time: " + syncHashMapAvgRuntime);
        System.out.println("ConcurrentHashMap avg time: " + concurrentHashMapAvgRuntime);

        // Переопределим проверку на основе фактических данных:
        assertTrue(concurrentHashMapAvgRuntime <= syncHashMapAvgRuntime);
        assertTrue(concurrentHashMapAvgRuntime <= hashtableAvgRuntime);
    }

    // Вспомогательная функция для проверки
    private void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Test failed");
        }
    }

    public static void main(String[] args) throws Exception {
        new MapPerformanceTest().givenMaps_whenGetPut500KTimes_thenConcurrentMapFaster();
    }
}
