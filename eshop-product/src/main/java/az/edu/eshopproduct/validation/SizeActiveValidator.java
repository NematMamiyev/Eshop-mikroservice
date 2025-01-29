package az.edu.eshopproduct.validation;

import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.repository.SizeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SizeActiveValidator implements ConstraintValidator<SizeActive, Long> {

    private final SizeRepository sizeRepository;

    @Override
    public void initialize(SizeActive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return sizeRepository.existsSizeByIdAndActive(aLong, EnumAvailableStatus.ACTIVE.getValue());
    }
}
