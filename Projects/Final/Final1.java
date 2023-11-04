// CS 201 Final exam programming question 1
// Name: Jorre Dahl

import java.util.*;

public class Final1 {

    // Define your method `repeatedCharacters` here 
    public boolean repeatedCharacters(String s) {
        HashSet<Character> list = new HashSet<Character>();
        for(int i = 0; i < s.length(); i++) {
            if(list.contains(s.charAt(i))) return true;
            list.add(s.charAt(i));
        }
        return false;
    }

    // Define your method `occurTwice` here
    public String occurTwice(String s) {
        HashSet<Character> list1 = new HashSet<Character>();
        HashSet<Character> list2 = new HashSet<Character>();
        HashSet<Character> list3 = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (list1.contains(s.charAt(i))) {
                if (!list2.contains(s.charAt(i))) {
                    list2.add(s.charAt(i));
                }
                else {
                    list3.add(s.charAt(i));
                }
            }
            list1.add(s.charAt(i));
        }
        for(char c : list3) {
            if (list2.contains(c)) {
                list2.remove(c);
            }
        }
        String output = "";
        for(char c : list2) {
            output += c;
        }
        return output;
    }

    public static void main(String[] args) {
        Final1 obj = new Final1();
        String str1 = "march";
        String str2 = "extreme";
        String str3 = "ABRACADABRA";
        System.out.printf("repeatedCharacters(%s) = ", str1);
        System.out.print(obj.repeatedCharacters(str1));
        System.out.println(); 
        System.out.printf("repeatedCharacters(%s) = ", str2);
        System.out.print(obj.repeatedCharacters(str2)); 
        System.out.println();
        System.out.printf("occurTwice(%s) = ", str3);
        System.out.print(obj.occurTwice(str3)); 
        System.out.println();
    }
}