package com.ssa.entity;

import javax.persistence.*;

@Entity
@Table(name="student")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="sat")
	private int sat;
	@Column(name="gpa")
	private double gpa;
	@Column(name="major_id")
	private Integer majorId;
	
	public String toString() {
		return this.id + ": " + this.lastName + ", " + this.firstName;
	}
	
	public Integer getMajorId() {
		return majorId;
	}
	
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

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
