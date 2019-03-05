/**
 * Java2. Core. HW2
 * 
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 06, 2019
 */
package hw2;
import java.util.*;

class ArrayMainHW2 {
    
    public static void MyArray(String array[][]) throws MyArraySizeException, MyArrayDataException{
        int sum = 0;
        int t = 0;   
        String[][] arr = new String [4][4]; 
            for (int i = 0; i < array.length; i++) {
                t = i;
                for (int j = 0; j < array[i].length; j++) {
                    System.out.print(array[i][j] + " ");
                    try{                 
                        arr[i][j] = array[i][j]; 
                        sum += Integer.parseInt(array[i][j]);
                    } catch (NumberFormatException e) { // 
                        System.out.println("Data array not good [" + (i+1) + "][" + (j+1) + "] " + e.getMessage()) ;
                    } catch (ArrayIndexOutOfBoundsException e) { // 
                        System.out.println("Size array not good! [" + (i+1) + "][" + (j+1) + "] " + e.getMessage()) ; 
                        }
                }
                System.out.println();  
            }
        System.out.println ("Sum int [4][4] = " + sum); 
            for (int i = 0; i < array.length; i++) {
                t = i;
                for (int j = 0; j < array[i].length; j++) {
                  
                    try{                  
                        arr[i][j] = array[i][j]; 
                        sum += Integer.parseInt(array[i][j]);
                       
                    } catch (NumberFormatException e) {  
                        throw new MyArrayDataException ("First Data array not good [" + (i+1) + "][" + (j+1) + "]" ); 
                        }  catch (ArrayIndexOutOfBoundsException e) {
                        throw new MyArraySizeException ("First Size array not good! ");
                        }
                }
                System.out.println();
            } 
    }    
    
    public static void main(String[] args) throws NumberFormatException,ArrayIndexOutOfBoundsException {
            try {
        String [][] array = {{"1","2","3","4"},{"1","2","3","4"},{"1","2","3","4"},{"1","2","3","4"}};
        String [][] array2 = {{"1","2","3","4"},{"1","d","3","4"},{"1","2","3","x"},{"1","2","3","4"}};
        String [][] array3 = {{"1","2","3","4"},{"1","2","3","4","5","6"},{"1","2","3","4"},{"1","2","3","4","5"}};        
        
        MyArray (array);
       // MyArray (array2); // select array2 or array3
        MyArray (array3); // select array2 or array3
        } catch (MyArrayDataException |MyArraySizeException  e) {
           // System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

class MyArraySizeException extends Exception {
    private String array;

    public String getArray() {
        return array;
    }

    public MyArraySizeException(String msg) {
        super(msg);
        }
    }


class MyArrayDataException extends Exception {
    private String array;

    public String getArray() {
        return array;
    }

    public MyArrayDataException(String msg) {
        super(msg);
        }
    }
