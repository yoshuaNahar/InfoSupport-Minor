package nl.infosupport.javaminor.case1.entities.view;

public class PersistedStatistics {

  private int persistedCourses;
  private int persistedCoursesInstances;

  public PersistedStatistics(int persistedCourses, int persistedCoursesInstances) {
    this.persistedCourses = persistedCourses;
    this.persistedCoursesInstances = persistedCoursesInstances;
  }

  public int getPersistedCourses() {
    return persistedCourses;
  }

  public void setPersistedCourses(int persistedCourses) {
    this.persistedCourses = persistedCourses;
  }

  public int getPersistedCoursesInstances() {
    return persistedCoursesInstances;
  }

  public void setPersistedCoursesInstances(int persistedCoursesInstances) {
    this.persistedCoursesInstances = persistedCoursesInstances;
  }

  @Override
  public String toString() {
    return "PersistedStatistics{" +
        "persistedCourses=" + persistedCourses +
        ", persistedCoursesInstances=" + persistedCoursesInstances +
        '}';
  }

}
