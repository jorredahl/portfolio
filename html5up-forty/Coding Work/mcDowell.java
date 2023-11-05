//Problem 3b, Jorre Dahl

import java.util.*;

public class mcDowell {
    public static void main(String[] args) {

        System.out.println("Enter Arguments: ");
		Scanner scan = new Scanner(System.in);
		
		String s = scan.nextLine();

        scan.close();

        Integer[] arguments = extract(s);
        int n = arguments[0];
        int k = arguments[1];
        Integer[] m = distanceArray(Arrays.copyOfRange(arguments, 2, n + 2));
        Integer[] p = Arrays.copyOfRange(arguments, n + 2, 2*n + 2);

        int[][] C = fillArray(n,k,m,p);

        System.out.println();
        System.out.print(C[0][n] + " at");

        int i = n;
        while (i != 0) { // Follows j values back to find the path
            if (C[0][i] == C[0][i - 1]) {
                i--;
            } else {
                System.out.print(" " + i);
                i = C[1][i];
            }
        }
    }

    public static Integer[] extract(String s) { // Takes the entered arguments and turns it into an array of integers
        ArrayList<Integer> output = new ArrayList<Integer>();
        int j = 0;
        for(int i = 0; i < s.length(); i++){
            if (s.substring(i,i+1).equals(" ")){
                output.add(Integer.valueOf(s.substring(j,i)));
                j = i + 1;
            }
        }
        output.add(Integer.valueOf(s.substring(j,s.length())));
        return output.toArray(new Integer[output.get(0)]);
    }

    public static Integer[] distanceArray(Integer[] m) { // Adds a negative infinity value at m[0] so that j values
        Integer[] output = new Integer[m.length + 1]; // have a minimum value and do not do out of bounds error.
        output[0] = -999999999;
        for(int i = 1; i <= m.length; i ++) {
            output[i] = m[i - 1];
        }
        return output;
    }

    public static int[][] fillArray (int n, int k, Integer[] m, Integer[] p) { // Fills the array per the DP formulation
        int[][] C = new int[2][n+1]; // Uses a 2 by n + 1 array to track j values too
        C[0][0] = 0;
        C[1][0] = 0;
        C[0][1] = p[0];
        C[1][1] = 0;
        for(int i = 2; i <= n; i++) {
            int j = i - 1;
            while(m[i] - m[j] < k) {
                j--;
            }
            if (C[0][i - 1] > p[i-1] + C[0][j]) {
                C[0][i] = C[0][i - 1];
                C[1][i] = C[1][i - 1];
            } else {
                C[0][i] = p[i-1] + C[0][j];
                C[1][i] = j;
            }
        }
        return C;
    }
}