package az.edu.eshopcustomer.validation;


import az.edu.eshopcustomer.enums.EnumAvailableStatus;
import az.edu.eshopcustomer.repository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerActiveValidator implements ConstraintValidator<CustomerActive,Long> {

    private final CustomerRepository customerRepository;

    @Override
    public void initialize(CustomerActive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return customerRepository.existsCustomerByIdAndActive(aLong, EnumAvailableStatus.ACTIVE.getValue());
    }
}
