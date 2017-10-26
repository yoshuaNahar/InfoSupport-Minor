import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Student {

  private String name;
  private boolean lazy;
  private int age;

  public Student(String name, boolean lazy, int age) {
    this.name = name;
    this.lazy = lazy;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isLazy() {
    return lazy;
  }

  public void setLazy(boolean lazy) {
    this.lazy = lazy;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", lazy=" + lazy +
        ", age=" + age +
        '}';
  }

}
