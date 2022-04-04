package ManagementSystem;


import StudentEnrolment.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class StudentEnrolmentList implements StudentEnrolmentManager {
    private ArrayList<StudentEnrolment> list;
    private TreeSet<String> availableSems;      //this attribute is to keep track of available semesters from the enrolment list read from csv file
    private HashSet<Course> availableCourses;   //this attribute is to keep track of available courses from the enrolment list read from csv file

    public StudentEnrolmentList() {
        list = new ArrayList<>();
        availableSems = new TreeSet<>();
        availableCourses = new HashSet<>();
    }

    public ArrayList<StudentEnrolment> getList(){return list;}

    public TreeSet<String> getAvailableSems() {
        return availableSems;
    }

    public HashSet<Course> getAvailableCourses() {
        return availableCourses;
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
    public HashSet<Course> getAll(String sem) {       //get all courses offered in 1 semester
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

    public void readData(){
        String fileName = "default.csv";
        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String line;
            StudentEnrolment enrolment;
            Student stu;
            Course course;
            while ((line = buf.readLine()) != null){
                String[] data = line.split(",");
                stu = new Student(data[0],data[1],data[2]);
                course = new Course(data[3],data[4],data[5]);
                //check if the student and course already exist or not
                //if yes, point stu and course to the one that already exists
                for(StudentEnrolment en : list){
                    if(en.getStudent().equals(stu)){
                        stu = en.getStudent();
                        break;
                    }
                }
                for (StudentEnrolment en:list){
                    if(en.getCourse().equals(course)){
                        course = en.getCourse();
                        break;
                    }
                }
                //update the course list for stu and student list for course
                stu.getCourses().add(course);
                course.getStudents().add(stu);
                //add new enrolment to the enrolment list
                enrolment = new StudentEnrolment(stu,course,data[6]);
                list.add(enrolment);
            }
            buf.close();
            //Set available courses and sems
            for(StudentEnrolment i : list){
                availableCourses.add(i.getCourse());
                availableSems.add(i.getSemester());
            }
            //print out to test the read file method
/*            for(int i =0;i<list.getList().size();i++){
                System.out.print(list.getList().get(i).getStudent().getId() + "\t" + list.getList().get(i).getStudent().getName() + "\t");
                System.out.print(list.getList().get(i).getStudent().getBirthday() + "\t" + list.getList().get(i).getCourse().getId() + "\t");
                System.out.print(list.getList().get(i).getCourse().getName() + "\t" + list.getList().get(i).getCourse().getCreditNum() + "\t");
                System.out.println(list.getList().get(i).getSemester());
            }
            System.out.println("\n");
            System.out.print("Available Sems:\t");
            for(String i : list.getAvailableSems()){
                System.out.print(i + "\t");
            }
            System.out.println();
            System.out.println("Available Courses: ");
            for(Course i : list.getAvailableCourses()){
                System.out.println("\t" + i.getName());
            }*/

        } catch (IOException e) {
            System.out.println("File not found!!!");
            pauseScreen();
            System.exit(0);
        }
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
            if (i.getStudent().getId().equals(id.toUpperCase())) return i.getStudent();
        }
        return null;
    }

    public void homeScreen_UI(){
        Scanner scanner = new Scanner(System.in);
        String state;
        System.out.println("***** HOME SCREEN ******");
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
                clearScreen();
                enroll_UI();
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
                clearScreen();
                System.exit(0);
                break;
        }
    }

    public void enroll_UI(){
        Scanner scanner = new Scanner(System.in);
        String[] availableSems = new String[this.availableSems.size()];
        this.availableSems.toArray(availableSems);
        Course[] availableCourses = new Course[this.availableCourses.size()];
        this.availableCourses.toArray(availableCourses);
        String input;
        int state;

        System.out.println("***** ENROLL A STUDENT FOR 1 SEMESTER ******");
        //Student id:
        Student stu;
        System.out.print("Enter student's id: ");
        input = scanner.nextLine();
        stu = searchStudentById(input);
        if(stu == null){
            System.out.println("\nStudent's id not found!");
            pauseScreen();
            clearScreen();
            homeScreen_UI();
        }else {
            System.out.println("\nStudent's id found!");
            System.out.println("Id: " + stu.getId() + "\tName: " + stu.getName() + "\n");
            //Semester:
            String sem;
            System.out.println("Choose a semester to enroll:");
            for (int i = 0; i < availableSems.length; i++){
                System.out.println("\t" + (i+1) + ". " + availableSems[i]);
            }
            System.out.println("\t0. Back to home screen");
            System.out.print("Please choose an option: ");
            input = scanner.nextLine();
            while(!validateInput(input)){
                System.out.print("Invalid choice. Please enter an available option: ");
                input = scanner.nextLine();
            }
            state = Integer.parseInt(input);
            while(state > availableSems.length){
                System.out.print("Invalid choice. Please enter an available option: ");
                input = scanner.nextLine();
                if(validateInput(input)) {
                    state = Integer.parseInt(input);
                }
            }
            switch (state){
                case 0:
                    homeScreen_UI();
                    return;
                default:
                    sem = availableSems[state-1];
                    break;
            }
            //Course
            Course course;
            System.out.println("\nAvailable courses:");
            for (int i = 0; i < availableCourses.length; i++){
                System.out.println("\t" + (i+1) + ". " + availableCourses[i].getName());
            }
            System.out.println("\t0. Back to home screen");
            System.out.print("Please choose a course to enroll: ");
            input = scanner.nextLine();
            while(!validateInput(input)){
                System.out.print("Invalid choice. Please enter an available option: ");
                input = scanner.nextLine();
            }
            state = Integer.parseInt(input);
            while(state > availableCourses.length){
                System.out.print("Invalid choice. Please enter an available option: ");
                input = scanner.nextLine();
                if(validateInput(input)) {
                    state = Integer.parseInt(input);
                }
            }
            switch (state){
                case 0:
                    homeScreen_UI();
                    return;
                default:
                    course = availableCourses[state-1];
                    break;
            }
            //Update all changes to the enrolment list
            add(new StudentEnrolment(stu,course,sem));
            System.out.println("\nSuccessfully add new enrolment!!!");
            pauseScreen();
            clearScreen();
            homeScreen_UI();
        }
    }
}
