package nl.infosupport.minor.week3.streams._01bjava8challeges;

public class SalaryGrades {

  private int grade;
  private double highCutoff;
  private double lowCutoff;

  public SalaryGrades(int grade, double highCutoff, double lowCutoff) {
    this.grade = grade;
    this.highCutoff = highCutoff;
    this.lowCutoff = lowCutoff;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public double getHighCutoff() {
    return highCutoff;
  }

  public void setHighCutoff(double highCutoff) {
    this.highCutoff = highCutoff;
  }

  public double getLowCutoff() {
    return lowCutoff;
  }

  public void setLowCutoff(double lowCutoff) {
    this.lowCutoff = lowCutoff;
  }

  @Override
  public String toString() {
    return "SalaryGrades{" +
        "grade=" + grade +
        ", highCutoff=" + highCutoff +
        ", lowCutoff=" + lowCutoff +
        '}';
  }

}
