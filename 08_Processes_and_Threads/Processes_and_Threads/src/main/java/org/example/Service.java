package org.example;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Service {
    private Integer count = 0;
    private final LinkedList<String> log = new LinkedList<>();

    public synchronized void log() {
        count++;
        String threadName = Thread.currentThread().getName();
        log.addLast(threadName);
    }

    public void debug() {
        System.out.println(count);
        log.forEach(System.out::println);
    }

    public static void callServiceClassMethod() throws Exception {
        var counter = new Service();
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();

            try {
                IntStream.range(0, 10).forEach(i -> counter.log());
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "T1");

        Thread t2 = new Thread(() -> {
            lock.lock();

            try {
                IntStream.range(0, 10).forEach(i -> counter.log());
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "T2");

        Thread t3 = new Thread(() -> {
            lock.lock();

            try {
                IntStream.range(0, 10).forEach(i -> counter.log());
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "T3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        counter.debug();
    }

    public static void main(String[] args) throws Exception {
        callServiceClassMethod();
    }
}