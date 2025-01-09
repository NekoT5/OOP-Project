package OOP;

public class Enrollment {	// Register Course Confirmation
    private Student student;
    private Course course;
    private double midtermScore;
    private double finalScore;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.midtermScore = 0;
        this.finalScore = 0;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public void setScores(double midtermScore, double finalScore) {
        this.midtermScore = midtermScore;
        this.finalScore = finalScore;
    }

    public double calculateFinalGrade() {
        return midtermScore * course.getMidtermWeight() + finalScore * course.getFinalWeight();
    }
    
    public double convertToGPA() {
        double score = calculateFinalGrade();
        	
        if (score >= 8.5) return 4.0;	// A & A+
        if (score >= 8.0) return 3.5;	// B+
        if (score >= 7.0) return 3.0;	// B
        if (score >= 6.5) return 2.5;	// C+
        if (score >= 5.5) return 2.0;	// C
        if (score >= 5.0) return 1.5;	// D+
        if (score >= 4.0) return 1.0; 	// D
        return 0.0; // Nếu điểm thấp hơn 4.0(F)
    }

    public void displayEnrollmentInfo() {
        System.out.println("Course: " + course.getName() +
                ", Midterm: " + midtermScore + ", Final: " + finalScore +
                ", Final Grade: " + calculateFinalGrade() + 
                ", GPA: " + convertToGPA());
    }
}