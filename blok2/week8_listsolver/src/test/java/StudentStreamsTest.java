import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

public class StudentStreamsTest {

  private List<Student> students = Arrays.asList(
      new Student("Lorenzo", true, 23),
      new Student("Dennis", true, 26),
      new Student("Bob", true, 25),
      new Student("Rico", false, 23),
      new Student("Hans", false, 25),
      new Student("Ruben", true, 20),
      new Student("Tinne", true, 21),
      new Student("Noah", false, 20),
      new Student("Sander", false, 22),
      new Student("Jimmy", true, 22),
      new Student("Yoshua", true, 22),
      new Student("Jeffrey", true, 21)
  );

  @Test
  public void sumOfStudentAges() {
    int studentsAgesSum = students
        .stream()
        .mapToInt(Student::getAge)
        .sum();

    System.out.println(studentsAgesSum);
  }

  @Test
  public void groupByLazy() {
    Map<Boolean, List<Student>> isLazyGroupMap = students
        .stream()
        .collect(groupingBy(Student::isLazy));

    System.out.println(isLazyGroupMap);
  }

  @Test
  public void groupByAge() {
    Map<Integer, List<Student>> ageGroupMap = students
        .stream()
        .collect(groupingBy(Student::getAge));

    System.out.println(ageGroupMap);
  }

  @Test
  public void groupByFirstLetterInName() {
    Map<Character, List<Student>> firstLetterGroupMap = students
        .stream()
        .collect(groupingBy(student -> student.getName().charAt(0)));

    System.out.println(firstLetterGroupMap);
  }

  @Test
  public void groupByNameLength() {
    Map<Integer, List<String>> nameLengthGroupMap = students
        .stream()
        .map(Student::getName)
        .collect(groupingBy(String::length));

    System.out.println(nameLengthGroupMap);
  }

  @Test
  public void getStudentCountInEachNameLengthGroup() {
    students
        .stream()
        .map(Student::getName)
        .collect(groupingBy(String::length, Collectors.counting()))
        .forEach((nameLength, listSize) -> System.out.println(nameLength + " " + listSize));
  }

  @Test
  public void groupByStudentNameLengthAndThenGroupByVowelCount() {
//    students
//        .stream()
//        .map(Student::getName)
//        .collect(String::length, groupingBy((String name) -> amountOfVowelsIn(name)));
  }

  private int amountOfVowelsIn(String string) {
    int length, vowels = 0;
    String j;
    length = string.length();
    for (int i = 0; i < length; i++) {
      j = string.substring(i, i + 1);
      System.out.println(j);
      if (j.equalsIgnoreCase("a")) {
        vowels++;
      } else if (j.equalsIgnoreCase("e")) {
        vowels++;
      } else if (j.equalsIgnoreCase("i")) {
        vowels++;
      } else if (j.equalsIgnoreCase("o")) {
        vowels++;
      } else if (j.equalsIgnoreCase("u")) {
        vowels++;
      }
    }
    return vowels;
  }

}
