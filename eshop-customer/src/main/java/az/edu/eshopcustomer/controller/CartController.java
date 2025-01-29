package az.edu.eshopcustomer.controller;

import az.edu.eshopcustomer.dto.response.RespCart;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.dto.response.Response;
import az.edu.eshopcustomer.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @ResponseStatus(HttpStatus.OK)
    // @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/add/{productDetailsId}")
    public RespStatus addCart(@PathVariable @NotNull(message = "Id is required") Long productDetailsId, HttpServletRequest httpServletRequest ){
        return cartService.addCart(productDetailsId,httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping()
    public Response<RespCart> listByCustomerId(HttpServletRequest httpServletRequest){
        return cartService.listByCustomerId(httpServletRequest);
    }



    @ResponseStatus(HttpStatus.OK)
  //  @PreAuthorize("hasAuthority('CUSTOMER')")
    @DeleteMapping("/delete/{productDetailsId}")
    public RespStatus deleteCart(@PathVariable @NotNull(message = "Id is required")  Long productDetailsId, HttpServletRequest httpServletRequest){
        return cartService.deleteCart(productDetailsId,httpServletRequest);
    }

}
