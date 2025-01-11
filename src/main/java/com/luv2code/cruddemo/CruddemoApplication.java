package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

		return runner -> {
//			createStudent(studentDAO);

//			createMultipleStudents(studentDAO);
			
//			readStudent(studentDAO);

//			queryForStudents(studentDAO);
//
//			queryForStudentsByLastName(studentDAO);

//			updateStudent(studentDAO);

			deleteStudent(studentDAO);
		};
	}

	private void deleteStudent(StudentDAO studentDAO) {

		// list all students before delete
		queryForStudents(studentDAO);

		// delete student with id 1
		studentDAO.delete(1);

		// list all students after deleted
		queryForStudents(studentDAO);
	}

	private void updateStudent(StudentDAO studentDAO) {

		// get a student
		Student tempStudent = studentDAO.findById(1);
		System.out.println("Retrieved student: " + tempStudent + " with id: " + tempStudent.getId());

		// change the first name of the student
		tempStudent.setFirstName("Scooby");

		// update the student
		studentDAO.update(tempStudent);

		// retrieve the updated student
		Student updatedStudent = studentDAO.findById(1);

		// display the updated student
		System.out.println("Updated student: " + updatedStudent);
	}

	private void queryForStudents(StudentDAO studentDAO) {

		System.out.println("Listing all students ...");

		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {

		System.out.println("Listing all students with last name Doe ...");
		List<Student> tempStudents = studentDAO.findByLastName("Doe");
		tempStudents.forEach(System.out::println);
	}

	private void readStudent(StudentDAO studentDAO) {

		// create new student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

		// save the student object
		System.out.println("Saving student object ...: " + tempStudent);
		studentDAO.save(tempStudent);

		// get student id
		int studentId = tempStudent.getId();
		System.out.println("Reading student id: " + studentId);

		// get the student object
		Student myStudent = studentDAO.findById(studentId);
		System.out.println("Retrieved student: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// create multiple student objects
		System.out.println("Creating new student object ...");
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

		// save the student objects
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent1.getId());
		System.out.println("Saved student. Generated id: " + tempStudent2.getId());
		System.out.println("Saved student. Generated id: " + tempStudent3.getId());

	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());

	}
}
