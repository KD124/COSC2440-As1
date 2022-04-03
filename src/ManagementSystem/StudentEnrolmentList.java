package ManagementSystem;


import StudentEnrolment.StudentEnrolment;
import StudentEnrolment.Student;
import StudentEnrolment.Course;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class StudentEnrolmentList implements StudentEnrolmentManager {
    ArrayList<StudentEnrolment> list;
    HashSet<String> availableSems;      //this attribute is to keep track of available semesters from the enrolment list read from csv file
    HashSet<Course> availableCourses;   //this attribute is to keep track of available courses from the enrolment list read from csv file

    public StudentEnrolmentList() {
        list = new ArrayList<>();
        availableSems = new HashSet<>();
        availableCourses = new HashSet<>();
    }


    @Override
    public boolean add(StudentEnrolment enrolment) {
        return list.add(enrolment);
    }

    @Override
    public boolean delete(StudentEnrolment enrolment) {
        return list.remove(enrolment);
    }

    @Override
    public void update() {

    }

    @Override
    public StudentEnrolment getOne() {
        return null;
    }

    @Override
    public HashSet<Course> getAll(String sem) {       //get all courses available in 1 semester
        HashSet<Course> courses = new HashSet<Course>();
        for (StudentEnrolment i : list){
            if(i.getSemester().equals(sem)) courses.add(i.getCourse());
        }
        return  courses;
    }

    //From this is helper and UI methods
    public void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void pauseScreen(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPress Enter key to continue...");
        scanner.nextLine();
    }

    public boolean validateInput(String input){
        if(input.length() != 1) return false;
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public Student searchStudentById(String id){
        for(StudentEnrolment i : list){
            if (i.getStudent().getId().equals(id)) return i.getStudent();
        }
        return null;
    }


}
