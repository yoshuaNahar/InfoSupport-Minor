package nl.infosupport.javaminor.case1.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "course_instance")
public class CourseInstance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "course_instance_id", nullable = false, insertable = false, updatable = false)
  private Long id;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @ManyToOne
  @JoinColumn(name = "fk_course_id")
  private Course course;

  public CourseInstance() {
  }

  public Long getId() {
    return id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  @Override
  public String toString() {
    return "CourseInstance{" +
        "id=" + id +
        ", startDate=" + startDate +
        '}';
  }

}
