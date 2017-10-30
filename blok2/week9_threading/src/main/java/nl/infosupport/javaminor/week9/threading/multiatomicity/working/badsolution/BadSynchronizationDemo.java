package nl.infosupport.javaminor.week9.threading.multiatomicity.working.badsolution;

import java.util.ArrayList;
import java.util.List;
import nl.infosupport.javaminor.week9.threading.multiatomicity.Factorizer;

public class BadSynchronizationDemo implements Factorizer {

  private long lastNumber;
  private Long[] storedFactors;

  public synchronized Long[] factor(long number) {
    if (lastNumber == number) {
      System.out.println("Return " + lastNumber + " from cache");
      return storedFactors;
    } else {
      Long[] factors = calculate(number);
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
