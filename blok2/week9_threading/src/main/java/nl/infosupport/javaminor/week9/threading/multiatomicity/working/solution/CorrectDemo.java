package nl.infosupport.javaminor.week9.threading.multiatomicity.working.solution;

import java.util.ArrayList;
import java.util.List;
import nl.infosupport.javaminor.week9.threading.multiatomicity.Factorizer;

public class CorrectDemo implements Factorizer {

  private long lastNumber;
  private Long[] storedFactors;

  public Long[] factor(long number) {
    synchronized (this) {
      if (lastNumber == number) {
        System.out.println("Return " + lastNumber + " from cache");
        return storedFactors;
      }
    }
    Long[] factors = calculate(number);

    synchronized (this) {
      storedFactors = factors;
      lastNumber = number;

      return factors;
    }
  }

  private Long[] calculate(long number) {
    List factorsList = new ArrayList();

    for (long i = 2; i < number; i++) {
      if (number % i == 0) {
        factorsList.add(i);
      }
    }
    Long[] result = new Long[factorsList.size()];

    return (Long[]) factorsList.toArray(result);
  }

}
