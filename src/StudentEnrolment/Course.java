package StudentEnrolment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Course {
    private String id;
    private String name;
    private String creditNum;
    private HashSet<Student> students;

    public Course(){
        id = null;
        name = null;
        creditNum = null;
        students = new HashSet<>();
    }

    public Course(String id, String name, String creditNum) {
        this.id = id;
        this.name = name;
        this.creditNum = creditNum;
        students = new HashSet<>();
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getCreditNum() {return creditNum;}
    public HashSet<Student> getStudents() {return students;}

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
}
