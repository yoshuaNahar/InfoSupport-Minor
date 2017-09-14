package nl.infosupport.javaminor.week3.tdd._04_testdata;

public class OngeldigeCursistRegistratie extends Exception {
  private static final long serialVersionUID = 1L;

  public OngeldigeCursistRegistratie() {
  }

  public OngeldigeCursistRegistratie(String message) {
    super(message);
  }

  public OngeldigeCursistRegistratie(String message, Throwable cause) {
    super(message, cause);
  }

  public OngeldigeCursistRegistratie(Throwable cause) {
    super(cause);
  }
}
