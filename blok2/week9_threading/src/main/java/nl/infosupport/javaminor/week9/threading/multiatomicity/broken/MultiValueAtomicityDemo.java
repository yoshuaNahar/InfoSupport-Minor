package nl.infosupport.javaminor.week9.threading.multiatomicity.broken;

import demo.Factorizer;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.List;
import java.util.ArrayList;

public class MultiValueAtomicityDemo implements Factorizer {
    private final AtomicLong lastNumber = new AtomicLong();
    private final AtomicReference<Long[]> storedFactors =
            new AtomicReference<Long[]>();

    public Long[] factor(long number) {
        if (lastNumber.get() == number) {
            System.out.println("Return " + lastNumber.get() + " from cache");
            return storedFactors.get();
        } else {
            Long[] factors = calculate(number);
            storedFactors.set(factors);
            lastNumber.set(number);
            
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
