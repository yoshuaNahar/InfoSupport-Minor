package nl.infosupport.javaminor.week4.spring.springlabs.xml_config;

import nl.infosupport.javaminor.week4.spring.springlabs.printer.PrinterService;

public class FactoryMethodInstantiation {

  private PrinterService printerService;
  private String value;

  private FactoryMethodInstantiation() {
  }

  public static FactoryMethodInstantiation createInstance(PrinterService printerService, String value) {
    FactoryMethodInstantiation instance = new FactoryMethodInstantiation();
    instance.printerService = printerService;
    instance.value = value;
    return instance;
  }

  public void saySomething() {
    printerService.print(value);
  }

}
