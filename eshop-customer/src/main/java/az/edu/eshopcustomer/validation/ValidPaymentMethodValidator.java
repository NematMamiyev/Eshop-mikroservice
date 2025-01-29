package az.edu.eshopcustomer.validation;

import az.edu.eshopcustomer.enums.PaymentMethod;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPaymentMethodValidator implements ConstraintValidator<ValidPaymentMethod, PaymentMethod> {
    @Override
    public void initialize(ValidPaymentMethod constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(PaymentMethod paymentMethod, ConstraintValidatorContext constraintValidatorContext) {
        return PaymentMethod.isValidPaymentMethod(paymentMethod.getValue());
    }
}
