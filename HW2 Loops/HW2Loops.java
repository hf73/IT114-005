import java.util.*;

public class HW2Loops {
	public static void main(String[] args) {
   
 int[] arr = new int[] {1,2,3,4,5};
   
   for(int num : arr)
   {
      System.out.println(num);
   }
 
   for(int i = 0 ; i<arr.length; ++i){
      if( i%2 == 0){
      System.out.println(i + " is an even number");
      }
 }
}
}