package az.edu.eshopproduct.dto.response;

import az.edu.eshopproduct.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespProductForProductDetails {
    private Long id;
    private String name;
    private String productInformation;
    private Date expertionDate;
    private Gender gender;
    private RespBrand respBrand;
    private RespSubcategory respSubcategory;
}
