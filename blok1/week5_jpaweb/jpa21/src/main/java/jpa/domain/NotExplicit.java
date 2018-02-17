package jpa.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotExplicitValidator.class)
public @interface NotExplicit {

  String[] filter() default {};

  String message() default "You really shouldn't say that!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
