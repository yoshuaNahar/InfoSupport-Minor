import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stack {

  private static String r = "My int is a horse float";

  public static void main(String[] args) {
    String key = "(int|float|if|else|double)"; // is a regex to check if one of the words exist
    Pattern pattern = Pattern.compile(key);
    Matcher matcher = pattern.matcher(r);
    while (matcher.find()) { // Checks if the matcher matches r.
      System.out.println(matcher.group()); // return all the words which matched
    }
  }

}
