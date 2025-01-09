package OOP;
import java.util.*;

class CreditBasedStudent extends Student {
    private int creditLimit = 20;
    private int creditRegistered = 0;

    public CreditBasedStudent(String id, String name) {
        super(id, name, "Credit-Based Student");
        this.coursesEnrolled = new ArrayList<>();
    }

    public boolean checkLimit(Course course) {
        if (creditRegistered + course.getCredits() > creditLimit) {
            System.out.println("Cannot register for the course due to exceeding the credit limit.");
            return false;
        }
        return true;
    }

    public boolean checkPrerequisites(Course course) {
        List<String> prerequisites = course.getPrerequisites();
        List<String> completedCourseIds = new ArrayList<>();
        
        for (Enrollment enrollment : coursesCompleted) {
            completedCourseIds.add(enrollment.getCourse().getId());
        }
        return completedCourseIds.containsAll(prerequisites);
    }

    public void registerCourse(Course course) {
        // Kiểm tra nếu đã đăng ký khóa học
        for (Enrollment enrollment : coursesEnrolled) {
            if (enrollment.getCourse().equals(course)) {
                System.out.println("Khóa học này đã được đăng ký.");
                return;
            }
        }
        // Kiểm tra giới hạn tín chỉ
        if (!checkLimit(course)) {
        	System.out.println("Cannot Enroll course: Credits Limit Exceed!");
            return;
        }
        // Kiểm tra điều kiện tiên quyết
        if (!checkPrerequisites(course)) {
            System.out.println("Cannot Enroll course: Prerequisites not met!");
            return;
        }
        // Đăng ký khóa học
        Enrollment newEnrollment = new Enrollment(this, course);
        coursesEnrolled.add(newEnrollment);
        creditRegistered += course.getCredits();
        System.out.println("Đăng ký thành công khóa học: " + course.getName());
    }
    
    public void DisplayMenu(List<Course> courses) {
    	Scanner scanner = new Scanner(System.in);	// Choice for CBS
        int choice;
        
    	do {
            System.out.println("Student Profile: ");
            displayInfo(); // profile 
            
            System.out.println("Choose 1 - Register Course ");
            System.out.println("Choose 2 - View Course");
            System.out.println("Choose 3 - Check Graduation");
            System.out.println("Choose 4 - Exit ");
            System.out.print("Your choice: ");	// Get choice
            choice = scanner.nextInt();           
            
            // Choice
            switch (choice) {
                case 1 -> {
                	String input;
                	do {
                        System.out.println("Enter CourseID (Press X to go back): ");
                        input = scanner.next().toUpperCase();                                               
                        // Check if student wants to back
                        if (input.equals("X")) {	// Value to exit loop
                            System.out.println("Returning to the main menu...");
                            scanner.nextLine();
                            break;
                        }
                            
                        Course selectedCourse = null;	// Find course by ID
                        for (Course course : courses) {
                            if (course.getId().equals(input)) {
                                selectedCourse = course;
                                break;
                            }
                        }

                        if (selectedCourse != null) {                               
                            registerCourse(selectedCourse);	// registerCourse
                        } else {
                            System.out.println("Cannot find course.");
                        }
                    } while (!input.equals("X"));
                }

                case 2 -> {
                    System.out.println("Courses Enrolled:");
                    if (coursesEnrolled.isEmpty()) {
                        System.out.println("You have not enrolled in any courses.");
                    } else {
                        for (Enrollment enrollment : coursesEnrolled) {
                            enrollment.displayEnrollmentInfo();
                        }
                    }
                    System.out.println("Press Enter to return to the main menu...");
                    scanner.nextLine(); // Xóa dòng thừa
                    scanner.nextLine(); // Chờ người dùng nhấn Enter
                }

                case 3 -> {	                    // Kiểm tra tốt nghiệp (cần thêm logic nếu cần)
                	validateGraduation();
                    System.out.println("Press Enter to return to the main menu...");
                    scanner.nextLine(); // Xóa dòng thừa
                    scanner.nextLine(); // Chờ người dùng nhấn Enter
                }

                case 4 -> System.out.println("Exiting..."); // Thoát vòng lặp do chương trình kết thúc       
                	
                default -> System.out.println("Invalid choice. Please try again.");
            }
                
            System.out.println(); // Thêm dòng trống để dễ nhìn
        } while (choice != 4); // Lặp lại cho đến khi người dùng chọn thoát
    }    
}

class PartTimeStudent extends Student {
    public PartTimeStudent(String id, String name) {
        super(id, name, "Part-Time Student");
    }

    public void registerCourse(Course course) {
        Enrollment enrollment = new Enrollment(this, course);
        addEnrollment(enrollment);
        System.out.println("Part-time student " + getName() + " has registered for course: " + course.getName());
    }
    
	
    public void DisplayMenu(List<Course> courses) {
    	Scanner scanner = new Scanner(System.in);
        int choice;
        
    	do {
            System.out.println("Student Profile: ");
            displayInfo(); // profile

            System.out.println("Choose 1 - View Course");	// Choice for PTS
            System.out.println("Choose 2 - Check Graduation");
            System.out.println("Choose 3 - Exit ");
            System.out.print("Your choice: "); // Get choice
            choice = scanner.nextInt();           
            
            switch (choice) {
                case 1 -> {
                	if (coursesEnrolled.isEmpty()) {
                        System.out.println("You have not enrolled in any courses.");
                	} else {
                        System.out.println("A list of course has been arranged for you!");                      
                        	
                        for (Enrollment enrollment : coursesEnrolled) {
                            enrollment.displayEnrollmentInfo();
                        }
                	}
                                                
                        System.out.println("Press Enter to return to the main menu...");
                        scanner.nextLine(); // Xóa dòng thừa
                        scanner.nextLine(); // Chờ người dùng nhấn Enter
                }	
                    
                case 2 -> {
                	validateGraduation(); //	Check graduation                       
                	System.out.println("Press Enter to return to the main menu...");
                    scanner.nextLine(); // Xóa dòng thừa
                    scanner.nextLine(); // Chờ người dùng nhấn Enter
                }

                case 3 -> System.out.println("Exiting...");		// Thoát vòng lặp do chương trình kết thúc
                
                default -> System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // Add a blank line for better look
        } while (choice != 3); // Repeat until exit
    }
}
