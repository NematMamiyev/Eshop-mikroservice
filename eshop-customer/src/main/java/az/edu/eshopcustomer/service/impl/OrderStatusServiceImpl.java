package az.edu.eshopcustomer.service.impl;

import az.edu.eshopcustomer.dto.response.RespOrderStatus;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.dto.response.Response;
import az.edu.eshopcustomer.entity.Order;
import az.edu.eshopcustomer.entity.OrderStatus;
import az.edu.eshopcustomer.enums.EnumAvailableStatus;
import az.edu.eshopcustomer.enums.Status;
import az.edu.eshopcustomer.exception.EshopException;
import az.edu.eshopcustomer.exception.ExceptionConstants;
import az.edu.eshopcustomer.mapper.OrderStatusMapper;
import az.edu.eshopcustomer.repository.OrderRepository;
import az.edu.eshopcustomer.repository.OrderStatusRepository;
import az.edu.eshopcustomer.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusMapper orderStatusMapper;

    @Override
    public Response<List<RespOrderStatus>> getOrderStatusList(Long orderId) {
        Response<List<RespOrderStatus>> response = new Response<>();
        Order order = orderRepository.findOrderByIdAndActive(orderId, EnumAvailableStatus.ACTIVE.getValue());
        if (order == null) {
            throw new EshopException(ExceptionConstants.ORDER_NOT_FOUND, "Order not found");
        }
        List<OrderStatus> orderStatusList = orderStatusRepository.findOrderStatusByOrderIdAndActive(orderId, EnumAvailableStatus.ACTIVE.getValue());
        response.setT(orderStatusMapper.toRespOrderStatusList(orderStatusList));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public RespStatus addOrderStatus(Long id, Status status) {
        OrderStatus orderStatus = OrderStatus.builder()
                .order(orderRepository.findOrderByIdAndActive(id,EnumAvailableStatus.ACTIVE.getValue()))
                .status(status)
                .build();
        orderStatusRepository.save(orderStatus);
        return RespStatus.getSuccessMessage();
    }
}
