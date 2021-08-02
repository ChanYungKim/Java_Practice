import java.util.ArrayList; 

public class LongestRepeatedSubsequenceProblem {
	static boolean DEBUG = false;
	//static boolean DEBUG = true;
	
	// brute-force solution
	public static int naiveSolution (String question) {
		if (DEBUG == true) System.out.println(question);
		
		if (question.length() == 0) return 0;
		
		int length = 0;
		int longest = 0;
		for (int firstIndex = 0; firstIndex < question.length(); firstIndex ++) {
			for (int secondIndex = firstIndex + 1; secondIndex < question.length(); secondIndex ++) {
				if (question.charAt(firstIndex) == question.charAt(secondIndex)) {
					String newQuestion = question.substring(firstIndex + 1, secondIndex) + question.substring(secondIndex + 1);
					length = 1 + naiveSolution (newQuestion); 
				}
				
				longest = Integer.max (longest, length);
			}
		}
		
		return longest;
	}
	
	// brute-force recursive approach solution
	public static int recursiveSolution (int index , String question) {
		if (DEBUG == true)  System.out.println(question);
		
		if (index == 0) index ++;
		
		if (index == question.length()) return recursiveSolution(0, question.substring(1));
		
		if (index >= question.length()) return 0;
		
		int length = 0;
		int longest = 0;
		if (question.charAt(0) == question.charAt(index)) {
			String newQuestion = question.substring(1, index) + question.substring(index + 1);
			
			//System.out.println(newQuestion);
			
			length = 1 + recursiveSolution (0, newQuestion);
		}
		
		return Integer.max(recursiveSolution (index + 1, question), length);
	}
	
	// dynamic programming approach solution by using two-dimensional array
	// NOTE : This solution does not work when there are more than 1 same letters consecutively 
	public static int twoDimensionalArraySolution (String question) {
		int longest = 0;
		
		int [][] memoizationLength = new int [question.length()][question.length()];
		
		for (int firstIndex = 0; firstIndex < question.length(); firstIndex ++) {
			for (int secondIndex = firstIndex + 1; secondIndex < question.length(); secondIndex ++) {
				if (firstIndex == 0) {
					if (question.charAt(firstIndex) == question.charAt(secondIndex)) { 
						memoizationLength [secondIndex][firstIndex] += 1;
					} 
					
					else memoizationLength [secondIndex][firstIndex] =  memoizationLength [secondIndex-1][firstIndex];
				}
				
				else {
					if (question.charAt(firstIndex) == question.charAt(secondIndex)) {
						memoizationLength [secondIndex][firstIndex] = memoizationLength [secondIndex - 1][firstIndex - 1] + 1;
					}
					else memoizationLength [secondIndex][firstIndex] = Integer.max(memoizationLength [secondIndex][firstIndex-1], memoizationLength [secondIndex-1][firstIndex]);
				}
				
				longest = Integer.max(longest, memoizationLength [secondIndex][firstIndex]);
			}
		}
		
		if (DEBUG == true) {
			for (int firstIndex = 0; firstIndex < question.length(); firstIndex ++) {
				for (int secondIndex = 0; secondIndex < question.length(); secondIndex ++) {
					System.out.print(memoizationLength [secondIndex][firstIndex] + "    ");
				}
				System.out.println();
			}
		}
		if (longest * 2 > question.length()) {
			if (longest % 2 == 0) return longest/2;
			
			else return (int) Math.ceil ((double) longest / 2); 
		}
		return longest;
	}
	
	public static boolean isFound (ArrayList <Character> foundList, char target) {
		for (int listIndex = 0; listIndex < foundList.size(); listIndex ++) {
			if (foundList.get(listIndex) == target) return true;
		}
		return false;
	}
	
	public static int twoDimensionalArraySolutionImproved (String question) { 
		ArrayList <Character> found = new ArrayList <Character> (26);
		
		int longest = 0;
		
		int [][] memoizationLength = new int [question.length()][question.length()];
		
		for (int firstIndex = 0; firstIndex < question.length(); firstIndex ++) {
			for (int secondIndex = firstIndex + 1; secondIndex < question.length(); secondIndex ++) {
				if (firstIndex == 0) {
					if (question.charAt(firstIndex) == question.charAt(secondIndex)) { 
						if (isFound(found, question.charAt(firstIndex)) == false) {
							found.add(question.charAt(firstIndex));
						}
						memoizationLength [secondIndex][firstIndex] += 1;
					} 
					
					else memoizationLength [secondIndex][firstIndex] =  0;
				}
				
				else {
					if (question.charAt(firstIndex) == question.charAt(secondIndex)) {
						if (isFound(found, question.charAt(firstIndex))) {
							memoizationLength [secondIndex][firstIndex] += 1;
						}
						
						else {
							found.add(question.charAt(firstIndex));
							memoizationLength [secondIndex][firstIndex] = longest + 1;
						}
						
					}
					else {
						memoizationLength [secondIndex][firstIndex] = 0;
					}
				}
				
				longest = Integer.max(longest, memoizationLength [secondIndex][firstIndex]);
			}
		}
		
		if (longest == 1) {
			int testSameLetterIndexAscending = 0;
			int testSameLetterIndexDecending = question.length() - 1;
			
			//System.out.println (testSameLetterIndexDecending);
			//System.out.println (question.charAt(testSameLetterIndexDecending));
			
			boolean isAllSame = true;
			
			while (testSameLetterIndexAscending < testSameLetterIndexDecending) {
				if (question.charAt(testSameLetterIndexAscending) != question.charAt(testSameLetterIndexDecending)) {
					isAllSame = false;
				}
				
				testSameLetterIndexAscending ++;
				testSameLetterIndexDecending --;
				
				//System.out.println (testSameLetterIndexAscending);
				//System.out.println (testSameLetterIndexDecending);
				
				//System.out.println (testSameLetterIndexAscending == testSameLetterIndexDecending);
			}
			
			if (isAllSame == true) {
				return question.length()/2;
			}
		}
		
		if (DEBUG == true) {
			for (int firstIndex = 0; firstIndex < question.length(); firstIndex ++) {
				for (int secondIndex = 0; secondIndex < question.length(); secondIndex ++) {
					System.out.print(memoizationLength [secondIndex][firstIndex] + "    ");
				}
				System.out.println();
			}
		}
		
		return longest;
	}
	
	// dynamic programming solution by using two single-dimensional arrays approach
	public static int twoSingleDimensionalArraysSolution (String question) {
		ArrayList <Character> found = new ArrayList <Character> (26);
		
		int longest = 0;
		
		int [] currentMemoization = new int [question.length()];
		int [] previousMemoization = new int [question.length()];		
		
		for (int firstIndex = 0; firstIndex < question.length(); firstIndex ++) {
			for (int secondIndex = firstIndex + 1; secondIndex < question.length(); secondIndex ++) {
				if (firstIndex == 0) {
					if (question.charAt(firstIndex) == question.charAt(secondIndex)) { 
						if (isFound(found, question.charAt(firstIndex)) == false){
							found.add(question.charAt(firstIndex));
						}
						currentMemoization [secondIndex] += 1;
					} 
					
					else currentMemoization [secondIndex] =  0;
				}
								
				else {
					if (question.charAt(firstIndex) == question.charAt(secondIndex)) {
						if (isFound(found, question.charAt(firstIndex))) {
							currentMemoization [secondIndex] = previousMemoization [secondIndex];
						}
						else {
							found.add(question.charAt(firstIndex));
							currentMemoization [secondIndex] = longest + 1;
						}
					}
					
					else {
						currentMemoization [secondIndex] = 0;
					}
				}
				longest = Integer.max(longest, currentMemoization [secondIndex]);
			}
			
			if (DEBUG == true) {
				for (int i = 0; i < currentMemoization.length; i ++) {
					System.out.print(currentMemoization [i] + "   ");
					previousMemoization [i] = currentMemoization[i];
				}
				System.out.println();
			}
		}
		
		if (longest == 1) {
			int testSameLetterIndexAscending = 0;
			int testSameLetterIndexDecending = question.length() - 1;
			
			boolean isAllSame = true;
		
			while (testSameLetterIndexAscending < testSameLetterIndexDecending) {
				if (question.charAt(testSameLetterIndexAscending) != question.charAt(testSameLetterIndexDecending)) {
					isAllSame = false;
				}
			
				testSameLetterIndexAscending ++;
				testSameLetterIndexDecending --;
			
				//System.out.println (testSameLetterIndexAscending);
				//System.out.println (testSameLetterIndexDecending);
			
				//System.out.println (testSameLetterIndexAscending == testSameLetterIndexDecending);
			}
		
			if (isAllSame == true) {
				return question.length()/2;
			}
		}
		
		return longest;
	}
	
	//dynamic programming solution by using one single-dimensional array approach
	public static int singleDimensionalArraySolution (String question) {
		ArrayList <Character> found = new ArrayList <Character> (26);
		
		int longest = 0;
		
		int [] lengthMemoization = new int [question.length()];
		
		for (int firstIndex = 0; firstIndex < question.length(); firstIndex ++) {
			for (int secondIndex = firstIndex + 1; secondIndex < question.length(); secondIndex ++) {
				if (firstIndex == 0) {
					if (question.charAt(firstIndex) == question.charAt(secondIndex)) {
						if (isFound(found, question.charAt(firstIndex)) == false) {
							found.add(question.charAt(firstIndex));
						}
						lengthMemoization [secondIndex] += 1;
					}
					
					else lengthMemoization [secondIndex] = 0;
				}
				
				else {
					if (question.charAt(firstIndex) == question.charAt(secondIndex)) {
						if (isFound(found, question.charAt(firstIndex)) == false) {
							found.add(question.charAt(firstIndex));
							lengthMemoization [secondIndex] = longest + 1;
						}
					}
					else {
						lengthMemoization [secondIndex] = 0;
					}
				}
				longest = Integer.max(longest, lengthMemoization [secondIndex]);
			}
		}
		
		if (longest == 1) {
			int testSameLetterIndexAscending = 0;
			int testSameLetterIndexDecending = question.length() - 1;
			
			boolean isAllSame = true;
		
			while (testSameLetterIndexAscending < testSameLetterIndexDecending) {
				if (question.charAt(testSameLetterIndexAscending) != question.charAt(testSameLetterIndexDecending)) {
					isAllSame = false;
				}
			
				testSameLetterIndexAscending ++;
				testSameLetterIndexDecending --;
			
				//System.out.println (testSameLetterIndexAscending);
				//System.out.println (testSameLetterIndexDecending);
			
				//System.out.println (testSameLetterIndexAscending == testSameLetterIndexDecending);
			}
		
			if (isAllSame == true) {
				return question.length()/2;
			}
		}
		
		return longest;
	}
	
	public static int singleVariableSolution (String question) {
		ArrayList <Character> found = new ArrayList <Character> (26);
		
		int longest = 0;
		
		for (int firstIndex = 0; firstIndex < question.length(); firstIndex ++) {
			for (int secondIndex = firstIndex + 1; secondIndex < question.length(); secondIndex ++) {
				if (question.charAt(firstIndex) == question.charAt(secondIndex)) {
					if (isFound (found, question.charAt(firstIndex)) == false) {
						found.add(question.charAt(firstIndex));
						longest = Integer.max(longest, ++ longest);
					}
					else {
						longest = 1;
					}
				}
			}
		}
		
		if (longest == 1) {
			int testSameLetterIndexAscending = 0;
			int testSameLetterIndexDecending = question.length() - 1;
			
			boolean isAllSame = true;
		
			while (testSameLetterIndexAscending < testSameLetterIndexDecending) {
				if (question.charAt(testSameLetterIndexAscending) != question.charAt(testSameLetterIndexDecending)) {
					isAllSame = false;
				}
			
				testSameLetterIndexAscending ++;
				testSameLetterIndexDecending --;
			
				//System.out.println (testSameLetterIndexAscending);
				//System.out.println (testSameLetterIndexDecending);
			
				//System.out.println (testSameLetterIndexAscending == testSameLetterIndexDecending);
			}
		
			if (isAllSame == true) {
				return question.length()/2;
			}
		}
		
		return longest;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String question = "ATACTCGGA";
		
		//String question = "ATACTCGGA1G";
		
		//String question = "ATACTCGGG";
		
		//String question = "DDDD";
		
		//String question = "DDDDD";
		
		//String question = "DDDDDD";
		
		//String question = "DDDDDDD";
		
		//String question = "DDDDDDDD";
		
		//String question = "XYZAA";
		
		//String question = "GGXYZBBEFGCCOPQ";
		
		//String question = "A123A4567B89B!@#C$%^&*()CDEFGD";
		
		//String question = "A123A4567B89B!@#C$%^&*()CDEFGD";
		
		String question = "A1A2B3B4C$%^C";
		
		//String question = "A1A2B3B4C567C";
		
		//String question = "A1A2B3B4C5C6D7D";
		
		System.out.println(naiveSolution (question));
		
		System.out.println(recursiveSolution (0, question));
		
		System.out.println (twoDimensionalArraySolutionImproved (question));
		
		System.out.println (twoSingleDimensionalArraysSolution (question));
		
		//System.out.println(twoDimensionalArraySolution (question));
		
		System.out.println(singleDimensionalArraySolution (question));
		
		System.out.println(singleVariableSolution (question));
	}
}
