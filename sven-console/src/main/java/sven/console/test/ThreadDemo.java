package sven.console.test;

import java.util.concurrent.SynchronousQueue;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/18 17:08
 * @description：
 * @version:
 * @see
 */
public class ThreadDemo {
    public void runSync() throws InterruptedException {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>(false);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 start put 1");
                try {
                    queue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 start put 2");
                try {
                    queue.put(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t3 start take");
                try {
                    Integer ta = queue.take();
                    System.out.println("t3 take value is : " + ta);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t4 start take ");
                try {
                    Integer ta = queue.take();
                    System.out.println("t4 take value is : " + ta);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("t1: " + t1.getState().toString());
        System.out.println("t2: " + t2.getState().toString());
        Thread.sleep(1000);
        t3.start();
        Thread.sleep(2000);
        t4.start();
        Thread.sleep(1000);
        System.out.println("t1: " + t1.getState().toString());
        System.out.println("t2: " + t2.getState().toString());
    }
}