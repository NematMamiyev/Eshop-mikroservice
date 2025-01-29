package az.edu.eshopproduct.validation;

import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductActiveValidator implements ConstraintValidator<ProductActive,Long> {

    private final ProductRepository productRepository;

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return productRepository.existsProductByIdAndActive(aLong, EnumAvailableStatus.ACTIVE.getValue());
    }

    @Override
    public void initialize(ProductActive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
