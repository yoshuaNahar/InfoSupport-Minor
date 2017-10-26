package nl.infosupport.javaminor.blok1.week2.tdd.generics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.junit.Test;

public class ExploreInheritance {

  private static void log(Werknemer werknemer) {
    System.out.println(werknemer);
  }

  private static void logAll(Werknemer[] werknemers) {
    for (Werknemer werknemer : werknemers) {
      log(werknemer);
    }
  }

  private static void logAll(List<? extends Werknemer> werknemers) {
    werknemers.forEach(werknemer -> log(werknemer));
  }

  private static void logAll(List<? extends Werknemer> werknemers,
      Consumer<? super Werknemer> consumer) {
    for (Werknemer werknemer : werknemers) {
      consumer.accept(werknemer);
    }
  }

  @Test
  public void createWerknemers() {
    Werknemer vw1 = new VasteWerknemer(1, "John", 12);
    Werknemer tw1 = new TijdelijkeWerknemer(2, "Json", 40, 100);

    System.out.println(vw1);
    System.out.println(tw1);

    System.out.println(vw1.getSalaris());
    System.out.println(tw1.getSalaris());
  }

  @Test
  public void logWerknemers() {
    List<VasteWerknemer> vasteWerknemerList = Arrays.asList(new VasteWerknemer(1, "John", 12));

    logAll(vasteWerknemerList);
  }

  @Test
  public void logWerknemersConsumer() {
    List<VasteWerknemer> vasteWerknemerList = Arrays.asList(new VasteWerknemer(1, "John", 12));

    logAll(vasteWerknemerList, System.out::println);
  }

}
