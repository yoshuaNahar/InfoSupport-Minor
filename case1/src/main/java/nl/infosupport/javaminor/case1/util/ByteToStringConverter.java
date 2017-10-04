package nl.infosupport.javaminor.case1.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ByteToStringConverter {

  private static final Logger LOG = LoggerFactory.getLogger(ByteToStringConverter.class);

  public String convertByteArrayToString(byte[] bytes) {
    if (bytes == null) {
      LOG.debug("byte array is null");
      return "";
    }

    String string = new String(bytes);
    LOG.info("Parsing bytes to string => {}", string);

    return string;
  }

}
