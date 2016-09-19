package ssa;

public class Student implements Comparable<Student> {

	private int id;
	private String firstName;
	private String lastName;
	private int sat;
	private double gpa;
	private Major major;
	
	public int compareTo(Student s) {
		return this.id - s.getId();
	}
	
	public String toString() {
		return this.id + " " + String.format("%-25s",this.firstName + " " + this.lastName) +
				" " + String.format("%.2f",this.gpa) + " " + this.sat;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Student(int id, String firstName, String lastName, int sat, double gpa, Major major) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.sat = sat;
		this.gpa = gpa;
		this.major = major;
	}
}
