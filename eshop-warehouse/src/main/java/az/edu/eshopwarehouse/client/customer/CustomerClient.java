package az.edu.eshopwarehouse.client.customer;

import az.edu.eshopwarehouse.dto.response.RespStatus;
import az.edu.eshopwarehouse.dto.response.Response;
import az.edu.eshopwarehouse.enums.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "eshop-customer")
public interface CustomerClient {

    @PostMapping("orderstatus/{id}/{status}")
    RespStatus addOrderStatus(@PathVariable Long id, @PathVariable Status status);

    @GetMapping("orders/{id}")
    Response<RespOrder> gerOrder(@PathVariable Long id);
}
