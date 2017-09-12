package exercises.part01_01;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import solutions.part01_01.Apple;
import solutions.part01_01.Filter;

public class FilterAppleTest {
	
	List<Apple> apples;
	
	@Before
	public void setUp() {
		apples=Arrays.asList(new Apple("green", 120),
							 new Apple("red", 80),
							 new Apple("green",200),
							 new Apple("red", 140));
	}
	
	public static List<Apple> filterApples(Filter<Apple> filter,List<Apple> stock){
		List<Apple> filteredApples=new ArrayList<>();
		for (Apple apple : stock) {
			if(filter.test(apple)) {
				filteredApples.add(apple);
			}
		}
		return filteredApples;
	}
	
	@Test
	public void filterAllGreenApplesWithAnonymousClass() {
		Filter<Apple> greenApplefilter=null;
		
		Stream<Apple> applesToFilter = apples.stream();
		Predicate<Apple> greenApplePredicate = apple -> apple.getColour().equalsIgnoreCase("green");
		greenApplefilter = applesToFilter.filter(greenApplePredicate)
		
		//TODO assign an instance of an anonymous class to the reference variable greenApplefilter
		//TODO the filter should filter all green apples
		
		List<Apple> filteredApples = filterApples(greenApplefilter, apples);
		
		filteredApples.forEach((apple) -> System.out.println(apple));
	}


	@Test
	public void filterAllApplesAbove100GramsWithAnonymousClass() {
		Filter<Apple> weightFilter=null;
		
		//TODO assign an instance of an anonymous class to the reference variable weightFilter
		//TODO the filter should filter all  apples above 100 grams
		
		List<Apple> filteredApples = filterApples(weightFilter, apples);
		
		filteredApples.forEach((apple) -> System.out.println(apple));
	}

	@Test
	public void filterAllGreenApplesAbove100GramsWithAnonymousClass() {
		Filter<Apple> greenAppleWeightfilter=null;

		//TODO assign an instance of an anonymous class to the reference variable greenAppleWeightfilter
		//TODO the filter should filter all  green apples above 100 grams
		
		List<Apple> filteredApples = filterApples(greenAppleWeightfilter, apples);
		
		filteredApples.forEach((apple) -> System.out.println(apple));
	}
	
	//TODO Copy the above 3 unittests to here and change the testnames into AnonymousFunction instead 
	//TODO of AnonymousClass
	//TODO remove the boiler plate code from the anonymous classes in such a way that a anonymous function remains
	
}
