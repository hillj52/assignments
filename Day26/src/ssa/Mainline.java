package ssa;

public class Mainline {

	public static void main (String[] args) {
		Students students = new Students();
		for (Student s : students.getAll()) {
			System.out.println(s);
		}
	}
}
