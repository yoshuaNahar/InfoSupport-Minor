package nl.infosupport.javaminor.case1.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "course_id", nullable = false, insertable = false, updatable = false)
  private Long id;

  @Column(name = "course_code", unique = true, nullable = false)
  private String courseCode;

  @Column(name = "title", nullable = false)
  // with updatable and insertable, eventhough they have values
  // and you try to merge or persist, they will not!
  // https://stackoverflow.com/questions/3805584/please-explain-about-insertable-false-updatable-false
  private String title;

  @Column(name = "duration", nullable = false)
  private int duration;

  @OneToMany(mappedBy = "course")
  private List<CourseInstance> courseInstances = new ArrayList<>();

  public Course() {
  }

  public Long getId() {
    return id;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
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
    return "Course{" +
        "id=" + id +
        ", courseCode='" + courseCode + '\'' +
        ", title='" + title + '\'' +
        ", duration=" + duration +
        ", courseInstances=" + courseInstances +
        '}';
  }

}
