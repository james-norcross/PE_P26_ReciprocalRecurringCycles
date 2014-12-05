package projecteuler.prob26;
import java.util.ArrayList;


public class Reciprocal {
	
	// Contains information to describe the reciprocal of integer d, 1/d
	
	private int d;
	private ArrayList<Integer> quotientList;	// list contains distinct elements in reciprocal
	private int reptendStart;		// the position in the quotientList of the first element of the reptend
	private int reptendLength;		// the length of the reptend
	
	public Reciprocal(int d) {
		this.d = d;
		quotientList = new ArrayList<Integer>();
		findQuotient();
	}
	
	public ArrayList<Integer> getQuotient() {
		return quotientList;
	}
	
	public int getReptendStart() {
		return reptendStart;
	}
	
	public int getReptendLength() {
		return reptendLength;
	}
	
	private void findQuotient() {
		
		/* 
		 * Consider the long division problem 1 divided by d.  The possible remainders at any step in the problem include 0, 1, ..., d-1.
		 * If the remainder is 0 then the decimal is terminated.  If at any point the remainder has the same value as it did earlier in 
		 * the problem then the decimal will be repeating.  The period of repetition is that between the two points when the remainder was 
		 * the same.
		 * 
		 * For each value of d
		 * 1. Initially the dividend 'r' is 1, and the position in the quotient 'position' is 0.  Array 'remainder' is
		 * 	  an array whose index represents the various values that the remainder can have.  So for instance, if d=3, then remainder array
		 *    has 4 elements 0,...,3.  At each point in the problem, the element at the index representing the current remainder is updated to
		 *    hold the value of the position in the quotient 'position'.  'quotientList' is an array list that holds the individual digits in the quotient
		 *    with the index representing the digit at 10exp(-index).
		 * 2. Initialize remainder array to -1
		 * 3. put r/d into the quotient array
		 * 4. determine the new remainder r (r mod d)
		 * 		a. If r is 0 the decimal is terminated
		 * 		b. If r is not 0
		 * 			i. check the value in the remainder array at position r.  If it is zero then the decimal is not repeating yet
		 * 			   Put the value of position in the remainder array at index equal to r
		 * 			ii. if the value in remainder array at index r is non zero, then the decimal is repeating with the repeating
		 * 			   digits being those between the current position and that in the remainder array
		 * 5. Continue until the decimal is terminated or until it repeats, can determine delta (the length of the repetend) and 
		 *    construct the quotient from the quotient array
		 */
		
		int r, position, j;
		int[] remainder = new int[d];		// possible remainders 0, ..., d-1
		boolean done;
		
		r=1;
		position = 0;
		reptendLength = 0;
		reptendStart = 0;
		done = false;
		
		for (j = 0; j < remainder.length; j++) {
			remainder[j] = -1;
		}
		
		while (!done) {
			// update quotient and remainder
			quotientList.add(r/d);
			r = r%d;
			
			// if remainder is zero decimal terminates
			if(r == 0) {
				done = true;
			}
			else {
				// if already had this remainder then decimal repeats done
				if (remainder[r] != -1) {
					done = true;
					reptendStart = remainder[r]+1;
					reptendLength = position-remainder[r];
				}
				// if not, update remainder array, add zero to remainder to create new dividend and move to next position in quotient
				else {
					remainder[r] = position;
					r=r*10;
					position++;
				}
			}
		}
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		for (int i : quotientList)
			sb.append(i);
		if (reptendLength > 0) {
			sb.insert(reptendStart, '('); 	
			sb.append(')');
		}
		sb.insert(1, '.');
		
		return sb.toString();
	}

}
