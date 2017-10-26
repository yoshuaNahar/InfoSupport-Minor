package nl.infosupport.javaminor.blok1.week4.spring.springlabs.xml_config;

import nl.infosupport.javaminor.blok1.week4.spring.springlabs.printer.PrinterService;

public class ConstructorDI {

  private PrinterService printerService;

  public ConstructorDI(PrinterService printerService) {
    this.printerService = printerService;
  }

  public void sayHello() {
    printerService.print("Hello. I was using constructor injection.");
  }

}
