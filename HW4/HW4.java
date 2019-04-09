//package HW4;
/**
 * Java. Level 3 3Lesson 4
 * Thread wait(),notify()
 *
 * @author DMITRIY OSTROVSKIY
 * @version 0.2 dated APR 09, 2019
 */

public class HW4 {
    private final Object letObj = new Object();
    private volatile char letter = 'A';

    public static void main(String[] args) {
        final HW4 let = new HW4();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                let.print('A','B');
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                let.print('B','C');
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                let.print('C','A');
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public void print(char first, char second) {
        synchronized(letObj) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != first) {
                        letObj.wait(1);
                    }
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(first);
                    letter = second;
                    letObj.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
