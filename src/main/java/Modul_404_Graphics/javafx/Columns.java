package Modul_404_Graphics.javafx;

public class Columns {
    int id;
    String first;
    String last;
    String classes;
    Float graded;

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void setGrade(Float grade) {
        this.graded = grade;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getClasses() {
        return classes;
    }

    public Float getGraded() {
        return graded;
    }

    public Columns(String first, String last, String classes, Float grade) {
        this.last = last;
        this.first = first;
        this.classes = classes;
        this.graded = grade;
    }
}

