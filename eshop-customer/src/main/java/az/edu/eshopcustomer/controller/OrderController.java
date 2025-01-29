package az.edu.eshopcustomer.controller;


import az.edu.eshopcustomer.dto.response.RespOrder;
import az.edu.eshopcustomer.dto.response.Response;
import az.edu.eshopcustomer.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
  //  @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping()
    public Response<List<RespOrder>> getList(HttpServletRequest httpServletRequest){
        return orderService.getList(httpServletRequest);
    }

    @GetMapping("/{id}")
    public Response<RespOrder> gerOrder(@PathVariable @NotNull(message = "Id is required") Long id){
        return orderService.getOrder(id);
    }
}
