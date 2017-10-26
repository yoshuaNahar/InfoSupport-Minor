package nl.infosupport.javaminor.blok1.week4.spring.springlabs.java_config.config;

import java.util.List;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.others.DynamicHitter;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.others.HitCounter;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.others.Hitter;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.printer.PrinterService;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.xml_config.NameFactory;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.printer.ConsolePrinter;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.xml_config.AutoWiringDI;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.xml_config.ConstructorDI;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.xml_config.FactoryMethodInstantiation;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.xml_config.SetterDI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

  @Bean
  public PrinterService printerService() {
    return new ConsolePrinter();
  }

  @Bean
  public SetterDI setterDI() {
    SetterDI setterDI = new SetterDI();
    setterDI.setPrinterService(printerService());
    return setterDI;
  }

  @Bean
  public ConstructorDI constructorDI() {
    return new ConstructorDI(printerService());
  }

  @Bean
  public AutoWiringDI autoWiringDI() {
    AutoWiringDI autoWiringDI = new AutoWiringDI();
    autoWiringDI.setPrinterService(printerService());
    return autoWiringDI;
  }

  @Bean
  public FactoryMethodInstantiation factoryMethodInstantiation() {
    FactoryMethodInstantiation factoryMethodInstantiation =
        FactoryMethodInstantiation.createInstance(printerService(), "My Test Annotation");
    return factoryMethodInstantiation;
  }

  @Bean
  public List<String> getNames() {
    return new NameFactory().createNameList();
  }

  @Bean
  @Scope(scopeName = "prototype") // remove will return 5
  public HitCounter hitCounter() {
    return new HitCounter();
  }

  @Bean
  public Hitter hitter1() {
    Hitter hitter1 = new Hitter();
    hitter1.setHitCounter(hitCounter());
    return hitter1;
  }

  @Bean
  public Hitter hitter2() {
    Hitter hitter2 = new Hitter();
    hitter2.setHitCounter(hitCounter());
    return hitter2;
  }

  @Bean
  public DynamicHitter hitter3() {
    DynamicHitter hitter3 = new DynamicHitter() {
      @Override
      public HitCounter getHitCounter() {
        return hitCounter();
      }
    };
    return hitter3;
  }

}
