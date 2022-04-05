package ManagementSystem;

public class Main {
    public static void main(String[] arg){
       StudentEnrolmentSystem sys = new StudentEnrolmentSystem();
       sys.readData();
       sys.clearScreen();
       sys.homeScreen_UI();
    }
}
