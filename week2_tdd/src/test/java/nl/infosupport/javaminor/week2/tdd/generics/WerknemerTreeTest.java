package nl.infosupport.javaminor.week2.tdd.generics;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import nl.infosupport.tdd.tree.DuplicateValueException;
import org.junit.Before;
import org.junit.Test;

public class WerknemerTreeTest {

  private WerknemerTree tree;

  @Before
  public void setup() {
    tree = new WerknemerTree();
  }

  @Test
  public void instantiateTreeDepthShouldBe0() {
    assertThat(tree.getDepth(), is(0));
  }

  @Test
  public void addSingleValueDepthShouldBe1() {
    tree.add(new VasteWerknemer(1, "A", 10));

    assertThat(tree.getDepth(), is(1));
  }

  @Test
  public void addTwoValuesValueDepthShouldBe2() {
    tree.add(new VasteWerknemer(1, "A", 10));
    tree.add(new VasteWerknemer(2, "B", 11));

    assertThat(tree.getDepth(), is(2));
  }

  @Test
  public void addThreeValuesInOrderThatDepthShouldBe2() {
    tree.add(new VasteWerknemer(1, "A", 10));
    tree.add(new VasteWerknemer(2, "B", 11));
    tree.add(new VasteWerknemer(3, "C", 9));

    assertThat(tree.getDepth(), is(2));
  }

  @Test
  public void addThreeValuesInOrderThatDepthShouldBe3() {
    tree.add(new VasteWerknemer(1, "A", 10));
    tree.add(new VasteWerknemer(2, "B", 11));
    tree.add(new VasteWerknemer(4, "D", 12));

    assertThat(tree.getDepth(), is(3));
  }

  @Test
  public void addFourValuesInOrderThatDepthShouldBe4() {
    tree.add(new VasteWerknemer(1, "A", 10));
    tree.add(new VasteWerknemer(2, "B", 11));
    tree.add(new VasteWerknemer(4, "D", 12));
    tree.add(new VasteWerknemer(5, "E", 100));

    assertThat(tree.getDepth(), is(4));
  }

  @Test
  public void addFourValuesInOrderThatDepthShouldBe3() {
    tree.add(new VasteWerknemer(1, "A", 10));
    tree.add(new VasteWerknemer(2, "B", 11));
    tree.add(new VasteWerknemer(4, "D", 12));
    tree.add(new VasteWerknemer(3, "C", 9));

    assertThat(tree.getDepth(), is(3));
  }

  @Test(expected = DuplicateValueException.class)
  public void addDuplicateValuesShouldThrowDuplicateValueException() {
    tree.add(new VasteWerknemer(1, "A", 10));
    tree.add(new VasteWerknemer(1, "A", 10));
  }

  @Test
  public void emptyTreeCountShouldBe0() {
    assertThat(tree.count(), is(0));
  }

  @Test
  public void add1ValueCountShouldBe1() {
    tree.add(new VasteWerknemer(1, "A", 10));

    assertThat(tree.count(), is(1));
  }

  @Test
  public void add4ValuesCountShouldBe4() {
    tree.add(new VasteWerknemer(1, "A", 10));
    tree.add(new VasteWerknemer(2, "B", 11));
    tree.add(new VasteWerknemer(4, "D", 12));
    tree.add(new VasteWerknemer(3, "C", 9));

    assertThat(tree.count(), is(4));
  }

  @Test
  public void emptyTreeMaxShouldBe0() {
    assertThat(tree.max(), nullValue());
  }

  @Test
  public void addOneValueMaxShouldBe5() {
    tree.add(new VasteWerknemer(1, "A", 10));

    assertThat(tree.max().getSalaris(), is(10_000));
  }

  @Test
  public void addFourValuesMaxShouldBe7() {
    tree.add(new VasteWerknemer(1, "A", 10));
    tree.add(new VasteWerknemer(2, "B", 11));
    tree.add(new VasteWerknemer(4, "D", 12));
    tree.add(new VasteWerknemer(3, "C", 9));


    assertThat(tree.max().getSalaris(), is(12_000));
  }

  @Test
  public void emptyTreeContainsShouldBeFalse() {
    assertThat(tree.contains(new VasteWerknemer(1, "A", 10)), is(false));
  }

  @Test
  public void addOneValueContainsShouldBeTrue() {
    VasteWerknemer vw = new VasteWerknemer(1, "A", 10);
    tree.add(vw);

    assertThat(tree.contains(vw), is(true));
  }

  @Test
  public void addFourValuesContainsShouldBeTrue() {
    VasteWerknemer vw = new VasteWerknemer(4, "D", 12);
    tree.add(new VasteWerknemer(1, "A", 10));
    tree.add(new VasteWerknemer(2, "B", 11));
    tree.add(vw);
    tree.add(new VasteWerknemer(3, "C", 9));

    assertThat(tree.contains(vw), is(true));
  }

}
