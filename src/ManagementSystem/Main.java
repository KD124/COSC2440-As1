package ManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] arg){
       StudentEnrolmentSystem sys = new StudentEnrolmentSystem();

       String fileName = sys.askFileName();
       sys.readData(fileName);
       sys.pauseScreen();
       sys.clearScreen();
       sys.homeScreen_UI();
    }
}
