package ManagementSystem;

public class Main {
    public static void main(String[] arg){
       StudentEnrolmentList sys = new StudentEnrolmentList();
       sys.readData();
       sys.clearScreen();
       sys.homeScreen_UI();
    }
}
