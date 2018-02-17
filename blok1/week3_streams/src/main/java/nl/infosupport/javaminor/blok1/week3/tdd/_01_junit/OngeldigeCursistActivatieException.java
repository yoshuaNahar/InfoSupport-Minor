package nl.infosupport.javaminor.blok1.week3.tdd._01_junit;

public class OngeldigeCursistActivatieException extends Exception {
  private static final long serialVersionUID = 1L;

  public OngeldigeCursistActivatieException() {
  }

  public OngeldigeCursistActivatieException(String message) {
    super(message);
  }

  public OngeldigeCursistActivatieException(String message, Throwable cause) {
    super(message, cause);
  }

  public OngeldigeCursistActivatieException(Throwable cause) {
    super(cause);
  }
}
