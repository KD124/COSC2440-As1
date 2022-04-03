package ManagementSystem;

import StudentEnrolment.StudentEnrolment;
import StudentEnrolment.Course;

import java.util.HashSet;

public interface StudentEnrolmentManager {
    boolean add(StudentEnrolment enrolment);
    boolean delete(StudentEnrolment enrolment);
    void update();
    StudentEnrolment getOne();
    HashSet<Course> getAll(String sem);
}
