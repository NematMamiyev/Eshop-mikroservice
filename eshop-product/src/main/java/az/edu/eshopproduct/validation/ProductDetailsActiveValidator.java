package az.edu.eshopproduct.validation;

import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.repository.ProductDetailsRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductDetailsActiveValidator  implements ConstraintValidator<ProductDetailsActive,Long> {

    private final ProductDetailsRepository productDetailsRepository;

    @Override
    public void initialize(ProductDetailsActive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return productDetailsRepository.existsProductDetailsByIdAndActive(aLong, EnumAvailableStatus.ACTIVE.getValue());
    }
}
