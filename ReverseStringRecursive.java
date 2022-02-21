import java.util.*;

public class ReverseString {
	public static String reverse (String input) {
        if (input.length() == 0) {
            return input;
        }
        
        return input.charAt(input.length() - 1) + reverse (input.substring(0, input.length() - 1));
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String input = "ABC";
		//String input = "ABCDEFB";
		
		String input = "DAVID IS AWESOME";
		
		System.out.println (reverse(input));
	}

}
