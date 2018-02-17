package nl.infosupport.moj.templatebuilder;

public class DukemailValidator {

  public boolean isValid(String dukemmailAddress) {
    String front = null;
    String back = null;
    String[] backBoth;

    try {
      String dukeMail[] = dukemmailAddress.split("@");
      front = dukeMail[0];
      back = dukeMail[1];
      backBoth = back.split(".");
      if (dukeMail.length > 2) {
        return false;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    }


      if (front.charAt(0) == '.') {
      return false;
    }

    if (front.charAt(front.length() - 1) == '.') {
      return false;
    }


    if (backBoth.length != 2) {
      return false;
    }

    if (!backBoth[1].endsWith(".dukemail")) {
      return false;
    }



    return true;
  }

}
