package az.edu.eshopwarehouse.validation;

import az.edu.eshopwarehouse.enums.EnumAvailableStatus;
import az.edu.eshopwarehouse.repository.WarehouseRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WarehouseActiveValidator  implements ConstraintValidator<WarehouseActive,Long> {
    private final WarehouseRepository warehouseRepository;

    @Override
    public void initialize(WarehouseActive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return warehouseRepository.existsWarehouseByIdAndActive(aLong, EnumAvailableStatus.ACTIVE.getValue());
    }
}
