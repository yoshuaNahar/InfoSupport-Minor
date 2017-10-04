package nl.infosupport.javaminor.case1.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "student_id", nullable = false, insertable = false, updatable = false)
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @ManyToMany
  private List<CourseInstance> courseInstances;

  public Student() {
  }

  public Long getId() {
    return id;
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

  public List<CourseInstance> getCourseInstances() {
    return courseInstances;
  }

  public void setCourseInstances(
      List<CourseInstance> courseInstances) {
    this.courseInstances = courseInstances;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", courseInstances=" + courseInstances +
        '}';
  }

}
