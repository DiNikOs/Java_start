/*
 *Java, hw1
 * @author Dmitriy Ostrovskiy
 * @version 1.2
*/ 

public class Hw1_1 {
    public static void main (String[] args){
        byte t =64;
        short s=2048;
        int i=256000;
        long l=25600000000000L;
        float f=128.5f;
        double g=12.8124;
        boolean n=true;
        char h='C';

        System.out.println("byte b= " + t);
        System.out.println("short s =" + s);
        System.out.println("int i = " + i);
        System.out.println("long l = " + l);
        System.out.println("float f = " + f);
        System.out.println("double d = " + g);
        System.out.println("boolean a = " + n);
        System.out.println("char c = " + h);                                    //��_1.2
        System.out.println("a*(b+(c/d))= " + calculate(1,2,3,4));     		//��_1.3
        System.out.println("10<=(one+two)>=20 = " + task10and20(7,10)); 	//��_1.4
        isPositiveOrNegative(7);                                            	//�� 1.5
        System.out.println("����� ������������� " + isNegative(-7));       	//�� 1.6
        greetings ("������");                                             	//�� 1.7
        getYear(2000);                                                          //�� 1.8

    }
    public static float calculate(float a,float b, float c, float d){           //��_1.3
          float result= 0;
            if (d==0) System.out.println("����� d �� ������ ���� = 0");
            else result=a*(b+(c/d));
       return  result;
    }
    public static boolean task10and20(int one,int two){                          //��_1.4
        int sym= one + two;
        if (sym<=20&&sym>=10)return true;
        else return false;
    }
    public static void isPositiveOrNegative(int num){                            //�� 1.5
        if (num>=0) System.out.println(num +  " ������������� �����");
        else System.out.println(num +  " ������������� �����");
    }
    public static boolean isNegative(int num){                                   //�� 1.6
        if (num<=0) return true ;
        else return false;
    }
    public static void greetings(String name){                                   //�� 1.7
        System.out.println("������, "  + name);
    }
    public static void getYear(int year){                                        //�� 1.8
        if (year%4!=0||(year%400!=0&&year%100==0))  {
            System.out.println(year +  " ��� �� ����������" );
            return;
        }
        else if (year%4==0) System.out.println(year +  " ��� ����������" );
    }
}
