//package HW3;
/**
 * Java. Leve3 3Lesson 3
 * Class IO
 *
 * @author DMITRIY OSTROVSKIY
 * @version 0.1 dated APR 07, 2019
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.lang.Math.*;
import java.util.Scanner;

public class HW3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        byte[] arr = new byte[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (byte) (255 * Math.random());
        }
        //----------HW3.1-----------
        getByteArr(arr);
        System.out.println("END HW3.1");
        //----------HW3.2-----------
        getStitchSequentially ();
        System.out.println("END HW3.2");
        //----------HW3.3-----------
        getRW(new File("123/demo.txt"));
        System.out.println("END HW3.3");
    }
    //--------------HW3.1-----------------
    public static void getByteArr (byte [] arr) {
        ByteArrayInputStream in = new ByteArrayInputStream(arr);
        int x;
        while ((x = in.read())!=-1) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
    //--------------HW3.2-----------------
    public static void getStitchSequentially () {
        ArrayList<InputStream> arrIS = new ArrayList<>();
        try {
            arrIS.add(new FileInputStream("123/1.txt"));
            arrIS.add(new FileInputStream("123/2.txt"));
            arrIS.add(new FileInputStream("123/3.txt"));
            arrIS.add(new FileInputStream("123/4.txt"));
            arrIS.add(new FileInputStream("123/5.txt"));

            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(arrIS));
            int x;
            while ((x = in.read()) != -1) {
                System.out.print((char) x);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //--------------HW3.3-----------------
    public static void getRW(File file){
        final int SIZE = 1800;
        byte arr [] = new byte[1800];
        Scanner scanner = new Scanner(System.in);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            int i = 1;
            while(file.length()< (1024*1024*10)) {
                for (int j = 0; j < 1799; j++) {
                    writer.write("" + 1);
                }
                writer.write("#");
                i++;
            }
            System.out.println("страниц = " + i);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Введите номер страницы для вывода (для выхода 0) от 1 до " + (file.length()/1800 + 1));
        int page = 0;
        do {
            System.out.print(">");
            page = scanner.nextInt();
            if (page > 0) {
                try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                    raf.seek((page-1)*SIZE);
                    raf.read(arr);
                    System.out.print(new String(arr));
                    System.out.println();
                    System.out.println("arr.length = " + arr.length);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (page != 0);
    }
}