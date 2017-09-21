package nl.infosupport.javaminor.week4.spring.springlabs;

import nl.infosupport.javaminor.week4.spring.springlabs.printer.PrinterService;

public class SetterDI {

  private PrinterService printerService;

  public void sayHello() {
    printerService.print("Hello. I was using setter injection.");
  }

  public void setPrinterService(PrinterService printerService) {
    this.printerService = printerService;
  }

}
