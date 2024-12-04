package Test;
import java.util.*;

public abstract class Student {
	private String id;
	private String name;
	private String type;
	private List<Course> registeredCourses;
		
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getType() {
    	return type;
    }
	
	protected void setType(String type) {
        this.type = type;
    }
	
	public Student(String id, String name) {
		this.id = id;
		this.name = name;
		this.registeredCourses = new ArrayList<>();
	}
	
	public abstract void registerCourse(Course course);   //Thể hiện tính đa hình
	
	public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }
	
	public void viewStudentDetail() {
        System.out.println("Student Details:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Type: " + getType());
        System.out.println("Các môn học đã đăng kí:");
        for (Course course : registeredCourses) {
            System.out.println(course.getName());
        }
    }
}


