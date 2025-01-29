package az.edu.eshopcustomer.client.product;

import az.edu.eshopcustomer.client.product.dto.ReqProductDetails;
import az.edu.eshopcustomer.client.product.dto.RespProductDetails;
import az.edu.eshopcustomer.dto.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "eshop-product")
public interface ProductDetailsClient {

    @GetMapping("productdetails/{id}")
    Response<RespProductDetails> getProductDetails(@PathVariable Long id);

    @PutMapping("productdetails/{id}")
    Response<RespProductDetails> updateProductDetails(@PathVariable Long id, @RequestBody ReqProductDetails reqProductDetails);

}