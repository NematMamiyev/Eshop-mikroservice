package az.edu.eshopproduct.dto.request;

import az.edu.eshopproduct.enums.Currency;
import az.edu.eshopproduct.validation.ColorActive;
import az.edu.eshopproduct.validation.SizeActive;
import az.edu.eshopproduct.validation.ValidCurrency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReqProductDetailsForProduct {
    @NotNull(message = "Size id is required")
    @SizeActive
    private Long sizeId;
    @NotNull(message = "Color id is required")
    @ColorActive
    private Long colorId;
    @NotNull(message = "Price is required")
    @Positive(message ="Price must be positive")
    private BigDecimal price;
    @NotNull(message = "Currency is required")
    @ValidCurrency
    private Currency currency;
    @NotNull(message = "stock is required")
    @Positive(message ="Stock must be positive")
    private Integer stock;
}
