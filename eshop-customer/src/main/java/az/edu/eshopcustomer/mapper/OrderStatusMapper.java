package az.edu.eshopcustomer.mapper;


import az.edu.eshopcustomer.dto.response.RespOrderStatus;
import az.edu.eshopcustomer.entity.OrderStatus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {
   List<RespOrderStatus> toRespOrderStatusList(List<OrderStatus> orderStatusList);
}
