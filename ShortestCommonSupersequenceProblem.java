
public class ShortestCommonSupersequenceProblem {
	public static int recursiveSolution (String A, String B) {
		if (A.length() == 0) {
			return B.length();
		}
		
		if (B.length() == 0) {
			return A.length();
		}
		
		if (A.charAt(0) == B.charAt(0)) {
			return 1 + recursiveSolution (A.substring(1), B.substring(1));
		}
		
		return Integer.min(1 + recursiveSolution (A.substring(1), B.substring(0)), 1 + recursiveSolution (A.substring(0), B.substring(1)));
	}
	
	public static int twoDimensionalArraySolution (String A, String B) {
		int [][] memoizationLength = new int [A.length()][B.length()+1];
		
		for (int firstIndex = 0; firstIndex <= B.length(); firstIndex ++) {
			for (int secondIndex = 0; secondIndex < A.length(); secondIndex ++) {
				if (firstIndex == 0) {
					if (secondIndex == 0) {
						memoizationLength [secondIndex][firstIndex] = 1;
					}
					else {
	 					memoizationLength [secondIndex][firstIndex] = memoizationLength [secondIndex-1][firstIndex] + 1;
					}
				}
				
				else if (secondIndex == 0) {
					if (B.charAt(firstIndex-1) == A.charAt(secondIndex)) {
						memoizationLength [secondIndex][firstIndex] = memoizationLength [secondIndex][firstIndex-1];
					}
					
					else {
						memoizationLength [secondIndex][firstIndex] = memoizationLength [secondIndex][firstIndex-1] + 1;
					}
				}
				
				else {
					if (B.charAt(firstIndex-1) == A.charAt(secondIndex)) {
						memoizationLength [secondIndex][firstIndex] = memoizationLength [secondIndex][firstIndex-1];
					}
					else {
						memoizationLength [secondIndex][firstIndex] = Integer.min(memoizationLength [secondIndex][firstIndex-1], memoizationLength [secondIndex-1][firstIndex]) + 1;
					}
				}
			}
		}
		
		//for (int i = 0; i <= B.length(); i ++) {
			//for (int j = 0; j < A.length(); j ++) {
				//System.out.print(memoizationLength[j][i] + "   ");
			//}
			//System.out.println();
		//}
		
		return memoizationLength [A.length() - 1][B.length()];
	}
	
	public static int twoSingleDimensionalArraysSolution (String A, String B) {
		String shorter = "";
		String longer = "";
		
		if (A.length() <= B.length()) {
			shorter = A;
			longer = B;
			
			//shorter = B;
			//longer = A;
		}
		
		else if (B.length() < A.length()) {
			shorter = B;
			longer = A;
			
			//shorter = A;
			//longer = B;
		}
		
		int [] memoizationPrevious = new int [longer.length()];
		int [] memoizationCurrent = new int [longer.length()];
		
		for (int firstIndex = 0; firstIndex < shorter.length(); firstIndex ++) {
			for (int secondIndex = 0; secondIndex < longer.length(); secondIndex ++) {
				if (firstIndex == 0) {
					if (secondIndex == 0) {
						if (shorter.charAt(firstIndex) == longer.charAt(secondIndex)) {
							memoizationCurrent [secondIndex] = 1;
						}
						else {
							memoizationCurrent [secondIndex] = 2;
						}
					}
					else {
						//memoizationCurrent [secondIndex] = secondIndex + 1;
						if (shorter.charAt(firstIndex) == longer.charAt(secondIndex)) {
							//memoizationCurrent [secondIndex] = memoizationCurrent [secondIndex - 1];
							memoizationCurrent [secondIndex] = secondIndex + 1;
						}
						else {
							memoizationCurrent [secondIndex] = memoizationCurrent [secondIndex - 1] + 1;
						}
						//memoizationCurrent [secondIndex] = memoizationCurrent [secondIndex - 1] + 1;
					}
				}
				else {
					if (secondIndex == 0) {
						memoizationCurrent [secondIndex] = memoizationPrevious [secondIndex] + 1; 
					}
					else {
						if (shorter.charAt(firstIndex) == longer.charAt(secondIndex)) {
							//memoizationCurrent [secondIndex] = memoizationPrevious [secondIndex];
							//memoizationCurrent [secondIndex] = Integer.min(memoizationPrevious [secondIndex], memoizationCurrent [secondIndex - 1]);
							memoizationCurrent [secondIndex] = memoizationPrevious [secondIndex];
						}
						else {
							memoizationCurrent [secondIndex] = Integer.min(memoizationPrevious [secondIndex], memoizationCurrent [secondIndex - 1]) + 1;
						}
					}
				}
			}
			for (int index = 0; index < longer.length(); index ++) {
				//System.out.print(memoizationCurrent [index] + "    ");
				memoizationPrevious [index] = memoizationCurrent [index];
			}
			//System.out.println();
		}
		
		return memoizationCurrent [longer.length() - 1];
	}
	
	public static int twoSingleDimensionalArraysSolutionImproved (String A, String B) {
		String shorter = "";
		String longer = "";
		
		if (A.length() <= B.length()) {
			shorter = A;
			longer = B;
			
			//shorter = B;
			//longer = A;
		}
		
		else if (B.length() < A.length()) {
			shorter = B;
			longer = A;
			
			//shorter = A;
			//longer = B;
		}
		
		int [] memoizationPrevious = new int [shorter.length()];
		int [] memoizationCurrent = new int [shorter.length()];
		
		for (int firstIndex = 0; firstIndex < longer.length(); firstIndex ++) {
			for (int secondIndex = 0; secondIndex < shorter.length(); secondIndex ++) {
				if (firstIndex == 0) {
					if (secondIndex == 0) {
						if (longer.charAt(firstIndex) == shorter.charAt(secondIndex)) {
							memoizationCurrent [secondIndex] = 1;
						}
						else {
							memoizationCurrent [secondIndex] = 2;
						}
					}
					else {
						//memoizationCurrent [secondIndex] = secondIndex + 1;
						if (longer.charAt(firstIndex) == shorter.charAt(secondIndex)) {
							memoizationCurrent [secondIndex] = memoizationCurrent [secondIndex - 1] + 1;
							//memoizationCurrent [secondIndex] = secondIndex + 1;
						}
						else {
							memoizationCurrent [secondIndex] = memoizationCurrent [secondIndex - 1] + 1;
						}
						//memoizationCurrent [secondIndex] = memoizationCurrent [secondIndex - 1] + 1;
					}
				}
				else {
					if (secondIndex == 0) {
						if (longer.charAt(firstIndex) == shorter.charAt(secondIndex)) {
							//memoizationCurrent [secondIndex] = memoizationPrevious [secondIndex];
							memoizationCurrent [secondIndex] = firstIndex + 1;
						}
						else {
							memoizationCurrent [secondIndex] = memoizationPrevious [secondIndex] + 1; 
						}
					}
					else {
						if (longer.charAt(firstIndex) == shorter.charAt(secondIndex)) {
							//memoizationCurrent [secondIndex] = memoizationPrevious [secondIndex];
							memoizationCurrent [secondIndex] = memoizationCurrent [secondIndex - 1];
						}
						else {
							memoizationCurrent [secondIndex] = Integer.min(memoizationPrevious [secondIndex], memoizationCurrent [secondIndex - 1]) + 1;
						}
					}
				}
			}
			for (int index = 0; index < shorter.length(); index ++) {
				//System.out.print(memoizationCurrent [index] + "    ");
				memoizationPrevious [index] = memoizationCurrent [index];
			}
			//System.out.println();
		}
		
		return memoizationCurrent [shorter.length() - 1];
	}
	
	public static int oneSingleDimensionalArraysSolution (String A, String B) {
		String shorter = "";
		String longer = "";
		
		if (A.length() <= B.length()) {
			shorter = A;
			longer = B;
		}
		
		else if (B.length() < A.length()) {
			shorter = B;
			longer = A;
		}
		
		int [] memoizationLength = new int [shorter.length()];
		
		for (int firstIndex = 0; firstIndex < longer.length(); firstIndex ++) {
			for (int secondIndex = 0; secondIndex < shorter.length(); secondIndex ++) {
				if (firstIndex == 0) {
					if (secondIndex == 0) {
						if (longer.charAt(firstIndex) == shorter.charAt(secondIndex)) {
							memoizationLength [secondIndex] = 1;
						}
						else {
							memoizationLength [secondIndex] = 2;
						}
					}
					else {
						memoizationLength [secondIndex] = memoizationLength [secondIndex - 1] + 1;
					}
				}
				else {
					if (secondIndex == 0) {
						if (longer.charAt(firstIndex) == shorter.charAt(secondIndex)) {
							memoizationLength [secondIndex] = firstIndex + 1;
						}
						else {
							memoizationLength [secondIndex] += 1;
						}
					}
					else {
						if (longer.charAt(firstIndex) == shorter.charAt(secondIndex)) {
							//memoizationCurrent [secondIndex] = memoizationPrevious [secondIndex];
							memoizationLength [secondIndex] = memoizationLength [secondIndex - 1];
						}
						else {
							memoizationLength [secondIndex] = Integer.min(memoizationLength [secondIndex], memoizationLength [secondIndex - 1]) + 1;
						}
					}
				}
			}
		}
		return memoizationLength [shorter.length() - 1];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String x = "ABCBDAB";
		String y = "BDCABA";
		
		//String x = "XYZABC";
		//String y = "XYZ";
		
		//String x = "ABCBDABABCBDAB";
		//String y = "BDCABABDCABA";
		
		//String x = "X";
		//String y = "XBDCABA";
		
		//String x = "AAA";
		//String y = "BBB";
		
		System.out.println (recursiveSolution(x,y));
		System.out.println (twoDimensionalArraySolution(x,y));
		System.out.println (twoSingleDimensionalArraysSolution(x,y));
		System.out.println (twoSingleDimensionalArraysSolutionImproved(x,y));
		System.out.println (oneSingleDimensionalArraysSolution(x,y));
	}
}