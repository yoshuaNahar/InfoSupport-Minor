package org.infosupport.minorjava.isTraversable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class IsTraversableListTests {

  private MovementsUtilty util;

  @Before
  public void setUp() {
    util = new MovementsUtilty();
  }

  @Test
  public void anEmptyListOfMovesShouldReturnFalse() {
    List<Integer> emptyListOfMoves = new ArrayList<Integer>();
    assertThat(util.isTraversable(emptyListOfMoves), is(false));
  }

  @Test
  public void anListOfMovesWhereTheLastMoveIsNotZeroShouldReturnFalse() throws Exception {
    List<Integer> listWithALastNotZeroMove = Arrays.asList(1);
    assertThat(util.isTraversable(listWithALastNotZeroMove), is(false));
  }

  @Test
  public void aListOf2MovesContaining1And0InLeftToRightOrderShouldReturnTrue() throws Exception {
    List<Integer> moves = Arrays.asList(1, 0);
    assertThat(util.isTraversable(moves), is(true));
  }

  @Test
  public void aListOf3MovesContaining2AndArbitraryNumberAnd0ShouldReturnTrue() throws Exception {
    int arbitraryNumber = 5;
    List<Integer> moves = Arrays.asList(2, arbitraryNumber, 0);
    assertThat(util.isTraversable(moves), is(true));
  }


  @Test
  public void aListOf3MovesContainingAFirstMoveLargerThan2ShouldReturnFalse() throws Exception {
    int arbitraryNumber = 5;
    int firstNumberOfMoves = 3;
    List<Integer> moves = Arrays.asList(firstNumberOfMoves, arbitraryNumber, 0);
    assertThat(util.isTraversable(moves), is(false));
  }

  @Test
  public void aListOfArbitraryMovesContainingAFirstNegativeMoveShouldReturnFalse()
      throws Exception {
    List<Integer> moves = Arrays.asList(-1, 0);
    Arrays.asList(util.isTraversable(moves), is(false));
  }

  @Test
  public void aListOf3MovesContainingMoves1And1And0ShouldReturnTrue() throws Exception {
    List<Integer> moves = Arrays.asList(1, 1, 0);
    assertThat(util.isTraversable(moves), is(true));
  }


  @Test
  public void aListOf3MovesContainingMoves2And1And0ShouldReturnTrue() throws Exception {
    List<Integer> moves = Arrays.asList(1, 1, 0);
    assertThat(util.isTraversable(moves), is(true));
  }

  @Test
  public void aListOf5MovementsContainingMoves2x2y0ShouldReturnTrueWherexAndyAreArbitrary()
      throws Exception {
    int arbitraryNumber = 4;
    List<Integer> moves = Arrays.asList(2, arbitraryNumber, 2, arbitraryNumber, 0);
    assertThat(util.isTraversable(moves), is(true));
  }

  @Test
  public void aListOfMovements2And2Minus1And0ShouldReturnTrue() throws Exception {
    List<Integer> moves = Arrays.asList(2, 2, -1, 0);
    assertThat(util.isTraversable(moves), is(true));
  }

  @Test
  public void aListOf3MovementsContaining1AndMinus1And0ShouldReturnFalse() throws Exception {
    List<Integer> moves = Arrays.asList(1, -1, 0);
    assertThat(util.isTraversable(moves), is(false));
  }

  @Test
  public void justForFun() throws Exception {
    List<Integer> moves = Arrays.asList(4, 2, 6, 2, -1, 2, 0, -5, 0);
    assertThat(util.isTraversable(moves), is(true));
  }

}
