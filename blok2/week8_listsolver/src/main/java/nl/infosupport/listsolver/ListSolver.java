package nl.infosupport.listsolver;

import java.util.Arrays;
import java.util.List;

public class ListSolver {

  public static void main(String[] args) {
    ListSolver solver = new ListSolver();
    System.out.println(solver.isSolvable(Arrays.asList(1, 0)));
    System.out.println(solver.isSolvable(Arrays.asList(0)));
    System.out.println(solver.isSolvable(Arrays.asList(2, 10, 0)));
    System.out.println(solver.isSolvable(Arrays.asList(2, 2, -1, 0)));
  }

  /**
   *
   * @param list
   * @return
   */
  public boolean isSolvable(List<Integer> list) {
    if (list == null) {
      throw new IllegalArgumentException("List is null");
    }

    int listSize = list.size();
    int lastIndex = listSize - 1;
    int currentIndexValue;
    for (int i = 0, loopCounter = 0; i < listSize; i = i + currentIndexValue, loopCounter++) {
      try {
        currentIndexValue = list.get(i);
      } catch (IndexOutOfBoundsException e) {
        return false;
      }

      // if traversed to last index and last index is 0
      if (i == lastIndex && currentIndexValue == 0) {
        return true;
      }

      // if infinite loop
      if (loopCounter > listSize) {
        return false;
      }
    }

    return false;
  }

}
