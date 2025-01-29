package az.edu.eshopcustomer.dto.response;

import az.edu.eshopcustomer.client.product.dto.RespColor;
import az.edu.eshopcustomer.client.product.dto.RespProductDetails;
import az.edu.eshopcustomer.client.product.dto.RespProductForProductDetails;
import az.edu.eshopcustomer.client.product.dto.RespSize;
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
public class CartProductDetails {
    private RespProductDetails respProductDetails;
    private Integer quantity;
}
