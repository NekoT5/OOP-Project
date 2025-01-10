package OOP;

import java.util.*;

public class Course {
    private String id;
    private String name;
    private int credits;
    private double midtermWeight;
    private double finalWeight;
    private List<String> prerequisites;

    public Course(String id, String name, int credits, List<String> prerequisites, double midtermWeight, double finalWeight) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.midtermWeight = midtermWeight;
        this.finalWeight = finalWeight;
        this.prerequisites = prerequisites != null ? prerequisites : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public double getMidtermWeight() {
        return midtermWeight;
    }

    public double getFinalWeight() {
        return finalWeight;
    }

    public void viewCourseDetail() {
        System.out.println("Course Name: " + name);
        System.out.println("Course ID: " + id);
        System.out.println("Credits: " + credits);
        System.out.println("Mid Term Weight: " + midtermWeight + " | Final Weight: " + finalWeight);
        System.out.println("Prerequisites: " + prerequisites);
        System.out.println();
    }
}