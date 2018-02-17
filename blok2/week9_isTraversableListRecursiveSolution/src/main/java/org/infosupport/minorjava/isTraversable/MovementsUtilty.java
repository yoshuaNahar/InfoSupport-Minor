package org.infosupport.minorjava.isTraversable;

import java.util.List;

public class MovementsUtilty {

  /**
   * <p>Checks if the moves(displacements) stored in the list form a path from the zero-th position
   * up to the last position.</p>
   *
   * <p>Moves are described with an<tt>int</tt> type where a positive number represents a movement
   * to the right and a negative number represents a movement to the left. The last position:
   * <tt>list.size() -1</tt> the most right position - must contain <tt>0</tt> When a movement
   * result in an invalid position <tt>isTraversable</tt> returns <tt>false</tt> </p>
   *
   * <p><b>Examples.</b>
   * <pre>{@code
   * isTraversable(asList(1,0))
   * isTraversable(asList(2,arbitraryNumber,0))
   * isTraversable(asList(2,arbitraryNumber,1,0))
   * }</pre>
   * All calls to isTraversable <tt>return true</tt> </p>
   *
   * @param moves the list containing the moves
   * @return <tt>true</tt> if a path is found from postion <tt>0</tt> to the last position
   */

  public boolean isTraversable(List<Integer> moves) {
    if (moves.isEmpty()) {
      return false;
    }
    if (isLastMoveNonZero(moves)) {
      return false;
    }

    int startPosition = 0;
    int maxTrials = moves.size() - 1;
    return (isTraversable(moves, startPosition, maxTrials));

  }

  private boolean isTraversable(List<Integer> moves, int position, int trialNumber) {
    //When you are still not done when trying for more than the number of positions then there must be a loop.
    if (trialNumber < 0) {
      return false;
    }

    int distanceFromZerothToNewPosition = position + moves.get(position);
    if (distanceFromZerothToNewPosition == stepsBetweenZerothAndLastPoint(moves)) {
      return true;
    } else {
      if (positionOutsideRange(moves, distanceFromZerothToNewPosition)) {
        return false;
      }
      return isTraversable(moves, distanceFromZerothToNewPosition, trialNumber - 1);
    }
  }

  private int stepsBetweenZerothAndLastPoint(List<Integer> moves) {
    return moves.size() - 1;
  }

  private boolean positionOutsideRange(List<Integer> moves, int position) {
    return crossesMovementLeftBorderOfList(position) || crossesMovementRightBorderOfList(moves,
        position);
  }

  private boolean crossesMovementLeftBorderOfList(int nextPosition) {
    return nextPosition < 0;
  }

  private boolean crossesMovementRightBorderOfList(List<Integer> moves, int nextPosition) {
    return nextPosition >= moves.size();
  }

  private boolean isLastMoveNonZero(List<Integer> moves) {
    return moves.get(stepsBetweenZerothAndLastPoint(moves)) != 0;
  }

}
