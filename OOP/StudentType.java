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
}
