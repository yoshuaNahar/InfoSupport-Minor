package nl.infosupport.javaminor.blok1.week4.spring.springlabs.xml_config;

import nl.infosupport.javaminor.blok1.week4.spring.springlabs.printer.PrinterService;

public class SetterDI {

  private PrinterService printerService;

  public void sayHello() {
    printerService.print("Hello. I was using setter injection.");
  }

  public void setPrinterService(PrinterService printerService) {
    this.printerService = printerService;
  }

}
