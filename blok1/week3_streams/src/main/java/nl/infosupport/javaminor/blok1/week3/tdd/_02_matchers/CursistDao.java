package nl.infosupport.javaminor.blok1.week3.tdd._02_matchers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class CursistDao {

  private Map<String, Cursist> cursisten = new HashMap<String, Cursist>();
  private File datafile;

  private CursistDao() {
  }

  public CursistDao(File file) {
    this.datafile = file;
  }

  public static CursistDao getInstance() {
    return new CursistDao();
  }

  public boolean isGeregistreerd(String gebruikersnaam) {
    return cursisten.containsKey(gebruikersnaam);
  }

  public void save(Cursist cursist) {
    cursisten.put(cursist.getGebruikersnaam(), cursist);
  }

  public Cursist getCursist(String gebruikersnaam) {
    return cursisten.get(gebruikersnaam);
  }

  @SuppressWarnings("unchecked")
  public void load() {
    Objects.requireNonNull(this.datafile, "Datafile mag niet null zijn");
    try {
      JAXBContext context = JAXBContext.newInstance(CursistList.class,
          Cursist.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      Object object = unmarshaller.unmarshal(datafile);
      if (object instanceof CursistList) {
        cursisten.clear();
        for (Cursist cursist : ((CursistList) object).getCursisten()) {
          cursisten.put(cursist.getGebruikersnaam(), cursist);
        }
      } else {
        throw new IllegalArgumentException("File: "
            + datafile.getAbsolutePath() + " bevat geen cursisten");
      }
    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }

  public void close() {
    Objects.requireNonNull(this.datafile, "Datafile mag niet null zijn");
    try {
      JAXBContext context = JAXBContext.newInstance(CursistList.class,
          Cursist.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.marshal(new CursistList(cursisten.values()), datafile);
    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }
}
