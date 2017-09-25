package nl.infosupport.javaminor.week3.kata._3_monday_25_Sept;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

  private StringCalculator stringCalculator;

  @Before
  public void setup() {
    this.stringCalculator = new StringCalculator();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNullShouldThrowException() {
    stringCalculator.add(null);
  }

  @Test
  public void addEmptyStringShouldReturn0() {
    assertThat(stringCalculator.add(""), is(0));
  }

  @Test
  public void addString0ShouldReturn0() {
    assertThat(stringCalculator.add("0"), is(0));
  }

  @Test
  public void addString1ShouldReturn1() {
    assertThat(stringCalculator.add("1"), is(1));
  }

  @Test
  public void addString12ShouldReturn3() {
    assertThat(stringCalculator.add("1,2"), is(3));
  }

  @Test
  public void addString123ShouldReturn6() {
    assertThat(stringCalculator.add("1,2,3"), is(6));
  }

  @Test
  public void addString12WithOtherDelimiterShouldReturn3() {
    assertThat(stringCalculator.add("1\n2"), is(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addString12WithDoubleDelimiterShouldThrowException() {
    stringCalculator.add("1,\n2");
  }

  @Test
  public void addString12WithCustomDelimiterShouldReturn3() {
    assertThat(stringCalculator.add("//;\n1;2"), is(3));
  }

}
