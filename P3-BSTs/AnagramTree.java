// AnagramTree class for Project 3 BSTs and Anagrams
// Name: Jorre

import java.util.*;
import java.io.*;

public class AnagramTree {
    private BST<String> tree;


    public AnagramTree(String filename, int maxLength) {
        tree = new BST<String>();
        Scanner s = getFileScanner(filename);
        while (s.hasNext()) {
            String word = s.next();
            if (word.length() <= maxLength && !word.equals("")) {
                char[] arr = word.toCharArray();
                Arrays.sort(arr);
                String cannon = new String(arr);
                tree.add(cannon, word);
            }
        }
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public ArrayList<String> findMatches(String searchKey) {
        char[] arr = searchKey.toCharArray();
        Arrays.sort(arr);
        String cannon = new String(arr);
        return tree.find(cannon);
    }
    
    public static Scanner getFileScanner(String filename) {
        Scanner myFile;
        try { myFile = new Scanner(new FileReader(filename)); }
        catch (Exception e) {
            System.out.println("File not found: " + filename);
            return null;
        }
        return myFile;
    }

}