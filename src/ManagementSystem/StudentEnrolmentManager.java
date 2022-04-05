package ManagementSystem;

import StudentEnrolment.*;

import java.util.HashSet;

public interface StudentEnrolmentManager {
    boolean add(StudentEnrolment enrolment);
    boolean delete(StudentEnrolment enrolment);
    void update();
    StudentEnrolment getOne(Student stu, Course course, String sem);
    HashSet<Course> getAll(String sem);
    HashSet<Course> getAll(Student stu, String sem);
    HashSet<Student> getAll(Course course, String sem);
}
