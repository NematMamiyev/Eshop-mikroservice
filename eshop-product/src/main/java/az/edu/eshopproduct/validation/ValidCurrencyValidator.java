package az.edu.eshopproduct.validation;


import az.edu.eshopproduct.enums.Currency;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCurrencyValidator implements ConstraintValidator<ValidCurrency, Currency> {
    @Override
    public void initialize(ValidCurrency constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Currency currency, ConstraintValidatorContext constraintValidatorContext) {
        return Currency.isValidCurrency(currency.getValue());
    }
}
