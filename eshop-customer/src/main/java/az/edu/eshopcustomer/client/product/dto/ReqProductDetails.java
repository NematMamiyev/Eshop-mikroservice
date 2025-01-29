package az.edu.eshopcustomer.client.product.dto;


import az.edu.eshopcustomer.enums.Currency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ReqProductDetails {
    private Long productId;
    private Long sizeId;
    private Long colorId;
    private BigDecimal price;
    private Currency currency;
    private Integer stock;
    private boolean outOfStock;
}
