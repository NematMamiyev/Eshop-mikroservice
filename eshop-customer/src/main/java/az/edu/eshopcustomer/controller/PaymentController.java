package az.edu.eshopcustomer.controller;


import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.enums.PaymentMethod;
import az.edu.eshopcustomer.service.PaymentService;
import az.edu.eshopcustomer.validation.ValidPaymentMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("payment")
public class PaymentController {

    private final PaymentService paymentService;

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("{paymentMethod}")
    public RespStatus payment(@PathVariable @NotNull(message = "Payment method is required")
                              @ValidPaymentMethod PaymentMethod paymentMethod, HttpServletRequest httpServletRequest) {
        return paymentService.payment(paymentMethod, httpServletRequest);
    }
}
