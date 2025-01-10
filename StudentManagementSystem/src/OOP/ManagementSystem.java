package OOP;
import java.util.*;

interface Manageable {
    void addStudent(Student student);
    void addCourse(Course course);
    List<Student> getStudents();
    List<Course> getCourses();
    void inputScore(String studentId, String courseId, double midGrade, double finalGrade);
}

public class ManagementSystem implements Manageable {
    private String name;
    private String id;
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Enrollment> enrollments = new ArrayList<>();

    public ManagementSystem(String name, String id) {
        this.name = name;
        this.id = id;

        
        // Tạo các đối tượng Student và Course ở đây
        Student student1 = new CreditBasedStudent("CB101", "HSA");
        Student student2 = new PartTimeStudent("PT101", "HSB");
        
        Course math1 = new Course("MATH101", "Math I", 3, new ArrayList<>(), 0.3, 0.7);
        Course math2 = new Course("MATH102", "Math II", 3, Arrays.asList("MATH101"), 0.3, 0.7);
        Course physics = new Course("PHYS101", "Physics I", 2, Arrays.asList("MATH101"), 0.4, 0.6);
        Course programming = new Course("CS101", "Introduction to Programming", 4, new ArrayList<>(), 0.3, 0.7);
        
        // Thêm các đối tượng Student và Course vào danh sách
        students.add(student1);
        students.add(student2);
        
        courses.add(math1);
        courses.add(math2);
        courses.add(physics);
        courses.add(programming);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }
    
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void displayInfo() {
        System.out.println("Admin Profile: ");
        System.out.println("ID: " + getId() + ", Name: " + getName());
    }
    
    public void addStudent(Student student) {	// Phương thức thêm sinh viên
        students.add(student);
        System.out.println("Student " + student.getName() + " added successfully.");
    }

    public void addCourse(Course course) {	// Phương thức thêm khóa học
        courses.add(course);
        System.out.println("Course " + course.getName() + " added successfully.");
    }

    public void inputScore(String studentId, String courseId, double midtermScore, double finalScore) {    // Phương thức nhập điểm
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getId().equals(studentId) &&
                    enrollment.getCourse().getId().equals(courseId)) {
                enrollment.setScores(midtermScore, finalScore);
                System.out.println("Scores updated for " + enrollment.getStudent().getName() +
                        " in course " + enrollment.getCourse().getName());
                return;
            }
        }
        System.out.println("Enrollment not found.");    
    }
    
    public static void MS(ManagementSystem admin) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            admin.displayInfo(); // profile

            System.out.println("Choose 1 - Add Student");
            System.out.println("Choose 2 - Add Course");
            System.out.println("Choose 3 - View Student List");
            System.out.println("Choose 4 - View Course List");
            System.out.println("Choose 5 - Update Grades");
            System.out.println("Choose 6 - Add list for Part-Time Student");
            System.out.println("Choose 7 - Exit");

            System.out.print("Your choice: "); // Get choice
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> { // Add Student
                    int subChoice = 0;
                    do {
                        System.out.println("Enter the student's name: ");
                        String name = scanner.next();  // Assuming name is a single word
                        System.out.println("Enter the student's ID: ");
                        String Id = scanner.next().toUpperCase(); // Assuming ID is a string
                        
                        Student newStudent = null;
                        
                        if (Id.startsWith("CB")) {
                        newStudent = new CreditBasedStudent(name, Id);
                        } else if (Id.startsWith("PT")) {
                        newStudent = new PartTimeStudent(name, Id);	
                        }
                        
                        if (newStudent != null) {
                            admin.addStudent(newStudent); // Assuming there's an addStudent method
                            System.out.println("Student added successfully!");
                        } else {
                            System.out.println("Invalid student ID. Student not added.");
                        }

                        System.out.println("Would you like to add another student? (1 - Yes, 2 - No)");
                        subChoice = scanner.nextInt();
                    } while (subChoice == 1);
                }

                // Remove Student(not updated)

                case 2 -> { // Add Course
                	int choiceC = 0;
                	do {
	                    System.out.println("Enter the course name: ");
	                    String courseName = scanner.next();
	                    
	                    System.out.println("Enter the course ID: ");
	                    String courseId = scanner.next().toUpperCase();
	                    
	                    System.out.println("Enter credits: ");
	                    int courseCredits = scanner.nextInt();
	                    
	                    System.out.println("Enter midWeight: ");
	                    double courseMW = scanner.nextDouble();
	                    
	                    System.out.println("Enter finalWeight: ");
	                    double courseFW = scanner.nextDouble();
	                    scanner.nextLine();
	                    
	                    System.out.println("Enter prerequisites (separate by spaces, e.g., 'MATH101 ENG202 PHYS303'):");
	                    String prerequisite = scanner.nextLine().toUpperCase(); // Đọc toàn bộ dòng
	                    
	                    // Chia danh sách các prerequisite thành mảng
	                    String[] prerequisitesArray = prerequisite.split("\\s+");	       
	                    
	                    // Chuyển mảng thành danh sách
	                    List<String> coursePreq = new ArrayList<>();
	                    for (String prereq : prerequisitesArray) {
	                        if (!prereq.isBlank()) { // Kiểm tra xem chuỗi có rỗng không
	                            coursePreq.add(prereq.trim());
	                        }
	                    }
	                    
	                    for (Course course : admin.courses) {
	                    	if (course.getId().equals(courseId)) {
	                    		System.out.println("Course existed! Try again");
	                    		break;
	                    	}
	                    	
                    		Course newCourse = new Course(courseId, courseName, courseCredits, coursePreq, courseMW, courseFW);
                            admin.addCourse(newCourse); // Assuming there's an addCourse method
	                    }
	                    
	                    System.out.println("Continue?(1 - Yes, 2 = No)");
	                    choiceC = scanner.nextInt();
	                    
                	} while (choiceC == 1);
                }
                    
                case 3 -> { // View Student List
                	int viewChoiceS = 0;
                	do
                	{
                		for (Student student : admin.getStudents()) {
                			student.displayInfo();
                		}
                		
                		System.out.println("Press 2 to exit");
                        viewChoiceS = scanner.nextInt();
                        
                        if (viewChoiceS == 2) {
                            System.out.println("Returning to the main menu...");
                        }

                	} while (viewChoiceS != 2);
                }
                	
                case 4 -> {	// View Course List
                	int viewChoiceC = 0;
                	do
                	{
                		for (Course course : admin.getCourses()) {
                			course.viewCourseDetail();
                		}
                		
                		System.out.println("Press 2 to exit");
                        viewChoiceC = scanner.nextInt();
                        
                        if (viewChoiceC == 2) {
                            System.out.println("Returning to the main menu...");
                        }
                        
                	} while (viewChoiceC != 2);
                }
                	
                case 5 -> {		// Update Grades
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
                }
                    
                case 6 -> { // Update courses for part-time student
                    Student selectedStudent = null;
                    Course selectedCourse = null;
                    
                    // Loop until a valid student ID is entered
                    do {
                        System.out.println("Enter the student ID (Press X to exit): ");
                        String SID = scanner.next().toUpperCase();
                        
                        if (SID.equals("X")) {
                            System.out.println("Exiting...");
                            break;
                        }
                        
                        boolean studentFound = false;
                        for (Student student : admin.getStudents()) {
                            if (student.getId().equals(SID) && student instanceof PartTimeStudent) {
                                selectedStudent = student;
                                studentFound = true;
                                break;
                            }
                        }
                        
                        if (!studentFound) {
                        	System.out.println("Part-time student not found! Try again.");
                        }
                    } while (selectedStudent == null); // Keep looping until a valid student ID is found

                    // After selecting a student, loop to enter course ID
                    do {
                        System.out.println("Enter the course ID (Press X to exit): ");
                        String CID = scanner.next().toUpperCase();
                        
                        if (CID.equals("X")) {
                            System.out.println("Exiting...");
                            break;
                        }

                        boolean courseFound = false; // Flag to check if course is found
                        for (Course course : admin.getCourses()) {
                            if (course.getId().equals(CID)) {
                                selectedCourse = course;
                                courseFound = true;
                                break;
                            }
                        }
                        
                        if (!courseFound) {
                            System.out.println("Course not found! Try again.");
                        } else {
                        	// Check if the student is already enrolled in the course
                            boolean alreadyEnrolled = false;
                            for (Enrollment e : admin.getEnrollments()) {
                                if (e.getStudent().equals(selectedStudent) && e.getCourse().equals(selectedCourse)) {
                                    alreadyEnrolled = true;
                                    break;
                                }
                            }

                            if (alreadyEnrolled) {
                                System.out.println("Student is already registered for this course.");
                            } else {
                                // Create a new Enrollment object and add it to the list
                            	Enrollment newEnrollment = new Enrollment(selectedStudent, selectedCourse);
                                admin.getEnrollments().add(newEnrollment);
                                selectedStudent.getCoursesEnrolled().add(newEnrollment);
                                System.out.println("Student " + selectedStudent.getId() + " registered for course " + selectedCourse.getId() + ".");
                            }
                        }
                    } while (true);
                }
                	
                case 7 -> System.out.println("Exiting...");
                
                default -> System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // Add a blank line for better readability
        } while (choice != 7);// Repeat until the user chooses to exit
    }	
}
