package OOP;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		
    	ManagementSystem admin = new ManagementSystem("Admin01", "MS01");	//Create admin        
                        
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.print("Enter ID (Press X to exit): ");
            input = scanner.nextLine().toUpperCase(); // Convert input to uppercase

            if (input.equals("X")) { // Exit condition
            	System.out.println("Exiting...");
                System.exit(0); // Exit the program immediately
            }

            if (admin.getId().equals(input)) { // Check if the input matches admin ID
                ManagementSystem.MS(admin); // Call management system method
            } else {
                boolean found = false;
                for (Student student : admin.getStudents()) {
                    if (student.getId().equals(input)) { // Check if student ID matches
                        if (input.startsWith("CB")) {
                            student.DisplayMenu(admin.getCourses()); // Handle credit-based students
                        } else if (input.startsWith("PT")) {
                        	student.DisplayMenu(admin.getCourses()); // Handle part-time students
                        }
                        found = true; // Mark student as found
                        break; // Exit loop once student is found
                    }
                }

                if (!found) { // If no student matches the ID
                    System.out.println("Student not found. Please try again.");
                }
            }
        } while (true);	// Continue the loop until a valid input is processed or "X" is entered
	}
}
