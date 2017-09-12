package exercises.part01_04;

import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

public class MethodReferencesOfSystemFunctions {
	
	
	@Test
	public void aRondomChoiseOfMethodsFromSystemOldStyle() {
		//1 TODO in the next unit test you have to change this
		long currentTimeMillis = System.currentTimeMillis();
		
		//2 TODO in the next unit test you have to change this
		System.out.println(currentTimeMillis);
		
		//3 TODO in the next unit test you have to change this
		System.gc();
		
		//4 TODO in the next unit test you have to change this
		System.out.format("%s are an exciting thing for %s\n", "Lambda's","Java Programmers");
		
		//5 TODO in the next unit test you have to change this
		String property = System.getProperty("os.arch");
		
		System.out.println(property);
		
		System.out.println("*********************");
		
		//6 TODO in the next unit test you have to change this
		Properties properties = System.getProperties();
		
	
		properties.forEach((key,value)-> {
			//7 TODO in the next unit test you have to change this
			System.out.println("key is " + key + "\t\t\t\t\t: value is  " + value);
		});
		
		
	}

	@Test
	public void aRondomChoiseOfMethodsFromSystemWithMethodReferences() {
		//1 
		long currentTimeMillis = System.currentTimeMillis();
		//1 TODO Use Supplier<T> interface to define a variable which holds a function reference.
		//  assing a method reference returning the time in milli seconds of the system
		
		
		//2 
		System.out.println(currentTimeMillis);
		
		//2 TODO define a function variable of type Consumer that expects a long.
		//assign an appropriate method reference that consumes the long by printing it to the console
		
		
		
		//3 
		System.gc();
		//3 TODO
		// Assign a method reference to an appropriate typed function variable.
		// The method reference calls gc() when the run() method(Hint!) is executed.
		
		
		//4 
		System.out.format("%s are an exciting thing for %s\n", "Lambda's","Java Programmers");
	
		// 4 TODO create an appropriate typed function variable expecting 2 type variables of 
		// type String and String[] 
		// Execute the method by calling the accept method on your function variable.
		
		
		String [] args= {"Lambda's","Java Programmers"};
		
	
		
		
		//5 
		String property = System.getProperty("os.arch");
		
		// 5 TODO create an appropriate typed function variable 
		
		// when the apply method is called on this variable it should return a String
				
	
		// 5 Extra
		System.out.println(property);
		
		// 5 Extra TODO Consume the String returned by our function variable when apply is called
		
		// Assign the consumer function to an appropriately typed function variable

		
		
		
		System.out.println("*********************");
		
		
		
		//6 
		Properties properties = System.getProperties();
		
		// 6 TODO Assign the getProperties method as a method reference to an appropriately typed variable
	
		
		
		properties.forEach((key,value)-> {
			//7 
			//7 TODO Assign the format method as a method reference to an appropriately typed variable
			//7 TODO Get the code working, you probably need an extra variable to get it working
			System.out.format("%30s  %s\n", key,value);
		});
		
	
	
		
	
		Object [] keyValuePair=new Object[2];
		
		properties. forEach((key,value) -> {
								keyValuePair[0]=key;
								keyValuePair[1]=value;
								//insert function variable here
							}
						);
		
	}
}
