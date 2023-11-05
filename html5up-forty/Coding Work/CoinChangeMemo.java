//Problem 3a, Jorre Dahl
//In this problem I switched the data structures from int and int[]
//to an ArrayList and a Hashmap because its easier to add values to 
//both and its easier to search through a Hashmap

import java.util.*;

public class CoinChangeMemo {

	final static int INF = 10000000;
	
	//memoDict now becomes a Hashmap to map lists to values of n
	static HashMap<Integer,ArrayList<Integer>> memoDict;
	
	public static void main(String[] args)
	{
		System.out.println("Enter amount: ");
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();	
		
		//initialize the Dictionary
		memoDict = new HashMap<Integer,ArrayList<Integer>>();

		//count changed to an ArrayList coins
		ArrayList<Integer> coins = getChange(n);

		System.out.println("coins to make change for "+n+": "+coins);
	}
	
	//Find min of the three values
	public static int getMin(int n1, int n2, int n3)
	{
		int min = INF;
		
		if (n1 < min)
			min = n1;
		if (n2 < min)
			min = n2;
		if (n3 < min)
			min = n3;
		
		
		return min;
	}
	
	//Created new function getMinList in order to return a List when comparing their sizes
	public static ArrayList<Integer> getMinList(ArrayList<Integer> l1, ArrayList<Integer> l2, ArrayList<Integer> l3) {
		int list = getMin(l1.size(), l2.size(), l3.size());
		if(list == l1.size()) return l1;
		if(list == l2.size()) return l2;
		if(list == l2.size()) return l3;

		//unused return statement so method has no errors
		return new ArrayList<>();
	}
	
	//calls helper method
	public static ArrayList<Integer> getChange(int n)
	{
		return getMinList(getChangeDenom(n-12, 12), getChangeDenom(n-5, 5), getChangeDenom(n-1,1));
	}

	//Helper method with denom allows values to be added initially in the first recursive call
	private static ArrayList<Integer> getChangeDenom(int n, int denom) {

		//memoization step
		if(memoDict.containsKey(n)){
			return memoDict.get(n);
		}

		//initialize ArrayList
		ArrayList<Integer> coins = new ArrayList<Integer>();

		//returns an ArrayList of size INF instead of int INF
		if (n < 0) {
			coins = new ArrayList<Integer>(Arrays.asList(new Integer[INF]));
			return coins;
		}

		//Adds both the denomination and 5 to the list
		if(n == 5) {
			coins.addAll(Arrays.asList(5, denom));
			return coins;
		}

		//Adds both the denomination and 12 to the list
		if (n ==12) {
			coins.addAll(Arrays.asList(12, denom));
			return coins;
		}

		//Once n is below 5, there can only be 1s, so there is little purpose to continue recursively calling
		if (n < 5) {
			for(int i = 0; i<n; i++){
				coins.add(1);
			}
			coins.add(denom);
			memoDict.put(n,coins);
			return coins;
		}
		else { // This step recursively calls all 3 statements, adding the denomination afterward, and checking the dictionary to put before returning
			System.out.println("getChange("+n+")");
			coins.addAll(getMinList(getChangeDenom(n-12,12), getChangeDenom(n-5,5), getChangeDenom(n-1,1)));
			coins.add(denom);
			if(memoDict.containsKey(n)) {
				if(coins.size() < memoDict.get(n).size()) {
					memoDict.put(n,coins);
				}
			} else {
				memoDict.put(n,coins);
			}
			return coins;
		}
	}
	
}

