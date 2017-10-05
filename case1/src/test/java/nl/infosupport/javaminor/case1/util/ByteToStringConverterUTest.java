package nl.infosupport.javaminor.case1.util;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ByteToStringConverterUTest {

  private ByteToStringConverter byteToStringConverter = new ByteToStringConverter();

  @Test
  public void convertByteArrayToStringWithNullByteArrayShouldReturnEmptyString() {
    String convertedString = byteToStringConverter.convertByteArrayToString(null);

    assertThat(convertedString.isEmpty(), is(true));
  }

  @Test
  public void convertByteArrayToStringWithEmptyByteArrayShouldReturnEmptyString() {
    String convertedString = byteToStringConverter.convertByteArrayToString(new byte[0]);

    assertThat(convertedString.isEmpty(), is(true));
  }

  @Test
  public void convertByteArrayToStringWithNonEmptyByteArrayShouldReturnNonEmptyString() {
    String convertedString = byteToStringConverter.convertByteArrayToString(new byte[] {84});

    assertThat(convertedString.isEmpty(), is(false));
  }

}
