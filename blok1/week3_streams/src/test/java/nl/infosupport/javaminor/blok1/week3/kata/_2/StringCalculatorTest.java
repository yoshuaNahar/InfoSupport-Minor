package nl.infosupport.javaminor.blok1.week3.kata._2;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

  private StringCalculator stringCalculator;

  @Before
  public void setup() {
    stringCalculator = new StringCalculator();
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
  public void addString123WithNewLineShouldReturn6() {
    assertThat(stringCalculator.add("1\n2,3"), is(6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addString1WithNewLineShouldGiveException() {
    stringCalculator.add("1,\n");
  }

}
