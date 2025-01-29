package az.edu.eshopcustomer.service;


import az.edu.eshopcustomer.dto.response.RespOrder;
import az.edu.eshopcustomer.dto.response.Response;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface OrderService {
    Response<List<RespOrder>> getList(HttpServletRequest httpServletRequest);

    Response<RespOrder> getOrder(Long id);
}
