package az.edu.eshopproduct.dto.response;

import az.edu.eshopproduct.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespProductDetailsForProduct {
    private Long id;
    private RespSize respSize;
    private RespColor respColor;
    private boolean outOfStock;
    private BigDecimal price;
    private Currency currency;
    private Integer stock;
}
