// Solution approched by Recursive, Dynamic Programming, Memoization
// LeetCode Analysis :
// 	Runtime: 13 ms, faster than 66.17% of Java online submissions for Find Kth Bit in Nth Binary String.
// 	Memory Usage: 45.8 MB, less than 60.65% of Java online submissions for Find Kth Bit in Nth Binary String.

import java.util.*;

public class FindKthBitinNthBinaryString {
	
	public static String modify (String input) {
        if (input.length() == 0) {
            return input;
        }
        
        String output = "";
        
        if (input.charAt(input.length() - 1) == '0') {
            output = "1";
        }
        else {
            output = "0";
        }
        
        return output += modify (input.substring(0, input.length() - 1));
    }
	
	public static String findBits (int n, String start, ArrayList <String> memo) {
        memo.add(start);
        //System.out.println (n + "  :  " + memo.toString()); // Debugging Purpose
        
        if (n == 1) {
            return memo.get(memo.size()-1);
        }
        if (memo.size() < 3) {
            return findBits(n-1, start + "1" + modify(start), memo);
        }
        
        String sNow = "";
        String backBits = memo.get(memo.size()-1).substring(memo.get(memo.size()-1-1).length()+1, memo.get(memo.size()-1).length()); 
        sNow = memo.get(memo.size()-1) + "1" + memo.get(memo.size()-1-1) + "0" + backBits;
        
        return findBits(n-1, sNow, memo);
    }
	
	public static char findKthBit(int n, int k) {
        ArrayList <String> memo = new ArrayList <String> ();
        
        return findBits(n, "0", memo).charAt (k-1);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println (findKthBit(3,3));
	}

}
