package com.twoDB;

import com.twoDB.student.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twoDB.teacher.domain.Teacher;
import com.twoDB.teacher.repo.TeacherRepository;
import com.twoDB.student.repo.StudentRepository;

@RestController
public class StudentTeacherController {

  private final StudentRepository studentRepo;
  private final TeacherRepository teacherRepo;

  @Autowired
  StudentTeacherController(StudentRepository studentRepo, TeacherRepository teacherRepo) {
    this.studentRepo = studentRepo;
    this.teacherRepo = teacherRepo;
  }

  @RequestMapping("/findStudentTeacher/{id}")
  public String findStudentTeacher(@PathVariable("id") Long id) {
    Student student = studentRepo.findById(id);
    Teacher teacher = teacherRepo.findById(id);
    return student.getLastName() + " " + student.getFirstName() + " " + teacher.getLastName() + " " + teacher.getFirstName() + "!";
  }

  @RequestMapping("/saveStudentTeacher/")
  public void saveStudentTeacher(){
    studentRepo.save(new Student("abc","efg"));
    teacherRepo.save(new Teacher("uvw","xyz"));
  }
}
