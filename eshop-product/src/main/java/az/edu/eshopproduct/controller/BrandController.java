package az.edu.eshopproduct.controller;


import az.edu.eshopproduct.dto.request.ReqBrand;
import az.edu.eshopproduct.dto.response.RespBrand;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;
import az.edu.eshopproduct.service.BrandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @ResponseStatus(HttpStatus.CREATED)
  //  @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @PostMapping
    public Response<RespBrand> addBrand(@RequestBody @Valid ReqBrand reqBrand){
        return brandService.addBrand(reqBrand);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Response<List<RespBrand>> brandList(){
        return brandService.brandList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<RespBrand> getBrandById(@PathVariable @NotNull(message = "Id is required") Long id){
        return brandService.getBrandById(id);
    }

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Response<RespBrand> updateBrand(@PathVariable @NotNull(message = "Id is required") Long id,@RequestBody @Valid ReqBrand reqBrand){
        return brandService.updateBrand(id, reqBrand);
    }

    @ResponseStatus(HttpStatus.OK)
  //  @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public RespStatus deleteBrand(@PathVariable @NotNull(message = "Id is required") Long id){
        return brandService.deleteBrand(id);
    }
}
