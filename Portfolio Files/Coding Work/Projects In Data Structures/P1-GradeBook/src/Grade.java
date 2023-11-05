// Jorre Dahl
// Section B


public class Grade {
    private String name;
    private int score;

    public Grade(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getGrade() {
        return this.score;
    }

    public String toString() {
        return name + " " + score;
    }

    public void changeGrade(int newScore) {
        score = newScore;
    }
}
