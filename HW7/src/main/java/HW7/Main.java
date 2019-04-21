package HW7;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.io.*;
import java.util.*;
import java.util.Map;
import java.util.HashMap;

import static java.lang.String.valueOf;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException,
            MalformedURLException, ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException {

        String list[] = {"Ivanov", "Petrov", "Sidorov"};

        Map<Integer, Integer> calcut = new HashMap<>();
        calcut.put(2, 1);
        calcut.put(20, 4);
        calcut.put(42, 6);

        Map<Integer, Boolean> checkTwo = new HashMap<>();
        checkTwo.put(4, false);
        checkTwo.put(7, true);
        checkTwo.put(15, false);

        Map<Integer, String> printIs = new HashMap<>();
        printIs.put(-3, "Your number is negative!");
        printIs.put(0, "Your number is positive!");
        printIs.put(7, "Your number is positive!");

        Map<Integer, Boolean> isNeg = new HashMap<>();
        isNeg.put(-3, true);
        isNeg.put(0, false);
        isNeg.put(7, false);

        Map<Integer, Boolean> isLeap = new HashMap<>();
        isLeap.put(2000, true);
        isLeap.put(1900, false);
        isLeap.put(2012, true);
        isLeap.put(1961, false);

        File file = new File("C:/Java1/lesson1");

        String[] str = file.list();

        for (String o : str) {
            String[] mass = o.split("\\.");
            if(!mass[1].equalsIgnoreCase("class")) {
                throw new RuntimeException(o, new Exception());
            }
        }

        for (String nameClass : list) {
            System.out.println(nameClass + " : ");

        Class ch = URLClassLoader.newInstance(new URL[]{new File("/lesson1") //"C:/java1/lesson1"
                .toURL()}).loadClass(nameClass);
        // get Class
        Class strClass = ch.getClass();
        System.out.println("get Class= " + strClass);
        // get Class Name
        String className = ch.getName();
        System.out.println("get ClassName= " + className);
        // get class Obj
        Class classObj = Class.forName(className);
        System.out.println("get class Obj= " + classObj);
      //  System.out.println();
        // get Fields
        Field[] fields = classObj.getDeclaredFields();
        for (Field o : fields) {
            System.out.println("get Field= " + o.getType() + "\nget FieldName= " + o.getName());
        }
        System.out.println();
        String name = new String();
        name = "" + className;
            Map<String, String> printWel = new HashMap<>();
            printWel.put(name, "Привет, " + name);

        // get Methods
        Method[] methods = classObj.getDeclaredMethods();
            int point = 0;
            System.out.println("--------Test_Method calculate--------");
            try {
                Method calc =  classObj.getDeclaredMethod("calculate", int.class, int.class, int.class, int.class);
                calc.setAccessible(true);
                int i = 0;

                for(Map.Entry<Integer, Integer> item : calcut.entrySet()){
                    i++;
                    if ((calc.invoke(classObj, item.getValue(), item.getValue(), item.getValue(), item.getValue())).equals(item.getKey())) {
                        System.out.println("Test 1." + i + " OK" + "(" + item.getValue() + "= " +  item.getKey() + ")");
                        point++;
                    } else System.out.println("Test 1." + i + " BAD!");
                }
                System.out.println("point = " + point);
            } catch (NoSuchMethodException | IllegalAccessException |
                    InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println("--------Test_Method checkTwoNumbers--------");
            try {
                Method check =  classObj.getDeclaredMethod("checkTwoNumbers", int.class, int.class);
                check.setAccessible(true);
                int i = 0;
                for(Map.Entry<Integer, Boolean> item : checkTwo.entrySet()){
                    i++;
                    if ((check.invoke(classObj, item.getKey(), item.getKey())).equals(item.getValue())) {
                        System.out.println("Test 2." + i + " OK" + "(" + item.getKey() + "," + item.getKey() + "= " +  item.getValue() + ")");
                        point++;
                    } else System.out.println("Test 2." + i + " BAD!");
                }
                System.out.println("point = " + point);
            } catch (NoSuchMethodException | IllegalAccessException |
                    InvocationTargetException e) {
                e.printStackTrace();
            }

            System.out.println("--------Test_Method printIsPositive--------");
            try {
                Method isPositive =  classObj.getDeclaredMethod("printIsPositive", int.class);
                isPositive.setAccessible(true);
                int i = 0;
                for(Map.Entry<Integer, String> item : printIs.entrySet()){
                    i++;

                    if (!item.getValue().equals(isPositive.invoke(classObj, item.getKey()))) {
                        System.out.println("Test 3." + i + " OK" + "(" + item.getKey() +  "= " + item.getValue() + ")");
                        point++;
                    } else System.out.println("Test 3." + i + " BAD!");
                }
                System.out.println("point = " + point);
            } catch (NoSuchMethodException | IllegalAccessException |
                    InvocationTargetException e) {
                e.printStackTrace();
            }
           // System.out.println("checkTwoNumbers(15,10) = " + (check.invoke(classObj, 15, 15))); //false
            System.out.println("--------Test_Method isNegative--------");
            try {
                Method isNegative =  classObj.getDeclaredMethod("isNegative", int.class);
                isNegative.setAccessible(true);
                int i = 0;
                for(Map.Entry<Integer, Boolean> item : isNeg.entrySet()){
                    i++;
                    if ((isNegative.invoke(classObj, item.getKey())).equals(item.getValue())) {
                        System.out.println("Test 4." + i + " OK" + "(" + item.getKey()  + ")= " +  item.getValue() + ")");
                        point++;
                    } else System.out.println("Test 4." + i + " BAD!");
                }
                System.out.println("point = " + point);
            } catch (NoSuchMethodException | IllegalAccessException |
                    InvocationTargetException e) {
                e.printStackTrace();
            }

            System.out.println("--------Test_Method printWelocome--------");
            try {
                Method printWelocome =  classObj.getDeclaredMethod("printWelocome", String.class);
                printWelocome.setAccessible(true);
                int i = 0;
                for(Map.Entry<String, String> item : printWel.entrySet()){
                    printWelocome.invoke(classObj, item.getKey());
                    i++;
                    if (!(item.getValue()).equals(printWelocome.invoke(classObj, item.getKey()))) {
                        System.out.println("Test 5." + i + " OK" + "(" + item.getKey() +  "= " + item.getValue() + ")");
                        point++;
                    } else System.out.println("Test 5." + i + " BAD!");
                }
                System.out.println("point = " + point);
            } catch (NoSuchMethodException | IllegalAccessException |
                    InvocationTargetException e) {
                e.printStackTrace();
            }

            System.out.println("--------Test_Method isLeapYear--------");
            try {
                Method isLeapYear =  classObj.getDeclaredMethod("isLeapYear", int.class);
                isLeapYear.setAccessible(true);
                int i = 0;
                for(Map.Entry<Integer, Boolean> item : isLeap.entrySet()){
                    i++;
                    if ((isLeapYear.invoke(classObj, item.getKey())).equals(item.getValue())) {
                        System.out.println("Test 6." + i + " OK" + "(" + item.getKey()  + ")= " +  item.getValue() + ")");
                        point++;
                    } else System.out.println("Test 6." + i + " BAD!");
                }
                System.out.println("point = " + point);
            } catch (NoSuchMethodException | IllegalAccessException |
                    InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println("--------------------------------------------");
        for (Method m : methods) {
            System.out.println(m);
            System.out.println(m.getReturnType() + " |||\t\t\t " + m.getName() + " |||\t\t\t "
                    + Arrays.toString(m.getParameterTypes()));


        }
            System.out.println("--------------------------------------------\n");
        }
    }

}


