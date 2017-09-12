package exercises.part01_06;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import org.junit.Test;


public class AppleFactorTest {
	
	@Test
	public void createApplesOfDifferentKinds() {

		ToBeChanged f=null;
		//Define an function variable f, that holds a method reference to 
		// the apple constructor expecting a String representing the colour
		// and an int representing the weight
		
		
		String [] colors= {"blue","orange","grey","black","green"};
		
		int [] weights= {1,2,3,4,5,6};
		
		//Create an appleFactory method that when called like in the statement below will
		// result in an List of 30 apples
		List<Apple> apples=appleFactory(colors, weights, f);
		
		System.out.println("Total number of apples = " + apples.size());
		
	}
	
	//Skeleton of the appleFactory method
	public static List<Apple> appleFactory(String [] colors,int [] weights,ToBeChanged f) {
		List<Apple> freshApples= new ArrayList<>();
		for (String color: colors) {
			for (int i : weights) {
				freshApples.add(f.apply(color, i));
			}
		}
		return freshApples;
	}

}
