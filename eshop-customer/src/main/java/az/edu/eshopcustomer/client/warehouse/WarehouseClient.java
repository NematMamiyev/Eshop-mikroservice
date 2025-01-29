package az.edu.eshopcustomer.client.warehouse;

import az.edu.eshopcustomer.dto.response.RespStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "eshop-warehouse")
public interface WarehouseClient {

    @PostMapping("warehouseworks/{id}")
    RespStatus addWork(@PathVariable Long id);
}
