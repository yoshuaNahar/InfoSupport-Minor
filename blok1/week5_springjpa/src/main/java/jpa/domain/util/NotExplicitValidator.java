package jpa.domain.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotExplicitValidator implements ConstraintValidator<NotExplicit, String> {

  private String[] filter;

  @Override
  public void initialize(NotExplicit notExplicit) {
    filter = notExplicit.filter();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (s == null) {
      return true;
    }

    for (String s1 : filter) {
      if (s.contains(s1)) {
        return false;
      }
    }

    return true;
  }

}
