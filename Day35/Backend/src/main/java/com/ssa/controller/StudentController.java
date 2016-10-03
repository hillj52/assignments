package com.ssa.controller;

import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.entity.Student;
import com.ssa.service.IStudentService;

@CrossOrigin(origins = {"http://localhost:8080","null"})
@RestController
@RequestMapping("/")
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@RequestMapping(value="/student",method=RequestMethod.GET)
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
	}
	
	@RequestMapping(value="/student/{id}",method=RequestMethod.GET)
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
		Student student = studentService.getStudentById(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@RequestMapping(value="/student/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
		Student student = studentService.getStudentById(id);
		studentService.deleteStudent(student);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@RequestMapping(value="/student/update",method=RequestMethod.PUT)
	public ResponseEntity<Student> update(@RequestBody Student student) {
		Student dbStudent = studentService.getStudentById(student.getId());
		dbStudent.setFirstName(student.getFirstName());
		dbStudent.setLastName(student.getLastName());
		dbStudent.setSat(student.getSat());
		dbStudent.setGpa(student.getGpa());
		studentService.updateStudent(student);
		return new ResponseEntity<Student>(dbStudent,HttpStatus.OK);
	}
	
	@RequestMapping(value="/student/add",method=RequestMethod.POST)
	public ResponseEntity<Boolean> add(@RequestBody Student student) {
		if(studentService.addStudent(student))
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
	}
	
}
