package az.edu.eshopproduct.validation;

import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.repository.BrandRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandActiveValidator implements ConstraintValidator<BrandActive,Long> {

    private final BrandRepository brandRepository;

    @Override
    public void initialize(BrandActive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return brandRepository.existsBrandByIdAndActive(aLong, EnumAvailableStatus.ACTIVE.getValue());
    }
}
