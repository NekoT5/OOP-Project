package OOP;
import java.util.*;

public class UserType {
	public static void delay() {
	    try {
	        Thread.sleep(750); // Delay for 0.75s
	    } catch (InterruptedException e) {
	        System.out.println("An interruption occurred during the delay.");
	    }
	}
	
	public static void CBS(Student student, List<Course> courses) {
    	Scanner scanner = new Scanner(System.in);	// Choice for CBS
        int choice;
        
    	do {
            student.displayInfo(); // profile
            
            System.out.println("Choose 1 - Register Course ");
            System.out.println("Choose 2 - View Course");
            System.out.println("Choose 3 - Check Graduation");
            System.out.println("Choose 4 - Exit ");

            System.out.print("Your choice: ");	// Get choice
            choice = scanner.nextInt();           
            delay();
            
            // Choice
            switch (choice) {
                case 1:
                	int subChoice = 0; // Create subChoice
                    do {
                        System.out.println("Enter CourseID (Press 2 to go back): ");
                        String input = scanner.next().toUpperCase();                        
                        delay();
                        
                        // Check if student wants to back
                        if (input.equals("2")) {
                            subChoice = 2;	// Value to exit loop
                            System.out.println("Returning to the main menu...");
                            
                        } else {	// Find course by ID
                            Course selectedCourse = null;
                            for (Course course : courses) {
                                if (course.getId().equals(input)) {
                                    selectedCourse = course;
                                    break;
                                }
                            }

                            if (selectedCourse != null) {                               
                                student.registerCourse(selectedCourse);	// registerCourse(Polymorphism)
                            } else {
                                System.out.println("Cannot find course.");
                            }
                        }
                    } while (subChoice != 2); // Exit
                    break;

                case 2:
                	int viewChoice = 0;
                	do {	// View enrolled courses
                		System.out.println("Courses Enrolled:");
                		if (student.getCoursesEnrolled().isEmpty()) {
                            System.out.println("You have not enrolled in any courses.");
                		} else {	                        
	                        for (Enrollment enrollment : student.getCoursesEnrolled()) {
	                            enrollment.displayEnrollmentInfo();                               
	                        }
                		}  
               		
                        System.out.println("Press 2 to exit!");
                        viewChoice = scanner.nextInt();
                        
                        if (viewChoice == 2) {
                            System.out.println("Returning to the main menu...");
                        }
                    } while (viewChoice != 2);
                    break;

                case 3:
                    // Kiểm tra tốt nghiệp (cần thêm logic nếu cần)
                    student.validateGraduation();
                    break;

                case 4:
                    return; // Thoát vòng lặp do chương trình kết thúc

            }

            System.out.println(); // Thêm dòng trống để dễ nhìn
        } while (choice != 4); // Lặp lại cho đến khi người dùng chọn thoát
    }
    
    public static void PTS(Student student, List<Course> courses) {
    	Scanner scanner = new Scanner(System.in);
        int choice;
        
    	do {
            student.displayInfo(); // profile

            System.out.println("Choose 1 - View Course");	// Choice for PTS
            System.out.println("Choose 2 - Check Graduation");
            System.out.println("Choose 3 - Exit ");

            System.out.print("Your choice: "); // Get choice
            choice = scanner.nextInt();           
            delay();
            
            switch (choice) {
                case 1:
                	int subChoice = 0; // Create subChoice
                    do {
                        System.out.println("A list of course has been arranged for you!");                      
                        delay();
                        	
                        for (Course selectedCourse: courses) {
                        	student.registerCourse(selectedCourse);
                        	selectedCourse.viewCourseDetail();
                        }
                                                
                        System.out.println("Press 2 to go back: ");
                        subChoice = scanner.nextInt();
                                               
                        if (subChoice == 2) {
                            System.out.println("Returning to the main menu...");
                            delay();
                            }
                        
	                    } while (subChoice != 2); // Thoát khi người dùng chọn 2
	                    break;
                    
                case 2:
                	student.validateGraduation(); //	Check graduation                       
                    break;

                case 3:
                    return; // Thoát vòng lặp do chương trình kết thúc

            }

            System.out.println(); // Add a blank line for better look
        } while (choice != 3); // Repeat until exit

    }
   
    public static void MS(ManagementSystem admin) {
    	Enrollment enrollment;
    	
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            admin.displayInfo(); // profile

            System.out.println("Choose 1 - Add Student");
            System.out.println("Choose 2 - Add Course");
            System.out.println("Choose 3 - View Student List");
            System.out.println("Choose 4 - View Course List");
            System.out.println("Choose 5 - Update Grades");
            System.out.println("Choose 6 -Exit");

            System.out.print("Your choice: "); // Get choice
            choice = scanner.nextInt();
            delay();

            switch (choice) {
                case 1: // Add Student
                    int subChoice = 0;
                    do {
                        System.out.println("Enter the student's name: ");
                        String name = scanner.next();  // Assuming name is a single word
                        System.out.println("Enter the student's ID: ");
                        String Id = scanner.next(); // Assuming ID is a string
                        
                        Student newStudent = null;
                        
                        if (Id.startsWith("CB")) {
                        newStudent = new CreditBasedStudent(name, Id);
                        } else if (Id.startsWith("PT")) {
                        newStudent = new PartTimeStudent(name, Id);	
                        }
                        
                        if (newStudent != null) {
                            admin.addStudent(newStudent); // Assuming there's an addStudent method
                        } 

                        System.out.println("Would you like to add another student? (1 - Yes, 2 - No)");
                        subChoice = scanner.nextInt();
                    } while (subChoice != 1);
                    break;

                // Remove Student(not updated)

                case 2: // Add Course
                    System.out.println("Enter the course name: ");
                    String courseName = scanner.next();
                    
                    System.out.println("Enter the course ID: ");
                    String courseId = scanner.next();
                    
                    System.out.println("Enter credits: ");
                    int courseCredits = scanner.nextInt();
                    
                    System.out.println("Enter midWeight: ");
                    double courseMW = scanner.nextDouble();
                    
                    System.out.println("Enter finalWeight: ");
                    double courseFW = scanner.nextDouble();

                    System.out.println("Enter prerequisites: ");
                    String input = scanner.nextLine();  // Read the entire line of input

                    // Split the input string into an array using \\s+ as consecutive separators
                    String[] prerequisitesArray = input.split("\\s+");

                    // Convert the array to a List
                    List<String> coursePreq = new ArrayList<>();
                    for (String prereq : prerequisitesArray) {
                        coursePreq.add(prereq.trim());  // Trim any leading/trailing spaces from each course
                    }
                    
                    Course newCourse = new Course(courseId, courseName, courseCredits, coursePreq, courseMW, courseFW);
                    admin.addCourse(newCourse); // Assuming there's an addCourse method
                    System.out.println("Course added successfully!");
                    break;
                    
                case 3: // View Student List
                	int viewChoiceS = 0;
                	do
                	{
                		for (Student student : admin.getStudents()) {
                			student.displayInfo();
                		}
                		
                		System.out.println("Press 2 to exit");
                        viewChoiceS = scanner.nextInt();
                        delay();
                        
                	} while (viewChoiceS == 1);
                	break;
                	
                case 4:	// View Course List
                	int viewChoiceC = 0;
                	do
                	{
                		for (Course course : admin.getCourses()) {
                			course.viewCourseDetail();
                		}
                		
                		System.out.println("Press 2 to exit");
                        viewChoiceC = scanner.nextInt();
                        delay();
                        
                	} while (viewChoiceC == 1);
                	break;
                	

                case 5: // Update Grades
                    System.out.println("Enter the student ID to update grades: ");
                    String s_Id = scanner.next();
                    System.out.println("Enter the course ID: ");
                    String c_Id = scanner.next();
                    System.out.println("Enter mid grade: ");
                    double midGrade = scanner.nextDouble();
                    System.out.println("Enter final grade: ");
                    double finalGrade = scanner.nextDouble();
                    

                    admin.inputScore(s_Id, c_Id, midGrade, finalGrade); // Assuming there's an updateGrades method
                    System.out.println("Grades updated successfully.");
                    break;
                    
                //Create a list for PTS(not updated)

                case 6: // Exit
                    return; // Exit the loop and program
                    

            }

            System.out.println(); // Add a blank line for better readability
        } while (choice != 6); // Repeat until the user chooses to exit
    }
}


