package az.edu.eshopproduct.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SizeActiveValidator.class)
@Target({ElementType.PARAMETER, ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SizeActive {
    String message() default "Size not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
