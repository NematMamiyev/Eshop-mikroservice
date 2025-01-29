package az.edu.eshopproduct.validation;

import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.repository.ColorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ColorActiveValidator implements ConstraintValidator<ColorActive,Long> {

    private final ColorRepository colorRepository;

    @Override
    public void initialize(ColorActive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return colorRepository.existsColorByIdAndActive(aLong, EnumAvailableStatus.ACTIVE.getValue());
    }
}
