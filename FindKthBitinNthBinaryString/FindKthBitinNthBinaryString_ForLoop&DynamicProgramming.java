// Solution approched by a Single For Loop and Dynamic Programming
// LeetCode Analysis :
//			Runtime: 56 ms, faster than 62.66% of Java online submissions for Find Kth Bit in Nth Binary String.
//			Memory Usage: 79.4 MB, less than 32.08% of Java online submissions for Find Kth Bit in Nth Binary String.

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
	
	public static String findBits (int n, String start) {
		ArrayList <String> memo = new ArrayList <String> ();
        
        memo.add(start);
        
        for (int i = 0; i < n; i ++) {
            if (memo.size() < 3) {
                memo.add(memo.get(memo.size()-1) + "1" + modify(memo.get(memo.size()-1)));
            }
            else {
                String concanated = memo.get(memo.size()-1) + "1" + memo.get(memo.size()-1-1) + "0" + memo.get(memo.size()-1).substring(memo.get(memo.size()-1-1).length()+1, memo.get(memo.size()-1).length());
                
                memo.add(concanated);
						}
        }
        return memo.get(memo.size()-1);
	}
	
	public static char findKthBit(int n, int k) {
		return findBits(n, "0").charAt (k-1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println (findKthBit(3,3));
	}
}
