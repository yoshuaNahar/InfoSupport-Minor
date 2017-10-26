package nl.infosupport.javaminor.blok1.week4.jdbc;

public class Department {

  private int deptNo;
  private String dName;
  private String loc;

  public Department(int deptNo, String dName, String loc) {
    this.deptNo = deptNo;
    this.dName = dName;
    this.loc = loc;
  }

  public int getDeptNo() {
    return deptNo;
  }

  public void setDeptNo(int deptNo) {
    this.deptNo = deptNo;
  }

  public String getdName() {
    return dName;
  }

  public void setdName(String dName) {
    this.dName = dName;
  }

  public String getLoc() {
    return loc;
  }

  public void setLoc(String loc) {
    this.loc = loc;
  }

  @Override
  public String toString() {
    return "Department{" +
        "deptNo=" + deptNo +
        ", dName='" + dName + '\'' +
        ", loc='" + loc + '\'' +
        '}';
  }

}
