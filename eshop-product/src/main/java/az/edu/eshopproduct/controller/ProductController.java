package az.edu.eshopproduct.controller;


import az.edu.eshopproduct.dto.request.ReqProduct;
import az.edu.eshopproduct.dto.request.ReqProductAndProductDetailsList;
import az.edu.eshopproduct.dto.response.RespProduct;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;
import az.edu.eshopproduct.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @PostMapping
    public Response<RespProduct> addProduct(@RequestBody @Valid ReqProductAndProductDetailsList reqProductAndProductDetailsList){
        return productService.addProduct(reqProductAndProductDetailsList.getReqProduct(),reqProductAndProductDetailsList.getReqProductDetailsForProducts());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Response<List<RespProduct>> getProductList(){
        return productService.getProductList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<RespProduct> getProductById(@PathVariable @NotNull(message = "Id is required") Long id){
        return productService.getProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @PutMapping("/{id}")
    public Response<RespProduct> updateProduct(@PathVariable @NotNull(message = "Id is required") Long id,@RequestBody @Valid ReqProduct reqProduct){
        return productService.updateProduct(id, reqProduct);
    }

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public RespStatus deleteProduct(@PathVariable @NotNull(message = "Id is required") Long id){
        return productService.deleteProduct(id);
    }

}
