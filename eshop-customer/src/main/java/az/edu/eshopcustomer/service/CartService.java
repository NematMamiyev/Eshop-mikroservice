package az.edu.eshopcustomer.service;


import az.edu.eshopcustomer.dto.response.RespCart;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.dto.response.Response;
import jakarta.servlet.http.HttpServletRequest;

public interface CartService {

    RespStatus addCart(Long productDetailsId, HttpServletRequest httpServletRequest);

    Response<RespCart> listByCustomerId(HttpServletRequest httpServletRequest);


    RespStatus deleteCart(Long productDetailsId, HttpServletRequest httpServletRequest);
}
