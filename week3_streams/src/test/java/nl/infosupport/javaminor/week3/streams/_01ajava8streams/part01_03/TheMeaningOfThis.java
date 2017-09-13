package nl.infosupport.javaminor.week3.streams._01ajava8streams.part01_03;

public class TheMeaningOfThis {

	public final int value = 4;

	public void whatIsThis() {
		int value = 6;
		Runnable r = new Runnable() {
			public final int value = 5;

			public void run() {
				int value = 10;
				System.out.println(this.value);
				System.out.println(this.getClass().getName());
			}
		};
		r.run();

		r = () -> {
			System.out.println("We are inside an anonymous function to what refers the this pointer");
			System.out.println(this.value);
			System.out.println(this.getClass().getName());
		};
		r.run();
	}

	public static void main(String... args) {
		TheMeaningOfThis m = new TheMeaningOfThis();
		m.whatIsThis(); 
		//TODO predict or guess which value will be printed inside the anonymous class Runnable
		//TODO and the anonymous function for this.value 
	}
}