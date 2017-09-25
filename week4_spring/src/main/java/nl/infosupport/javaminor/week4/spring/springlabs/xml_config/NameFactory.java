package nl.infosupport.javaminor.week4.spring.springlabs.xml_config;

import java.util.ArrayList;
import java.util.List;

public class NameFactory {

  public List<String> createNameList() {
    List<String> names = new ArrayList<>();
    names.add("JSON");
    names.add("Names");
    return names;
  }

}
