package az.edu.eshopproduct.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReqProductAndProductDetailsList {
    private ReqProduct reqProduct;
    private List<ReqProductDetailsForProduct> reqProductDetailsForProducts = new ArrayList<>();
}
