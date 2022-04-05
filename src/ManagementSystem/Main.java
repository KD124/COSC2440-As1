package ManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] arg){
       StudentEnrolmentSystem sys = new StudentEnrolmentSystem();
       Scanner scanner = new Scanner(System.in);
       String filename;
       sys.clearScreen();
       System.out.print("Enter a file name to populate data (this file should be included in project file): ");
       filename = scanner.nextLine();
       sys.readData(filename);
       sys.pauseScreen();
       sys.clearScreen();
       sys.homeScreen_UI();
    }
}
