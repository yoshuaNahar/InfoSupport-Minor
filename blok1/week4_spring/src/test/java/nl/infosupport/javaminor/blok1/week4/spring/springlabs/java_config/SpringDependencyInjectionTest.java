package nl.infosupport.javaminor.blok1.week4.spring.springlabs.java_config;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.java_config.config.Config;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.others.DynamicHitter;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.others.Hitter;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.printer.PrinterService;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.xml_config.AutoWiringDI;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.xml_config.ConstructorDI;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.xml_config.FactoryMethodInstantiation;
import nl.infosupport.javaminor.blok1.week4.spring.springlabs.xml_config.SetterDI;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDependencyInjectionTest {

  @Test
  public void initTest() {
    ApplicationContext applicationContext = new
        AnnotationConfigApplicationContext(Config.class);

    PrinterService springService = applicationContext.getBean(PrinterService.class);
    springService.print("Holla");
  }

  @Test
  public void setterInjectionShouldPrintHello() {
    ApplicationContext applicationContext = new
        AnnotationConfigApplicationContext(Config.class);

    SetterDI setterDI = applicationContext.getBean(SetterDI.class);
    setterDI.sayHello();
  }

  @Test
  public void constructorInjectionShouldPrintHello() {
    ApplicationContext applicationContext = new
        AnnotationConfigApplicationContext(Config.class);

    ConstructorDI setterDI = applicationContext.getBean(ConstructorDI.class);
    setterDI.sayHello();
  }

  @Test
  public void autowireInjectionShouldPrintHello() {
    ApplicationContext applicationContext = new
        AnnotationConfigApplicationContext(Config.class);

    AutoWiringDI autoWiringDI = applicationContext.getBean(AutoWiringDI.class);
    autoWiringDI.sayHello();
  }

  @Test
  public void factoryMethodInstantiationShouldPrintHello() {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

    FactoryMethodInstantiation factoryMethodInstantiation = applicationContext
        .getBean(FactoryMethodInstantiation.class);
    factoryMethodInstantiation.saySomething();
  }

  // TODO: ask if this is the bedoeling :) (Step 7 - Factory bean)
  // Create List bean by using factory method -> nameFactory.createNameList
  @Test
  public void namedFactoryShouldPrintNames() {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

    List<String> names = applicationContext.getBean(List.class);
    PrinterService printerService = applicationContext.getBean(PrinterService.class);

    names.forEach(printerService::print);
  }

  @Test
  public void prototypeScopeUsedInAllDependencies() {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

    Hitter hitter1 = applicationContext.getBean("hitter1", Hitter.class);
    Hitter hitter2 = applicationContext.getBean("hitter2", Hitter.class);

    hitter1.hit();
    hitter1.hit();
    hitter1.hit();
    hitter2.hit();
    hitter2.hit();

    assertThat(hitter1.getHits(), is(3));
//     assertThat(hitter1.getHits(), is(5)); // Remove the scope of the HitCounter bean
  }

  @Test
  public void newBeanPerMethodCallUsedInAllDependencies() {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

    DynamicHitter hitter3 = applicationContext.getBean("hitter3", DynamicHitter.class);

    hitter3.hit();
    hitter3.hit();
    hitter3.hit();

    assertThat(hitter3.getHits(), is(0));
  }

}
