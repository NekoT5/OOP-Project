package Test;

public class Enrollment {
    private Student student;
    private Course course;
    private double midGrade = 0;
    private double finalGrade = 0;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
        
    public void setGrade(double midGrade, double finalGrade) {
		this.midGrade = midGrade;
		this.finalGrade = finalGrade;
    }

	public Student getStudent() {
		return student;
	}

	public Course getCourse() {
		return course;
	}
	
	public double calculateFinalGrade() {
        return (midGrade * course.getMidWeight()) + (finalGrade * course.getFinalWeight());
    }
}