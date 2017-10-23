package nl.infosupport.listsolver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;

// TODO: 1. Make it as you would have made it
// 2. Ask Joris what better names would be
// 3. Ask question of Code Reviews what they think
// 3a. Mention that I think that the way I write it is the best solution,
//     Because you have a Given the parameters, and you can include a
//     `containing  to indicate which element a List contains or a person object
//     `with` specific params.
// 3b. Talk about testing booleans. My teacher also started talking about
//     that boolean return methods are the hardest to test. He talked about
//     that you need to start with a red test (in TDD), but than if you
//     `return false;` by default, you don't have a failing test in my
//     second test isSolvableGivenEmptyListExpectFalse. So, only write
//     tests where you expect true, or make your isSolvable method return
//     true by default? How do you decide? (I opted for return false, by
//     default, because now the problem will bubble up to the person that
//     uses my method and got true for an unsolvable List. Whereas if you
//     returned false, you might get some false negatives. Which might less bad.

/*
 * Notes:
 * - Unit test as rudimentary code documentation of business requirements
 *
 */
public class ListSolverTest {

  private ListSolver listSolver = new ListSolver();

  @Test(expected = IllegalArgumentException.class)
  public void isSolvableGivenNullExpectIllegalArgumentException() {
    listSolver.isSolvable(null);
  }

//  Removed these test, because I can't test it. It starts green which is a bad test.
//  @Test
//  public void isSolvableGivenEmptyListExpectFalse() {
//    boolean isSolvable = listSolver.isSolvable(new ArrayList<>());
//
//    assertFalse(isSolvable);
//  }
//  @Test
//  public void isSolvableGivenListContainingOneExpectFalse() {
//    boolean isSolvable = listSolver.isSolvable(Arrays.asList(1));
//
//    assertFalse(isSolvable);
//  }
  @Test
  public void isSolvableGivenListContainingZeroAndZeroExpectFalse() {
    boolean isSolvable = listSolver.isSolvable(Arrays.asList(0, 0));

    assertFalse(isSolvable);
  }

  @Test
  public void isSolvableGivenListContainingOneAndNegOneExpectFalse() {
    boolean isSolvable = listSolver.isSolvable(Arrays.asList(1, -1));

    assertFalse(isSolvable);
  }

  @Test
  public void isSolvableGivenListContainingZeroExpectTrue() {
    boolean isSolvable = listSolver.isSolvable(Collections.singletonList(0));

    assertTrue(isSolvable);
  }

  @Test
  public void isSolvableGivenListContainingOneAndZeroExpectTrue() {
    boolean isSolvable = listSolver.isSolvable(Arrays.asList(1, 0));

    assertTrue(isSolvable);
  }

  @Test
  public void isSolvableGivenListContainingNegOneExpectFalse() {
    boolean isSolvable = listSolver.isSolvable(Collections.singletonList(-1));

    assertFalse(isSolvable);
  }

}
