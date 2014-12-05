package projecteuler.prob26;


import java.util.ArrayList;

public class ReciprocalRecurringCycles {

	/**
	 * Author: James Norcross
	 * Date: 12/1/14
	 * Purpose: Project Euler Problem 26 solution
	 * Description: finds the integer d <1000 for which 1/d has the longest recurring cycle
	 */
	
	final static int DMAX = 1000;
	
	public static void main(String[] args) {
		
		int d;
		int reptendLengthMax = 0; 
		int dMax = 1;
		
		for (d=1; d<= DMAX; d++) {
			Reciprocal r = new Reciprocal(d);
			if (r.getReptendLength() > reptendLengthMax) {
				reptendLengthMax = r.getReptendLength();
				dMax = d;
			}
//			System.out.println(r);
		}
		
		System.out.println(dMax + "   " + reptendLengthMax);
	}
	
}
