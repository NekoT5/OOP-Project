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
    private List<Student> students;
    private List<Course> courses;
    private List<Enrollment> enrollments;

    public ManagementSystem(String name, String id) {
        this.name = name;
        this.id = id;
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.enrollments = new ArrayList<>();
        
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

    public void displayInfo() {
        System.out.println("Admin Profile: ");
        System.out.println("ID: " + getId() + ", Name: " + getName());
    }

    // Phương thức thêm sinh viên
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student " + student.getName() + " added successfully.");
    }

    // Phương thức thêm khóa học
    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course " + course.getName() + " added successfully.");
    }

    // Phương thức nhập điểm
    public void inputScore(String studentId, String courseId, double midtermScore, double finalScore) {
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
}
