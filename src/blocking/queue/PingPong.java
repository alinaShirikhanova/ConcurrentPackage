package blocking.queue;

import java.util.concurrent.SynchronousQueue;

public class PingPong {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        Thread ping = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    queue.put("ping");                     // отправляем ping
                    System.out.println("ping отправлен");

                    String reply = queue.take();           // ждём pong
                    System.out.println("pong получен");

                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread pong = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    String msg = queue.take();             // ждём ping
                    System.out.println(msg + " принят");

                    queue.put("pong");                     // отвечаем pong
                    System.out.println("pong отправлен");

                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        ping.start();
        pong.start();
    }
}
