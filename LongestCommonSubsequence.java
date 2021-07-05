import java.util.HashMap;

public class LongestCommonSubsequence {
	static boolean DEBUG = false; 
	
	// by recursive approach from the front
	public static int recursiveLCSLengthFromFront (int xCounter, int yCounter, String x, String y) {
	    //System.out.println(x + "     " + y);
	    //if (xCounter != x.length()-1 && yCounter != y.length()-1) {
	    if (x.length() != 0 && y.length() != 0) {
	      if (x.charAt(0) == y.charAt(0)) {
	        return recursiveLCSLengthFromFront (xCounter+1, yCounter+1, x.substring(1, x.length()), y.substring(1, y.length())) + 1;
	      }
	      return Integer.max(recursiveLCSLengthFromFront(xCounter+1, yCounter, x.substring(1, x.length()), y), recursiveLCSLengthFromFront(xCounter, yCounter+1, x, y.substring(1, y.length())));
	    }

	    else return 0;
	  }
	// by recursive approach from the end	
	public static int recursiveLCSLengthFromBack(int m, int n, String x, String y) {
	    if (x.length() == 0 || y.length() == 0) {
	      return 0;
	    }

	    if (x.charAt(m) == y.charAt(n)) {
	      return recursiveLCSLengthFromBack(m-1, n-1, x.substring(0, m), y.substring(0, n)) + 1;
	    }
	    return Integer.max(recursiveLCSLengthFromBack(m-1, n, x.substring(0, m), y), recursiveLCSLengthFromBack(m, n-1, x, y.substring(0, n)));
	  }
	  
	// by using HashMap memoization
	public static int hashmapApproachLCSLength (int m, int n, String x, String y, HashMap <String, Integer> recordingTable) {
	    if (x.length() == 0 || y.length() == 0) {
	      return 0;
	    }

	    String key = m + "|" + n;

	    //System.out.println(recordingTable.containsKey(key));

	    if (recordingTable.containsKey(key) == false) {
	      if (x.charAt(m) == y.charAt(n)) {
	        recordingTable.put(key, hashmapApproachLCSLength(m-1, n-1, x.substring(0, m), y.substring(0, n), recordingTable) + 1);
	      }

	      else recordingTable.put(key, Integer.max(hashmapApproachLCSLength(m-1, n, x.substring(0, m), y, recordingTable), hashmapApproachLCSLength(m, n-1, x, y.substring(0, n), recordingTable)));
	      //return Integer.max(recursiveLCSLengthFromBack(m-1, n, x.substring(0, m), y), recursiveLCSLengthFromBack(m, n-1, x, y.substring(0, n)));
	    }
	    return recordingTable.get(key);
	  }
	// by dynamic programming approach using 2D array for memoization
	public static int twoDimensionArrayApproachLCSLength (String x, String y, int [][] memoizationLength) {
		for (int i = 0; i <= x.length(); i ++) {
			for (int j = 0; j <= y.length(); j ++) {
				if (i == 0 || j == 0) {
					memoizationLength [i][j] = 0;
				}
				
				else if (i != 0 && j != 0) {
					if (x.charAt (i-1) == y.charAt(j-1)) {
						memoizationLength [i][j] += 1 + memoizationLength [i-1][j-1];
					}
					
					else {
						memoizationLength [i][j] = Integer.max (memoizationLength [i-1][j], memoizationLength [i][j-1]);
					}
				}
			}
		}
		
		return memoizationLength [x.length()][y.length()];
	}
	
	// by dynamic programming approach using 2 of single dimension array for memoization
	public static int twoArrayApproach (String x, String y) {
		
		String longer = "";
		String shorter = "";
		
		if (x.length() < y.length()) {
			shorter = x;
			longer = y;
		}
		
		else {
			shorter = y;
			longer = x;
		}
		
		int [] previous = new int [shorter.length()];
		int [] current = new int [shorter.length()];
		
		for (int i = 0; i < longer.length(); i ++) {
			boolean isFound = false;
			for (int j = 0; j < shorter.length(); j ++) {
				if (i == 0) {
					if (longer.charAt(i) == shorter.charAt(j)) {
						if (isFound == false) {
							current [j] += 1;
						}
						
						if (isFound == true) {
							int copy = current [j-1]; 
							current [j] = copy;
						}
						isFound = true;
					}
					
					else {
						if (j == 0) {
							current [j] = 0;
						}
						else current [j] = current [j-1];
					}
				}
				
				else {
					if (j == 0) {
						if (longer.charAt(i) == shorter.charAt(j)) {
							current [j] += 1;
						}
						else current [j] = previous [j];
					}
					else {
						if (longer.charAt(i) == shorter.charAt(j)) {
							current [j] = 1 + Integer.max(previous[j], current [j-1]);
						}
						else
						{
							current [j] = Integer.max(previous[j], current [j-1]);
						}
					}
				}
			}
			// for debugging
			if (DEBUG == true) {
				for (int w = 0; w < shorter.length(); w ++) {
					System.out.print (current[w] + "   ");
				}
				System.out.println();
			}
			
			for (int q = 0; q < shorter.length(); q ++) {
				previous [q] = current [q];
			}
		}
		
		return current [shorter.length()-1];
	}
	
	// by dynamic programming approach using one dimension array for memoization
	public static int oneArrayApproach (String x, String y) {
		String longer = "";
		String shorter = "";
		
		if (x.length() < y.length()) {
			shorter = x;
			longer = y;
		}
		
		else {
			shorter = y;
			longer = x;
		}
		 
		int [] memoization = new int [shorter.length()];
		
		for (int i = 0; i < longer.length(); i ++) {
			boolean isFound = false;
			for (int j = 0; j < shorter.length(); j ++) {
				if (i  == 0) {
					if (longer.charAt(i) == shorter.charAt(j)) {
						if (isFound == false) {
							memoization [j] += 1;
						}
						else if (isFound == true) {
							if (j == 0) {
								memoization [j] = 0;
							}
							else memoization [j] = memoization [j-1];
						}
					}
					else memoization [j] = 0;
				}
				
				else {
					if (j == 0) {
						if (longer.charAt(i) == shorter.charAt(j)) {
							memoization [j] = 1;
						}
					}
					else {
						if (longer.charAt(i) == shorter.charAt(j)) {
							memoization [j] = 1 + Integer.max (memoization [j], memoization [j-1]);
						}
						
						else {
							memoization [j] = Integer.max (memoization [j], memoization [j-1]);
						}
					}
				}
			}
			if (DEBUG == true) {
				for (int w = 0; w < shorter.length(); w ++) {
					System.out.print (memoization[w] + "   ");
				}
				System.out.println();
			}
		}
		return memoization [shorter.length()-1];
	}
	
	public static void main(String[] args) {
		//String X = "ABCBDAB", Y = "BDCABA";
		String X = "XMJYAUZ", Y = "MZJAWXU";
		
		// trying the Recursive approach from the front
	    System.out.println(recursiveLCSLengthFromFront(0, 0, X, Y));
	    
	    // trying the Recursive approach from the back
	    System.out.println(recursiveLCSLengthFromBack(X.length()-1, Y.length()-1, X, Y));

	    // trying the HashMap approach
	    HashMap <String, Integer> recording = new HashMap <String, Integer> (); 
	    System.out.println(hashmapApproachLCSLength(X.length()-1, Y.length()-1, X, Y, recording));
	    
	    // trying the 2D-array approach
	    int [][] memoizationLength = new int [X.length() + 1][Y.length() + 1];
	    System.out.println(twoDimensionArrayApproachLCSLength(X, Y, memoizationLength));
	    
	    if (DEBUG == true) {
	    	for (int i = 0; i <= X.length(); i ++) {
	    		for (int j = 0; j <= Y.length(); j ++) {
	    			System.out.print (memoizationLength[i][j]);
	    		}
	    		System.out.println();
	    	}
	    }
	    
	    // trying the 2 of single dimension array approach
	    System.out.println(twoArrayApproach(X, Y));
	    
	    System.out.println (oneArrayApproach(X, Y)); 
	}

}