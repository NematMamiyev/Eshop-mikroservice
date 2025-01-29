package az.edu.eshopcustomer.client.product;

import az.edu.eshopcustomer.client.product.dto.RespProductDetails;
import az.edu.eshopcustomer.dto.response.Response;
import az.edu.eshopcustomer.exception.EshopException;
import az.edu.eshopcustomer.exception.ExceptionConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class ProductDetailsUtil {

    private final ProductDetailsClient productDetailsClient;

    public RespProductDetails getRespProductDetails(Long productDetailsId) throws EshopException {
        Response<RespProductDetails> response = productDetailsClient.getProductDetails(productDetailsId);
        if (response.getStatus().getCode() !=1){
            throw new EshopException(ExceptionConstants.PRODUCT_SERVICE_EXCEPTION,"The correct response was not returned from product service.");
        }
        if (response.getT()==null){
            throw new EshopException(ExceptionConstants.PRODUCT_DETAILS_NOT_FOUND,"Product not faund");
        }
        return response.getT();
    }
}
