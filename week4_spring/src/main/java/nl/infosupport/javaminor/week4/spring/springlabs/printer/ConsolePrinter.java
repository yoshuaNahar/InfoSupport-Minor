package nl.infosupport.javaminor.week4.spring.springlabs.printer;

public class ConsolePrinter implements PrinterService {

  public void print(String message) {
    System.out.println("Printing: " + message);
  }

}
