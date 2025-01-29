package az.edu.eshopcustomer.service;



import az.edu.eshopcustomer.dto.response.RespOrderStatus;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.dto.response.Response;
import az.edu.eshopcustomer.enums.Status;

import java.util.List;

public interface OrderStatusService {
    Response<List<RespOrderStatus>> getOrderStatusList(Long orderId);

    RespStatus addOrderStatus(Long id, Status status);
}
