public class LongestCommonSubstringProblem {
	
	// helper function for recursive solution
	public static int theLongest (int a, int b, int c) {
		return Integer.max(a, Integer.max(b, c));
	}
	
	// recursive solution
	public static int recursiveSolutionFromFront (String a, String b, int length) {
		int optionOne = 0;
		int optionTwo = 0;
		int optionThree = 0;
		
		if (a.length() != 0 && b.length() != 0) {
			optionOne = recursiveSolutionFromFront (a, b.substring(1), 0);
			optionTwo = recursiveSolutionFromFront (a.substring(1), b, 0);
			
			if (a.charAt(0) == b.charAt(0)) {
				optionThree = recursiveSolutionFromFront (a.substring(1), b.substring(1), length + 1);
			}
			
			else {
				return Integer.max(recursiveSolutionFromFront (a, b.substring(1), 0), recursiveSolutionFromFront (a.substring(1), b, 0));
			}
		}
		
		else return length;
		
		return theLongest (optionOne, optionTwo, optionThree);
	}
	
	// dynamic programming solution by using 2 dimensional array
	public static int twoDimensionalArrayApproachSolution (String a, String b) {
		int longest = 0;
		
		int [][] commonSubstring = new int [b.length()][a.length()];
		
		for (int bIndex = 0; bIndex < b.length(); bIndex ++) {
			for (int aIndex = 0; aIndex < a.length(); aIndex ++) {
				if (bIndex == 0 || aIndex == 0) {
					if (b.charAt(bIndex) == a.charAt(aIndex)) {
						commonSubstring [bIndex][aIndex] = 1;
					}
					else commonSubstring [bIndex][aIndex] = 0;
				}
				
				else {
					if (b.charAt(bIndex) == a.charAt(aIndex)) {
						commonSubstring [bIndex][aIndex] = commonSubstring [bIndex-1][aIndex-1] + 1;
					}
					else commonSubstring [bIndex][aIndex] = 0;
				}
				
				longest = Integer.max(longest, commonSubstring [bIndex][aIndex]);
			}
		}
		
		return longest;
	}
	
	// dynamic programming solution by using two single dimensional arrays
	public static int twoArraysApproach (String a, String b) {
		int longest = 0;
		
		String shorter;
		String longer;
		
		if (a.length() <= b.length()) {
			shorter = a;
			longer = b;
		}
		
		else {
			shorter = b;
			longer = a;
		}
		
		int [] commonSubstringPrevious = new int [shorter.length()];
		int [] commonSubstringCurrent = new int [shorter.length()];
		
		for (int longerIndex = 0; longerIndex < longer.length(); longerIndex ++) {
			for (int shorterIndex = 0; shorterIndex < shorter.length(); shorterIndex ++) {
				if (longerIndex == 0 || shorterIndex == 0) {
					if (longer.charAt(longerIndex) == shorter.charAt(shorterIndex)) {
						commonSubstringCurrent [shorterIndex] = 1;
					}
					else commonSubstringCurrent [shorterIndex] = 0;
				}
				
				else {
					if (longer.charAt(longerIndex) == shorter.charAt(shorterIndex)) {
						commonSubstringCurrent [shorterIndex] = commonSubstringPrevious [shorterIndex-1] + 1;
					}
					else commonSubstringCurrent [shorterIndex] = 0;
				}
			longest = Integer.max(longest, commonSubstringCurrent [shorterIndex]);
			}
			
			for (int arrayIndex = 0; arrayIndex < commonSubstringCurrent.length; arrayIndex ++) {
				//System.out.print(commonSubstringCurrent [arrayIndex] + "    ");
				commonSubstringPrevious [arrayIndex] = commonSubstringCurrent [arrayIndex];
			}
			//System.out.println();
		}
		return longest;
	}
	
	// dynamic programming solution by using one single dimensional array
	/*public static int singleArrayApproach (String a, String b) {
		int longest = 0;
		
		String shorter;
		String longer;
		
		if (a.length() <= b.length()) {
			shorter = a;
			longer = b;
		}
		
		else {
			shorter = b;
			longer = a;
		}
		
		int [] commonSubstringLength = new int [shorter.length()];
		
		int temp = 0;
		for (int longerIndex = 0; longerIndex < longer.length(); longerIndex ++) {
			for (int shorterIndex = 0; shorterIndex < shorter.length(); shorterIndex ++) {
				//temp = commonSubstringLength [shorterIndex];
				if (shorterIndex == 0) {
					temp = commonSubstringLength [shorterIndex];
					if (longer.charAt(longerIndex) == shorter.charAt(shorterIndex)) {
						commonSubstringLength [shorterIndex] = 1;
					}
					else {
						commonSubstringLength [shorterIndex] = 0;
					}
					//temp = commonSubstringLength [shorterIndex];
				}
				
				else {
					//temp = commonSubstringLength [shorterIndex];
					if (longer.charAt(longerIndex) == shorter.charAt(shorterIndex)) {
						commonSubstringLength [shorterIndex] = temp + 1;
					}
					else {
						commonSubstringLength [shorterIndex] = 0;
					}	
				}				
				longest = Integer.max(longest, commonSubstringLength [shorterIndex]);
			}
			for (int i = 0; i < commonSubstringLength.length; i ++) {
				System.out.print(commonSubstringLength[i] + "   ");
			}
			
			System.out.println();
		}
		
		return longest;
	}*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String a = "ABABC";
		//String b = "BABCA";
		
		//String a = "ABAB";
		//String b = "BABA";
		
		//String a = "AABAAAAA";
		//String b = "ACAAA";
		
		String a = "AABAAA";
		String b = "AACAAA";
		
		// trying naive recursive approach
		System.out.println(recursiveSolutionFromFront (a, b, 0));
		
		// trying the 2D-array approach 
		System.out.println(twoDimensionalArrayApproachSolution (a, b));
		
		// trying two single dimensional arrays approach
		System.out.println(twoArraysApproach(a, b));
	}

}