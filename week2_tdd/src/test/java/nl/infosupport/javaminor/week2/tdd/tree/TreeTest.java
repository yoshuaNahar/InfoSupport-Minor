package nl.infosupport.javaminor.week2.tdd.tree;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TreeTest {

  private Tree tree;

  @Before
  public void setup() {
    tree = new Tree();
  }

  @Test
  public void instantiateTreeDepthShouldBe0() {
    assertThat(tree.getDepth(), is(0));
  }

  @Test
  public void addSingleValueDepthShouldBe1() {
    tree.add(5);

    assertThat(tree.getDepth(), is(1));
  }

  @Test
  public void addTwoValuesValueDepthShouldBe2() {
    tree.add(5);
    tree.add(6);

    assertThat(tree.getDepth(), is(2));
  }

  @Test
  public void addThreeValuesInOrderThatDepthShouldBe2() {
    tree.add(5);
    tree.add(6);
    tree.add(4);

    assertThat(tree.getDepth(), is(2));
  }

  @Test
  public void addThreeValuesInOrderThatDepthShouldBe3() {
    tree.add(5);
    tree.add(6);
    tree.add(7);

    assertThat(tree.getDepth(), is(3));
  }

  @Test
  public void addFourValuesInOrderThatDepthShouldBe4() {
    tree.add(5);
    tree.add(6);
    tree.add(7);
    tree.add(8);

    assertThat(tree.getDepth(), is(4));
  }

  @Test
  public void addFourValuesInOrderThatDepthShouldBe3() {
    tree.add(5);
    tree.add(6);
    tree.add(7);
    tree.add(4);

    assertThat(tree.getDepth(), is(3));
  }

  @Test(expected = DuplicateValueException.class)
  public void addDuplicateValuesShouldThrowDuplicateValueException() {
    tree.add(5);
    tree.add(5);
  }

  @Test
  public void emptyTreeCountShouldBe0() {
    assertThat(tree.count(), is(0));
  }

  @Test
  public void add1ValueCountShouldBe1() {
    tree.add(5);

    assertThat(tree.count(), is(1));
  }

  @Test
  public void add4ValuesCountShouldBe4() {
    tree.add(5);
    tree.add(6);
    tree.add(7);
    tree.add(4);

    assertThat(tree.count(), is(4));
  }

  @Test
  public void emptyTreeMaxShouldBe0() {
    assertThat(tree.max(), is(0));
  }

  @Test
  public void addOneValueMaxShouldBe5() {
    tree.add(5);

    assertThat(tree.max(), is(5));
  }

  @Test
  public void addFourValuesMaxShouldBe7() {
    tree.add(5);
    tree.add(6);
    tree.add(7);
    tree.add(4);

    assertThat(tree.max(), is(7));
  }

  @Test
  public void emptyTreeContainsShouldBeFalse() {
    assertThat(tree.contains(5), is(false));
  }

  @Test
  public void addOneValueContainsShouldBeTrue() {
    tree.add(5);

    assertThat(tree.contains(5), is(true));
  }

  @Test
  public void addFourValuesContainsShouldBeTrue() {
    tree.add(5);
    tree.add(6);
    tree.add(7);
    tree.add(4);

    assertThat(tree.contains(7), is(true));
  }

}
