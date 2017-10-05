package nl.infosupport.javaminor.case1.services;

import java.util.List;
import nl.infosupport.javaminor.case1.daos.StudentDao;
import nl.infosupport.javaminor.case1.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

  private StudentDao studentDao;

  @Autowired
  public StudentService(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  public Student getStudentById(Long studentId) {
    return studentDao.getStudentById(studentId);
  }

  public List<Student> getStudents() {
    return studentDao.getStudents();
  }

  public void saveStudent(Student student) {
    studentDao.saveStudent(student);
  }

  public void addCourseInstance(Student student) {
    studentDao.mergeStudent(student);
  }

}
