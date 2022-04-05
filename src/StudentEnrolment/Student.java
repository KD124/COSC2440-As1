package StudentEnrolment;

import java.util.HashSet;
import java.util.Objects;

public class Student {
    private String id;
    private String name;
    private String birthday;
    private HashSet<Course> courses;        //keep track of enrolled courses to handle the duplicate courses

    public Student(){
        id = null;
        name = null;
        birthday = null;
        courses = new HashSet<>();
    }
    public Student(String id, String name, String birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        courses = new HashSet<>();
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getBirthday() {return birthday;}
    public HashSet<Course> getCourses() {return courses;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString(){
        return String.format("%-10s - %-35s %s",id , name, birthday);
    }
}
