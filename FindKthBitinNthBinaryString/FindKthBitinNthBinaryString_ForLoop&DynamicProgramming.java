// 

import java.util.*;

public class FindKthBitinNthBinaryString {
	public static String reverse (String input) {
        if (input.length() == 0) {
            return input;
        }
        
        return input.charAt(input.length() - 1) + reverse (input.substring(0, input.length() - 1));
    }
	
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
	
	/*public static String findBits (int n, String start, ArrayList <String> memo) {
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
    }*/
	
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
        //ArrayList <String> memo = new ArrayList <String> ();
        
        //return findBits(n, "0", memo).charAt (k-1);
		
		return findBits(n, "0").charAt (k-1);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println (findKthBit(3,3));
		
		//String input = "ABC";
		//String input = "ABCDEFB";
		
		//String input = "DAVID IS AWESOME";
		
		//System.out.println (reverse(input));
	}

}