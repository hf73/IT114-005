import java.util.*;

public class HW2Loops {
	public static void main(String[] args) {
   
 int[] arr = new int[] {5,6,9,4,8};
   
   for(int num : arr)
   {
      System.out.println(num);
   }
   
   int i, x;
   for(i = 0 ; i<arr.length; ++i){
    x = arr[i]; 
      if(x%2 == 0){
      System.out.println(x + " is an even number");
      }
 }
}
}