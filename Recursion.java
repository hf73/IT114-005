public class Recursion {
/*  
	public static int sum(int num) {
		if (num > 0) {
			return num + sum(num - 1);
		}
		return 0;
	}
     

	public static void main(String[] args) {
		System.out.println(sum(10));
	}
*/
   
   public static int recursionLoop(int input){
   int count = 0;
      for (int i = 0; i<input; i++){
         count = input + recursionLoop(input-1);
         }
         
      return count;
      
   }
   
	public static void main(String[] args) {
   
      System.out.println(recursionLoop(10));

   }
 
 }
