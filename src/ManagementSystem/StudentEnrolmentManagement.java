package ManagementSystem;

import StudentEnrolment.StudentEnrolment;

public interface StudentEnrolmentManagement {
    void add();
    void delete();
    void update();
    StudentEnrolment getOne();

}
