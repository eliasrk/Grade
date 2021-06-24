package Modul_404_Graphics.javafx;

public class Columns {
        int id;
        String first;
        String last;
        String classes;
        String grade;

    public Columns(String first, String last, String classes, String grade) {
        this.id = id;
        this.last = last;
        this.first = first;
        this.classes = classes;
        this.grade = grade;
    }

    public void setId(int id) {
            this.id = id;
        }
        public void setFirst(String first) {
            this.first = first;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public void setClass(String classes) {
            this.classes = classes;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getId() {
            return id;
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
        public String getGrade() {
            return grade;
        }


    }

