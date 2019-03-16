/**
 * Java2. Core. HW5
 * Многопоточность. Тесты
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 16, 2019
 */

import java.util.*;

public class HW5 {
    
    public static void main(String[] args) {
        Test test = new Test();
        test.Array(); 
        test.test1Thread(); 
        test.test2Thread();
        test.testArray();
        }
}  
 
class Test {  
    
    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size]; 
    
    public void Array() { 
        System.out.print("Create Array... ");
        long arr1 = System.currentTimeMillis();
        for (int i = 0; i < size; i++)
            arr[i]= 1.0f;
        long arr2 = System.currentTimeMillis();
        System.out.println("Done " + (arr2 - arr1) + " mc");
    }
    // Testing class 1 Thread
    
    public void test1Thread() { 
        System.out.print("Testing 1 Thread... ");
        long t1 = System.currentTimeMillis();        
        for (int i = 0; i < size; i++)
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        long t2 = System.currentTimeMillis();
        System.out.println("Done " + (t2 - t1) + " mc");
    }
    // Testing class class 2 Thread
    
    public void test2Thread() { 
        System.out.print("Testing 2 Thread... ");
        float a1[] = new float[h];
        float a2[] = new float[h];
        
        long t1 = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                 for (int i = 0; i < a1.length; i++)
                    a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a2.length; i++)
                    a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });    
        
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
  
        long t2 = System.currentTimeMillis();
        System.out.println("Done " + (t2 - t1) + " mc");        
    }
    
    public void testArray() {
        System.out.print("Testing Array... ");
        int w = 0;
        long t1 = System.currentTimeMillis();        
        for (int i = 0; i < size; i++) {
            if (arr[i] == 1 || arr[i] == 0 || arr == null) {
                w = 0;
                break;
            }
            else w = 1;
        }
        long t2 = System.currentTimeMillis();
        if (w == 0) System.out.println("Done NOK " + (t2 - t1) + " mc");
        else System.out.println("Done OK " + (t2 - t1) + " mc");
    }
}