//package HW4;
/**
 * Java. Level 3 Lesson 4
 * Thread
 *
 * @author DMITRIY OSTROVSKIY
 * @version 0.1 dated APR 09, 2019
 */

public class HW4 {
    private final Object letObj = new Object();
    private volatile char letter = 'A';

    public static void main(String[] args) {
        final HW4 let = new HW4();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                let.printA();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                let.printB();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                let.printC();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public void printA() {
        synchronized(letObj) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'A') {
                        letObj.wait();
                    }
                    //Thread.sleep(100);  ? if the same values random bug
                    Thread.sleep(50);
                    System.out.println("A");
                    letter = 'B';
                    letObj.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized(letObj) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'B') {
                        letObj.wait();
                    }
                    //Thread.sleep(100);  ? if the same values random bug
                    Thread.sleep(100);
                    System.out.println("B");
                    letter = 'C';
                    letObj.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized(letObj) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'C') {
                        letObj.wait();
                    }
                    //Thread.sleep(100);  ? if the same values random bug
                    Thread.sleep(50);
                    System.out.println("C");
                    letter = 'A';
                    letObj.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
