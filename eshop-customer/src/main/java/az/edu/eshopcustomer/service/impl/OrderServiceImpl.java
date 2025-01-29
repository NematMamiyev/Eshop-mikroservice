package az.edu.eshopcustomer.service.impl;


import az.edu.eshopcustomer.dto.response.RespOrder;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.dto.response.Response;
import az.edu.eshopcustomer.entity.Order;
import az.edu.eshopcustomer.enums.EnumAvailableStatus;
import az.edu.eshopcustomer.exception.EshopException;
import az.edu.eshopcustomer.exception.ExceptionConstants;
import az.edu.eshopcustomer.mapper.OrderMapper;
import az.edu.eshopcustomer.repository.OrderRepository;
import az.edu.eshopcustomer.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
   // private final JwtGenerator jwtGenerator;

    @Override
    public Response<List<RespOrder>> getList(HttpServletRequest httpServletRequest) {
        Response<List<RespOrder>> response = new Response<>();
        /*String token = httpServletRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        List<Order> orders = orderRepository.findOrderByCustomerIdAndActive(jwtGenerator.getId(token), EnumAvailableStatus.ACTIVE.getValue());*/
        List<Order> orders = orderRepository.findOrderByCustomerIdAndActive(1L, EnumAvailableStatus.ACTIVE.getValue());
        if (orders.isEmpty()){
            throw new EshopException(ExceptionConstants.ORDER_NOT_FOUND,"Order not found");
        }
        response.setT(orderMapper.toRespOrderList(orders));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespOrder> getOrder(Long id) {
        Response<RespOrder> response = new Response<>();
        Order order = orderRepository.findOrderByIdAndActive(id,EnumAvailableStatus.ACTIVE.getValue());
        if (order == null){
            throw new EshopException(ExceptionConstants.ORDER_NOT_FOUND,"Order not found");
        }
        response.setT(orderMapper.toRespOrder(order));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }
}
