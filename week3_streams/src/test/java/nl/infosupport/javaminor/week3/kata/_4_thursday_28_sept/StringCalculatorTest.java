package nl.infosupport.javaminor.week3.kata._4_thursday_28_sept;

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

  @Test(expected = IllegalArgumentException.class)
  public void addNullShouldThrowException() {
    stringCalculator.add(null);
  }

  @Test
  public void addEmptyStringShouldReturn0() {
    assertThat(stringCalculator.add(""), is(0));
  }

  @Test
  public void add0ShouldReturn0() {
    assertThat(stringCalculator.add("0"), is(0));
  }

  @Test
  public void add1ShouldReturn1() {
    assertThat(stringCalculator.add("1"), is(1));
  }

  @Test
  public void add123ShouldReturn6() {
    assertThat(stringCalculator.add("1,2,3"), is(6));
  }

  @Test
  public void add123WithLineBreakShouldReturn6() {
    assertThat(stringCalculator.add("1,2\n3"), is(6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void add123WithIllegalDelimiterShouldThrowException() {
    stringCalculator.add("1,\n2,3");
  }

//  @Test
//  public void add123WithCustomDelimiter

}
