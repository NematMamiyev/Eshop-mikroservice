package az.edu.eshopcustomer.client.product.dto;

import az.edu.eshopcustomer.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespProductDetails {
    private Long id;
    private RespProductForProductDetails respProductForProductDetails;
    private RespSize respSize;
    private boolean outOfStock;
    private RespColor respColor;
    private BigDecimal price;
    private Currency currency;
    private Integer stock;
}
