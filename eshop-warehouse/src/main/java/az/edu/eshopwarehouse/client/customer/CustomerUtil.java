package az.edu.eshopwarehouse.client.customer;

import az.edu.eshopwarehouse.dto.response.Response;
import az.edu.eshopwarehouse.exception.EshopException;
import az.edu.eshopwarehouse.exception.ExceptionConstants;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerUtil {

    private  final CustomerClient customerClient;

    @Named("mapOrder")
    public RespOrder getRespOrder(Long orderId){
        Response<RespOrder> response = customerClient.gerOrder(orderId);
        if (response.getStatus().getCode() !=1){
            throw new EshopException(ExceptionConstants.CUSTOMER_SERVICE_EXCEPTION,"The correct response was not returned from product service.");
        }
        if (response.getT()==null){
            throw new EshopException(ExceptionConstants.PRODUCT_DETAILS_NOT_FOUND,"Product not faund");
        }
        return response.getT();
    }
}
