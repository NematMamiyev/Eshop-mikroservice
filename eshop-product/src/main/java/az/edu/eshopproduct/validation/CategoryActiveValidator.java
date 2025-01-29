package az.edu.eshopproduct.validation;

import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.repository.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryActiveValidator implements ConstraintValidator<CategoryActive,Long> {
    private final CategoryRepository categoryRepository;

    @Override
    public void initialize(CategoryActive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return categoryRepository.existsCategoryByIdAndActive(aLong, EnumAvailableStatus.ACTIVE.getValue());
    }
}
