package az.edu.eshopcustomer.dto.response;


import az.edu.eshopcustomer.client.product.dto.RespProductDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespCart {
    private List<CartProductDetails> cartProductDetailsList;
    private BigDecimal amount;
}
