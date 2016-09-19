package ssa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("serial")
public class Students extends HashMap<Integer, Student> {

	private SqlDB db;
	
	public List<Student> getAll() {
		ArrayList<Student> list = new ArrayList<Student>();
		for (Integer key:this.keySet()) {
			list.add(this.get(key));
		}
		list.sort(null);
		return list;
	}
	
	public Student getById(int id) {
		if (this.containsKey(id))
			return this.get(id);
		return null;
	}
	
	private void init() {
		List<Student> list = db.getAllStudents();
		for (Student s: list) {
			this.put(s.getId(), s);
		}
	}
	
	public Students() {
		super();
		this.db = SqlDB.getInstance();
		this.init();
	}
}
