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

    public void homeScreen_UI(){
        Scanner scanner = new Scanner(System.in);
        String state;
        System.out.println("***** HOMESCREEN ******");
        System.out.println("1. Enroll a student for 1 semester");
        System.out.println("2. Update an enrolment of a student for 1 semester");
        System.out.println("3. Print all courses for 1 student in 1 semester");
        System.out.println("4. Print all students of 1 course in 1 semester");
        System.out.println("5. Prints all courses offered in 1 semester");
        System.out.println("0. Exit the program\n");
        System.out.print("Please choose an option: ");
        state = scanner.nextLine();
        if(!validateInput(state)){
            System.out.println("\nInvalid choice. Please select an available option!!!");
            pauseScreen();
            clearScreen();
            homeScreen_UI();
            return;
        }else{
            homeScreen_Operation(Integer.parseInt(state));
        }
    }

    public void homeScreen_Operation(int state){
        switch (state){
            default:
                System.out.println("\nInvalid choice. Please select an available option!!!");
                pauseScreen();
                clearScreen();
                homeScreen_UI();
                return;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 0:
                System.exit(0);
                break;
        }
    }


}
