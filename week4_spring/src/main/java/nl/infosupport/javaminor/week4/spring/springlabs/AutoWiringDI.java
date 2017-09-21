package nl.infosupport.javaminor.week4.spring.springlabs;

import nl.infosupport.javaminor.week4.spring.springlabs.printer.PrinterService;

public class AutoWiringDI {

  private PrinterService printerService;

  public void setPrinterService(PrinterService printerService) {
    this.printerService = printerService;
  }

  public void sayHello() {
    printerService.print("Hello. I was using autowiring.");
  }

}
