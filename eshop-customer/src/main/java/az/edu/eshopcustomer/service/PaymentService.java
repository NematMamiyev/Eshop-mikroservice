package az.edu.eshopcustomer.service;


import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.enums.PaymentMethod;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {
    RespStatus payment(PaymentMethod paymentMethod, HttpServletRequest httpServletRequest);
}
