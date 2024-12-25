package OOP;

import java.util.*;

public abstract class Student {
    private String id;
    private String name;
    private String type;
    protected List<Enrollment> coursesEnrolled;
    protected List<Enrollment> coursesCompleted;

    public Student(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.coursesEnrolled = new ArrayList<>();
        this.coursesCompleted = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public List<Enrollment> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public List<Enrollment> getCoursesCompleted() {
        return coursesCompleted;
    }

    public abstract void registerCourse(Course course);

    public void displayInfo() {
        System.out.println("Student Profile: ");
        System.out.println("ID: " + getId() + ", Name: " + getName() + ", Role: " + type);
    }

    public double calculateGPA() {
        double totalScore = 0;
        int totalCredits = 0;
        for (Enrollment enrollment : coursesCompleted) {
            totalScore += enrollment.calculateFinalGrade() * enrollment.getCourse().getCredits();
            totalCredits += enrollment.getCourse().getCredits();
        }
        return totalCredits == 0 ? 0 : totalScore / totalCredits;
    }

    public void addEnrollment(Enrollment enrollment) {
        coursesEnrolled.add(enrollment);
    }
    
    public void validateGraduation() {    
        // Check conditions for graduation(GPA >= 2.5 + All courses completed)
        if ((calculateGPA() >= 2.5) && (coursesCompleted.containsAll(coursesEnrolled))) {
            System.out.println("All graduation requirements met!");
        } else {
            System.out.println("Requirement not met: ");
        }
    }
}
