// GradeBook class for P1-GradeBook
// Name: Jorre Dahl
// Section (A or B):  B

import java.util.*;
import java.io.*;

public class GradeBook {

    private ArrayList<Student> roster;
    
    // Default constructor
    public GradeBook() {
        roster = new ArrayList<Student>();
    }
    
    // Constructor fills roster with data from file
    public GradeBook(String fileName) {
        // your code goes here
        Scanner s = getFileScanner(fileName);
        roster = new ArrayList<Student>();

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] data = line.split(" ");
            Student p = new Student(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4]);
            roster.add(p);
            for (int i = 0; i < (data.length - 5) / 2; i++) {
                Grade g = new Grade (data[5 + (i * 2)], Integer.parseInt(data[5 + (i * 2 + 1)]));
                p.addGrade(g);
            }
        }
    }
    
    // String representation of GradeBook
    public String toString() {
        String str = "";
        for(int i = 0; i < roster.size(); i++) {
            str += roster.get(i) + " ";
            str += roster.get(i).getGrades().get(0);
            for(int j = 1; j < roster.get(i).getGrades().size(); j++) {
                str += ", " + roster.get(i).getGrades().get(j);
            }
            str += "\n";
        }
        return str;  // a way to get started
    }

    public void printIndividualGrades(String id) {
        for(int i = 0; i < roster.size(); i++) {
            if(id.equals(roster.get(i).getID())) {
                System.out.print(roster.get(i).getGrades().get(0));
                for(int j = 1; j < roster.get(i).getGrades().size(); j++) {
                    System.out.print(", " + roster.get(i).getGrades().get(j));
                }
                System.out.println();
            }
        }
    }
    
    public void printGradesByMajor(String major) {
        for(int i = 0; i < roster.size(); i++) {
            if(major.equals(roster.get(i).getMajor())) {
                System.out.print(roster.get(i).getGrades().get(0));
                for(int j = 1; j < roster.get(i).getGrades().size(); j++) {
                    System.out.print(", " + roster.get(i).getGrades().get(j));
                }
                System.out.println();
            }
        }
    }

    public void removeStudent(String id) {
        boolean is = false;
        for(int i = 0; i < roster.size(); i++) {
            if(id.equals(roster.get(i).getID())) {
                roster.remove(i);
                is = true;
            }
        }
        if (!is) {
            System.out.println("Error! Enter a valid student id");
        }
    }

    public void changeGrade(String id, String assignment, int newScore) {
        boolean isA = false;
        boolean isB = false;
        for(int i = 0; i < roster.size(); i++) {
            if(id.equals(roster.get(i).getID())) {
                isA = true;
                for(int j =0; j < roster.get(i).getGrades().size(); j++) {
                    if(assignment.equals(roster.get(i).getGrades().get(j).getName()))  {
                        isB = true;
                        roster.get(i).getGrades().get(j).changeGrade(newScore);
                    }
                }
            }
        }
        if(!isA) {
            System.out.println("Error! Enter a valid student id");
        }
        if(!isB) {
            System.out.println("Error! Enter a valid assignment name");
        }
    }

    // Helper methods to open files for reading/writing
    public static Scanner getFileScanner(String filename) {
        Scanner myFile;
        try { myFile = new Scanner(new FileReader(filename)); }
        catch (Exception e) {
            System.out.println("File not found: " + filename);
            return null;
        }
        return myFile;
    }
    
    public static PrintWriter getFileWriter(String filename) {
        PrintWriter outFile;
        try { outFile = new PrintWriter(filename); }
        catch (Exception e) {
            System.out.println("Error opening file: " + filename);
            return null;
        }
        return outFile;
    }

    // Testing the GradeBook class
    public static void main(String[] args) {
        GradeBook grades = new GradeBook("data/F22grades.txt");
        System.out.print(grades);

        grades.printIndividualGrades("pahmad");
        grades.printIndividualGrades("pmad");
        grades.printGradesByMajor("CSCI");

        grades.removeStudent("miikkaa");
        grades.removeStudent("miikkaa");
        grades.changeGrade("y", "x", 30);
        grades.changeGrade("pahmad","HW1",99);

        System.out.print(grades);
        // add method calls here to test your code
    }
    
}
