package com.ssa.eddb;

public class Student {
	
	private int id;
	private String firstName;
	private String lastName;
	private int sat;
	private double gpa;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getSat() {
		return sat;
	}
	public void setSat(int sat) {
		this.sat = sat;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public int getId() {
		return id;
	}
	
	public Student() {}
	
	public Student(String firstName, String lastName, int sat, double gpa) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sat = sat;
		this.gpa = gpa;
	}
	
}
