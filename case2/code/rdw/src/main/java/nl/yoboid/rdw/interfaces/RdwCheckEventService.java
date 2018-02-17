package nl.yoboid.rdw.interfaces;

import java.io.IOException;

public interface RdwCheckEventService {

  void checkResponseAndSend(String json) throws IOException;

}
