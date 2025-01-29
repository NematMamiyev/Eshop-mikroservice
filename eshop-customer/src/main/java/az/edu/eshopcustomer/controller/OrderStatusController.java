package az.edu.eshopcustomer.controller;


import az.edu.eshopcustomer.dto.response.RespOrderStatus;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.dto.response.Response;
import az.edu.eshopcustomer.enums.Status;
import az.edu.eshopcustomer.service.OrderStatusService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orderstatus")
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    @ResponseStatus(HttpStatus.OK)
  //  @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/{orderId}")
    public Response<List<RespOrderStatus>> getOrderStatusList(@PathVariable @NotNull(message = "Id is required") Long orderId){
        return orderStatusService.getOrderStatusList(orderId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/{status}")
    public RespStatus addOrderStatus(@PathVariable Long id, @PathVariable Status status){
        return orderStatusService.addOrderStatus(id,status);
    }
}
