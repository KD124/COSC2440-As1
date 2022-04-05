package StudentEnrolment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Course {
    private String id;
    private String name;
    private String creditNum;

    public Course(){
        id = null;
        name = null;
        creditNum = null;
    }

    public Course(String id, String name, String creditNum) {
        this.id = id;
        this.name = name;
        this.creditNum = creditNum;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getCreditNum() {return creditNum;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString(){
        return String.format("%-10s - %-35s (%s)",id , name, creditNum);
    }
}
