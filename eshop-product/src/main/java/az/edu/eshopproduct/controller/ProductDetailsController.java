package az.edu.eshopproduct.controller;

import az.edu.eshopproduct.dto.request.ReqProductDetails;
import az.edu.eshopproduct.dto.request.ReqUpdateProductDetails;
import az.edu.eshopproduct.dto.response.RespProductDetails;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;
import az.edu.eshopproduct.service.ProductDetailsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productdetails")
@RequiredArgsConstructor
public class ProductDetailsController {

    private final ProductDetailsService productDetailsService;

    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @PostMapping
    public Response<RespProductDetails> addProductDetails( @RequestBody @Valid ReqProductDetails reqProductDetails){
        return productDetailsService.addProductDetails(reqProductDetails);
    }

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @PutMapping("/{id}")
    public Response<RespProductDetails> updateProductDetails(@PathVariable @NotNull(message = "Id is required") Long id, @RequestBody @Valid ReqUpdateProductDetails reqUpdateProductDetails){
        return productDetailsService.updateProductDetails(id,reqUpdateProductDetails);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Response<List<RespProductDetails>> getProductDetails(){
        return productDetailsService.getProductDetails();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<RespProductDetails> getProductDetailsById(@PathVariable @NotNull(message = "Id is required") Long id){
        return productDetailsService.getProductDetailsById(id);
    }

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public RespStatus deleteProductDetails(@PathVariable @NotNull(message = "Id is required") Long id){
        return productDetailsService.deleteProductDetails(id);
    }

}
