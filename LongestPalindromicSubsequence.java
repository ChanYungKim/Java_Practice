import java.util.HashMap;
import java.util.Map;

public class LongestPalindromicSubsequence {	
	static boolean DEBUG = false;
	// recursive solution
	public static int recursiveSolutionPalindromeSubsequence (String x) {
		if (x.length() == 0) return 0;
		if (x.length() == 1) return 1;
		
		if (x.charAt(0) == x.charAt(x.length()-1)) {
			return 2 + recursiveSolutionPalindromeSubsequence(x.substring(1,x.length()-1));
		}
		
		return Integer.max(recursiveSolutionPalindromeSubsequence(x.substring(1)), recursiveSolutionPalindromeSubsequence(x.substring(0,x.length()-1)));
	}
	
	// dynamic programming solution by using HashMap memoization
	// when the code of Main is modifiable as the HashMap needs to be declared in the Main
	public static int hashmapApproachPalindromeSubsequence (String x, Map <String, Integer> memoization) {
		if (x.length() == 0) return 0; 
		if (x.length() == 1) return 1;
		
		if (!memoization.containsKey(x)) {
			if (x.charAt(0) == x.charAt(x.length()-1)) {
				memoization.put(x, 2 + hashmapApproachPalindromeSubsequence(x.substring(1,x.length()-1), memoization));
			}
		
			else memoization.put(x, Integer.max(hashmapApproachPalindromeSubsequence(x.substring(1), memoization), hashmapApproachPalindromeSubsequence(x.substring(0,x.length()-1), memoization)));
		}
		
		return memoization.get(x);
	}
	
	/*// dynamic programming solution by using 2 dimensional array memoization
	public static int twoDimensionalArrayApproachPalindromeSubsequence (String x) {
		int [][] palindromeLength = new int [x.length()][x.length()];
		
		int difference = 0;
		
		int longest = 0;
		
		while (difference < x.length()) {
			for (int index = 0; index < x.length(); index ++) {
				if (difference + index >= x.length()) break;
				else if (difference == 0) palindromeLength [index][index + difference] = 1;
				
				else if (x.charAt(index) == x.charAt(difference)) {
					palindromeLength [index][index + difference] = 2 + palindromeLength [index + 1][index + difference - 1]; 
				}
				
				else {
					palindromeLength [index][index + difference] = Integer.max(palindromeLength [index][index + difference - 1], palindromeLength [index + 1][index + difference]);
				}
				
				longest = Integer.max(longest, palindromeLength [index][index + difference]);
			}
			difference ++;
		}
		return longest;
	}*/
	
	// dynamic programming solution by using 2 dimensional array memoization
	public static int twoDimensionalArrayApproachPalindromeSubsequence (String x) {
		int [][] palindromeLength = new int [x.length()][x.length()];
			
		int difference = 0;
			
		int longest = 0;
			
		while (difference < x.length()) {
			int index = 0;
			while (index + difference < x.length()) {
				if (difference == 0) palindromeLength [index][index + difference] = 1;
					
				else if (x.charAt(index) == x.charAt(difference)) {
					palindromeLength [index][index + difference] = 2 + palindromeLength [index + 1][index + difference - 1]; 
				}
					
				else {
					palindromeLength [index][index + difference] = Integer.max(palindromeLength [index][index + difference - 1], palindromeLength [index + 1][index + difference]);
				}
					
				longest = Integer.max(longest, palindromeLength [index][index + difference]);
					
				index ++;
			}
			difference ++;
		}
		
		return longest;
	}
	
	// dynamic programming solution by using just a single one dimensional array memoization
	// improving the memory complexity from 2 dimensional array to a single 1 dimensional array
	public static int oneArrayApproachPalindromeSubsequence (String x) {
		int [] palindromeLength = new int [x.length()];
			
		int difference = 0;
			
		int longest = 0;
				
		while (difference < x.length()) {
			int index = 0;				
			while (index + difference < x.length()) {					
				if (difference == 0) {
					palindromeLength [index] = 1;
				}
					
				else if (x.charAt(index) == x.charAt(index + difference)) {
					if (difference == 1) {
						palindromeLength [index] = palindromeLength [index] + difference;						
					}
					else {
						palindromeLength [index] = palindromeLength [index] + 2;
					}
				}
					
				else {
					palindromeLength [index] = Integer.max(palindromeLength [index], palindromeLength [index + 1]);
				}
					
				longest = Integer.max(longest, palindromeLength [index]); 
				index ++;
			}
			
			if (DEBUG) {
				for (int i = 0; i < x.length(); i ++) {
					System.out.print(palindromeLength[i] + "   ");
				}
				System.out.println();
			}
				
			difference ++;
		}
		return longest;
	
	}
	
	public static void main(String[] args) { 
		// TODO Auto-generated method stub
		
		String a = "ABBDCACB";
		
		//String a = "QWERTYUIOPVAVSDFGHJKL"; // VAV
		
		//String a = "ABBAD";
		
		//String a = "ABCDE";
		
		System.out.println (recursiveSolutionPalindromeSubsequence (a));
		
		Map <String, Integer> memoization = new HashMap <> ();
		System.out.println (hashmapApproachPalindromeSubsequence (a, memoization));
		
		System.out.println (twoDimensionalArrayApproachPalindromeSubsequence (a));
		
		System.out.println (oneArrayApproachPalindromeSubsequence (a));
	}
}