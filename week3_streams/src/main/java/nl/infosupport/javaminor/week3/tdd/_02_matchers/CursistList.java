package nl.infosupport.javaminor.week3.tdd._02_matchers;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class CursistList {

  @XmlElementWrapper
  private ArrayList<Cursist> cursisten;

  public CursistList() {
  }

  public CursistList(Collection<Cursist> cursisten) {
    this.cursisten = new ArrayList<Cursist>();
    this.cursisten.addAll(cursisten);
  }

  public ArrayList<Cursist> getCursisten() {
    return cursisten;
  }

}
