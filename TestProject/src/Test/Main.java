package Test;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Tạo một số khóa học
        Course math1 = new Course("M101", "Calculus I", 3, new String[]{}, 0.5, 0.5);
        Course math2 = new Course("M102", "Calculus II", 3, new String[] {"M101"}, 0.5, 0.5);
        Course physics = new Course("P101", "Physics I", 4, new String[]{"M101"}, 0.4, 0.6);
        Course programming = new Course("CS101", "Introduction to Programming", 3, new String[]{}, 0.3, 0.7);
        Course dsa = new Course("CS201", "Data Structure & Algorithm ", 3, new String[] {"CS101"}, 0.3, 0.7);

        // Tạo danh sách khóa học
        List<Course> regCourse = List.of(math1, math2, physics, programming, dsa);

        // Tạo một sinh viên credit-based
        CreditBasedStudent student1 = new CreditBasedStudent("001", "Alice");

        // Sinh viên credit-based đăng ký môn học
        student1.registerCourse(math1);
        student1.registerCourse(physics);
        student1.registerCourse(programming);
        student1.registerCourse(dsa);

        // Tạo một sinh viên part-time
        Student student2 = new PartTimeStudent("101", "Bob");
        // Sinh viên part-time đăng ký khóa học
        for (Course course : regCourse) {
            student2.registerCourse(course); // Đảm bảo tính đa hình khi gọi phương thức registerCourse()
        }
        System.out.print(student2.getName() + " đã dăng ký thành công các môn: ");
        for (Course course : regCourse) {
            System.out.print(course.getName() + ", ");
        }
        System.out.println(); // Dòng trống cuối cùng để làm gọn giao diện
    }
}
