/**
* Java.Java_Start.HW2
*
*@ Dmitriy Ostrovskiy
*@ version date Jan 17,2019
*/
import java.util.Arrays;

public class HW2Lesson {
    public static void main(String[] args){
		invertArray();									//2.1
		fillArray();									//2.2
		changeArray();									//2.3
		fillDiogonal(5);								//2.4
		findMinMax();									//2.5
		int []arr = {1,5,3,2,11,4,5,2,4,8,7};
		compareSum(arr);								//2.6		
		//offsetArray(4);								//2.7	not finished
		
	}

    public static void invertArray(){					//2.1
     	int[] arr = {1,1,0,0,1,0,1,1,0,0};	  
		for (int i = 0; i < arr.length; i++){
			arr[i] = arr[i]==0? arr[i]+1 : arr[i]-1;
		}
		System.out.println(Arrays.toString(arr));
  }  
    public static void fillArray(){						//2.2
     	int[] arr = new int [8];	
		int j = 0;
		for (int i = 0; i < arr.length; i++){
			arr[i] = j;
			j+=3;
		}
		System.out.println(Arrays.toString(arr));
  }
    public static void changeArray(){					//2.3
     	int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};	  
		for (int i = 0; i < arr.length; i++){
			if (arr[i] < 6) arr[i] = arr[i]*2;
		}
		System.out.println(Arrays.toString(arr));
  }  
    public static void fillDiogonal(int len){			//2.4
     	int[][] arr = new int[len][len];	
		for (int i = 0; i < len; i++){		
			for (int j = 0; j < len; j++){		
				arr[i][j] = (i == j)? 1 : 0;
				if (i == len-1-j) arr[i][j] = 1 ;
				System.out.print(arr[i][j] + " ");
				}	
			System.out.println();
		}		
  }    
    public static void findMinMax(){					//2.5
		int[] arr ={1,5,3,2,11,4,5,2,4,8,9,1};		
		int Max = arr[arr.length-1];
		int Min = arr [0];
			for (int i = 0; i < arr.length; i++){
				if (arr[i] <=  Min) Min = arr [i];
				if (arr[i] >=  Max) Max = arr [i];	
				}
		System.out.println(Arrays.toString(arr) + " Min= " + Min + ",Max= " + Max);		
  }
    public static void compareSum(int[] arr){			//2.6		
		boolean sum = false;
		for (int j = 0 ; j < arr.length ; j++){
			int sum1 = 0;
			int sum2 = 0;		
			for (int i = 0; i < j+1 ; i++) sum1 = sum1 + arr [i];
			for (int i = j+1; i < arr.length ; i++) sum2 = sum2 + arr [i];	
			if (sum1==sum2) { 
				sum = true;
				break;
				}
			else sum = false;
		}
		System.out.println(Arrays.toString(arr) + " Half array equal? " + sum);		
  }	
 /*  not finished
    public static void offsetArray(int n){				//2.7	
		int[] arr ={0,1,2,3,4,5,6,7,8,9};	
		System.out.println(Arrays.toString(arr) + " offset " + n );
		int arr0 = arr[0];
		int arrN = arr[n];
				 while (true) {							 
					for (int i = 0; i <arr.length-n-1 ; i++) { 
						int j = arr[i] ;							
						arr[i] = arr[i+n] ;
						arr[i+n] = j;
						}
						if (arrN == arr[n] && arr0==arr[arr.length-n]) break;
					}					
		System.out.println(Arrays.toString(arr));			
  }	*/ 
}  