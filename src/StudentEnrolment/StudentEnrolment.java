package StudentEnrolment;

public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment() {
        student = null;
        course = null;
        semester = null;
    }
    public StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Student getStudent() {return student;}
    public Course getCourse() {return course;}
    public String getSemester() {return semester;}

}


