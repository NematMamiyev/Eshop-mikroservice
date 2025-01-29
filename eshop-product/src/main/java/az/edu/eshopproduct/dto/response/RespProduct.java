package az.edu.eshopproduct.dto.response;

import az.edu.eshopproduct.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespProduct {
    private Long id;
    private String name;
    private String productInformation;
    private Date expirationDate;
    private Gender gender;
    private RespBrand respBrand;
    private List<RespProductDetailsForProduct> respProductDetailsForProductList;
    private RespSubcategory respSubcategory;
}
