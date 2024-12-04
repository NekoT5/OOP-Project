package Test;

public class Course {
	
	private String id;
	private String name;
	private int credit;
	private String[] prerequisites;
	private double midWeight;
	private double finalWeight;
	
	public Course(String id, String name, int credit, String[] prerequisites, double midWeight, double finalWeight) {
		this.id = id;
		this.name = name;
		this.credit = credit;
		this.prerequisites = prerequisites;
		this.midWeight = midWeight;
		this.finalWeight = finalWeight;
	}
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public int getCredit() {
		return credit;
	}

	public String[] getPrerequisites() {
		return prerequisites;
	}
	
	public double getMidWeight() {
		return midWeight;
	}

	public double getFinalWeight() {
		return finalWeight;
	}

	public void viewCourseDetail() {
		System.out.println("Course ID: " + id);
        System.out.println("Course Name: " + name);
        System.out.println("Prerequisites: " + String.join(",", prerequisites));
        System.out.println("Credits: " + credit);
        System.out.println("Midweight: " + midWeight);
        System.out.println("Finalweight: " + finalWeight);		
	}
}
