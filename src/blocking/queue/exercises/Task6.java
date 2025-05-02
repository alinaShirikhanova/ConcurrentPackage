package blocking.queue.exercises;

import java.util.ArrayList;
import java.util.List;

public class Task6 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        new Thread(
                () ->
                {

                    try {
                        int counter = 0;
                        while (true) {
                            synchronized (list) {
                                while (list.size() >= 10) {
                                    list.wait();
                                }
                                System.out.println("Кладу: " + counter);
                                list.add(counter++);
                                list.notify();
                            }
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
        ).start();

        new Thread(
                () ->
                {

                    try {
                        while (true) {
                            synchronized (list) {
                                while (list.isEmpty()) {
                                    list.wait();
                                }
                                System.out.println("Беру: " + list.removeFirst());
                                list.notify();
                                Thread.sleep(100);
                            }
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
    }
}

