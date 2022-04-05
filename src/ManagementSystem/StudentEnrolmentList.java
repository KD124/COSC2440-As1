package ManagementSystem;


import StudentEnrolment.*;


import javax.sound.midi.Soundbank;
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
    public StudentEnrolment getOne(Student stu, Course course, String sem) {
        for (StudentEnrolment en : list) {
            if (stu.equals(en.getStudent()) && course.equals(en.getCourse()) && sem.equals(en.getSemester())) {
                return en;
            }
        }
        return null;
    }

    @Override
    public HashSet<Course> getAll(String sem) {       //get all courses offered in 1 semester
        HashSet<Course> courses = new HashSet<>();
        for (StudentEnrolment i : list){
            if(i.getSemester().equals(sem)) courses.add(i.getCourse());
        }
        return  courses;
    }

    @Override
    public HashSet<Course> getAll(Student stu, String sem) {    //get all courses for 1 student in 1 semester
        HashSet<Course> courses = new HashSet<>();
        for (StudentEnrolment i : list){
            if(i.getStudent().equals(stu) && i.getSemester().equals(sem)){
                courses.add(i.getCourse());
            }
        }
        return courses;
    }

    @Override
    public HashSet<Student> getAll(Course course, String sem) {     //get all students for 1 course in 1 semester
        HashSet<Student> stu = new HashSet<>();
        for (StudentEnrolment i : list){
            if(i.getCourse().equals(course) && i.getSemester().equals(sem)){
                stu.add(i.getStudent());
            }
        }
        return stu;
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
        } catch (IOException e) {
            System.out.println("File not found!!!");
            pauseScreen();
            System.exit(0);
        }
    }

    public boolean isDigit(String input){
        if(input.length() != 1) return false;
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public int validateInput(String input, int availableOpt){
        Scanner scanner = new Scanner(System.in);
        while(!isDigit(input)){ //check if input is from 0 to 9
            System.out.print("Invalid choice. Please enter an available option: ");
            input = scanner.nextLine();
        }
        int state = Integer.parseInt(input);
        while(state > availableOpt){ //check whether input larger than available options
            System.out.print("Invalid choice. Please enter an available option: ");
            input = scanner.nextLine();
            if(isDigit(input)) {
                state = Integer.parseInt(input);
            }
        }
        return state;
    }

    public Student searchStudentById(String id){
        for(StudentEnrolment i : list){
            if (i.getStudent().getId().equals(id.toUpperCase())) return i.getStudent();
        }
        return null;
    }

    public Course searchCourseById(String id){
        for(StudentEnrolment i : list){
            if (i.getCourse().getId().equals(id.toUpperCase())) return i.getCourse();
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
        if(!isDigit(state)){
            System.out.println("\nInvalid choice. Please select an available option!!!");
            pauseScreen();
            clearScreen();
            homeScreen_UI();
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
                clearScreen();
                updateEnrol_UI();
                break;
            case 3:
                clearScreen();
                printCourses1Stu_UI();
                break;
            case 4:
                clearScreen();
                printStus1Course_UI();
                break;
            case 5:
                clearScreen();
                printCourses1Sem_UI();
                break;
            case 0:
                clearScreen();
                System.exit(0);
                break;
        }
    }

    public String chooseSem_UI(){
        Scanner scanner = new Scanner(System.in);
        String[] availableSems = new String[this.availableSems.size()];
        this.availableSems.toArray(availableSems);
        String input;
        int state;

        System.out.println("Choose a semester:");
        for (int i = 0; i < availableSems.length; i++){
            System.out.println("\t" + (i+1) + ". " + availableSems[i]);
        }
        System.out.println("\t0. Back to home screen");
        System.out.print("Please choose an option: ");
        input = scanner.nextLine();
        state = validateInput(input, availableSems.length);
        switch (state){
            case 0:
                return "-1";
            default:
                return availableSems[state-1];
        }
    }

    public Course chooseCourse_UI(String purpose, Student stu){
        Scanner scanner = new Scanner(System.in);
        Course[] availableCourses = new Course[this.availableCourses.size()];
        this.availableCourses.toArray(availableCourses);
        String input;
        int state;

        System.out.println("\nAvailable courses:");
        for (int i = 0; i < availableCourses.length; i++){
            System.out.print("\t" + (i+1) + ". " + availableCourses[i]);
            for (Course course : stu.getCourses()){
                if (availableCourses[i].equals(course)){
                    System.out.print("\t[already enrolled]");
                    break;
                }
            }
            System.out.println();
        }
        System.out.println("\t0. Back to home screen");
        System.out.print("Please choose a course to " + purpose + ": ");
        input = scanner.nextLine();
        state = validateInput(input, availableCourses.length);
        switch (state){
            case 0:
                return null;
            default:
                return availableCourses[state-1];
        }
    }

    public void enroll_UI(){
        Scanner scanner = new Scanner(System.in);
        String input;

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
            String sem = chooseSem_UI();
            if(sem.equals("-1")){
                clearScreen();
                homeScreen_UI();
                return;
            }
                //Course:
            Course course = chooseCourse_UI("enroll", stu);
            if(course == null){
                clearScreen();
                homeScreen_UI();
                return;
            }

            //add the course to the courseList of the student and check whether student already enrolled this course
            if(!stu.getCourses().add(course)){
                System.out.println("\nStudent already enrolled on this course!!!");
                pauseScreen();
                clearScreen();
                homeScreen_UI();
                return;
            }
            //add student to the studentList of the course
            course.getStudents().add(stu);
            //add new enrolment to the list
            add(new StudentEnrolment(stu,course,sem));
            System.out.println("\nSuccessfully add new enrolment!!!");
            pauseScreen();
            clearScreen();
            homeScreen_UI();
        }
    }

    public void updateEnrol_UI(){
        Scanner scanner = new Scanner(System.in);
        String input;
        int state;

        System.out.println("***** UPDATE AN ENROLMENT ******");
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
            String sem = chooseSem_UI();
            if(sem.equals("-1")){
                clearScreen();
                homeScreen_UI();
                return;
            }
            //Update courses
            Course course;
            System.out.println("\nWhat you want to do with this student's enrolment in " + sem + "?");
            System.out.println("\t1. Add a new course");
            System.out.println("\t2. Delete an existing course");
            System.out.println("\t0. Back to home screen");
            System.out.print("Please choose an option: ");
            input = scanner.nextLine();
            state = validateInput(input, 2);
            switch (state) {
                case 0:
                    clearScreen();
                    homeScreen_UI();
                    return;
                case 1:     //add new course
                    System.out.print("\n***** ADD NEW COURSE *****");
                    course = chooseCourse_UI("add",stu);
                    if(course == null){
                        clearScreen();
                        homeScreen_UI();
                        return;
                    }
                    if(!stu.getCourses().add(course)){
                        System.out.println("\nStudent already enrolled on this course!!!");
                        pauseScreen();
                        clearScreen();
                        homeScreen_UI();
                        return;
                    }
                    course.getStudents().add(stu);
                    add(new StudentEnrolment(stu,course,sem));
                    System.out.println("\nSuccessfully add new course!!!");
                    System.out.println("New enrolment has been added as well!!!");
                    pauseScreen();
                    clearScreen();
                    homeScreen_UI();
                    return;
                case 2:     //delete an existing course
                    //find the courses that a student enrolls in specific semester
                    ArrayList<Course> existCourse = new ArrayList<>();
                    for(StudentEnrolment en : list){
                        if (sem.equals(en.getSemester()) && stu.equals(en.getStudent())){
                            existCourse.add(en.getCourse());
                        }
                    }
                    //UI
                    System.out.println("\n***** DELETE COURSE *****");
                    System.out.println("Existing courses in " + sem + ":");
                    for (int i =0; i < existCourse.size();i++){
                        System.out.println("\t" + (i+1) + ". " + existCourse.get(i).toString());
                    }
                    if(existCourse.size() ==0){
                        System.out.println("There is no enrolled course of this student in " + sem + "!!!");
                        pauseScreen();
                        clearScreen();
                        homeScreen_UI();
                        return;
                    }
                    System.out.println("\t0. Back to home screen");
                    System.out.print("Choose a course to delete: ");
                    input = scanner.nextLine();
                    state = validateInput(input, existCourse.size());
                    switch (state){
                        case 0:
                            clearScreen();
                            homeScreen_UI();
                            return;
                        default:
                            course = existCourse.get(state-1);
                    }
                    //remove student from course and course from student
                    if(stu.getCourses().remove(course) && course.getStudents().remove(stu)) {
                        //remove the student's enrolment on this course
                        delete(getOne(stu,course,sem));
                        System.out.println("Successfully delete course as well as enrolment!!!");
                        pauseScreen();
                        clearScreen();
                        homeScreen_UI();
                        return;
                    }
                    System.out.println("\nCannot delete the course. Some errors happen!!!");
                    pauseScreen();
                    clearScreen();
                    homeScreen_UI();
            }
        }
    }

    public void printCourses1Stu_UI(){
        Scanner scanner = new Scanner(System.in);
        HashSet<Course> courses;
        String input;

        System.out.println("***** PRINT ALL COURSES FOR 1 STUDENT IN 1 SEMESTER *****");
            //Ask for student's id
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
            //Choose sem
            String sem = chooseSem_UI();
            if (sem.equals("-1")) {
                clearScreen();
                homeScreen_UI();
                return;
            }
            //Print all courses
            courses = getAll(stu, sem);
            if(courses.size() < 1){
                System.out.println("\nThere is no courses for" + stu.getName().toUpperCase() + " in " + sem + "!!!");
                pauseScreen();
                clearScreen();
                homeScreen_UI();
            }
            System.out.println("\n\n***** All courses for " + stu.getName().toUpperCase() + " in " + sem + ":");
            int i = 1;
            for (Course c: courses){
                System.out.println("\t" + i + ". " + c.toString());
                i++;
            }
            pauseScreen();
            clearScreen();
            homeScreen_UI();
        }
    }

    public void printStus1Course_UI(){
        Scanner scanner = new Scanner(System.in);
        HashSet<Student> stu;
        String input;

        System.out.println("***** PRINT ALL STUDENTS FOR 1 COURSE IN 1 SEMESTER *****");
        //Ask for course's id
        Course course;
        System.out.print("Enter course's id: ");
        input = scanner.nextLine();
        course = searchCourseById(input);
        if(course == null){
            System.out.println("\nCourse's id not found!");
            pauseScreen();
            clearScreen();
            homeScreen_UI();
        }else {
            System.out.println("\nCourse's id found!");
            System.out.println(course + "\n");
            //Choose sem
            String sem = chooseSem_UI();
            if (sem.equals("-1")) {
                clearScreen();
                homeScreen_UI();
                return;
            }
            //Print all students
            stu = getAll(course, sem);
            if(stu.size() < 1){
                System.out.println("\nThere is no students for " + course.getName().toUpperCase() + " in " + sem + "!!!");
                pauseScreen();
                clearScreen();
                homeScreen_UI();
            }
            System.out.println("\n\n***** All students for " + course.getName().toUpperCase() + " in " + sem + ":");

            int i = 1;
            for (Student s: stu){
                System.out.println("\t" + i + ". " + s.toString());
                i++;
            }
            pauseScreen();
            clearScreen();
            homeScreen_UI();
        }
    }

    public void printCourses1Sem_UI() {
        HashSet<Course> courses;

        System.out.println("***** PRINT ALL COURSES OFFERED IN 1 SEMESTER *****");
        //Choose sem
        String sem = chooseSem_UI();
        if (sem.equals("-1")) {
            clearScreen();
            homeScreen_UI();
            return;
        }
        //Print all courses
        courses = getAll(sem);
        if(courses.size() < 1){
            System.out.println("\nThere is no courses offered in " + sem + "!!!");
            pauseScreen();
            clearScreen();
            homeScreen_UI();
        }
        System.out.println("\n\n***** All Courses in " + sem + ":");
        int i = 1;
        for (Course c : courses) {
            System.out.println("\t" + i + ". " + c);
        }
        pauseScreen();
        clearScreen();
        homeScreen_UI();
    }
}
