package az.edu.eshopcustomer.mapper;

import az.edu.eshopcustomer.dto.response.RespOrder;
import az.edu.eshopcustomer.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

   List<RespOrder> toRespOrderList(List<Order> orderList);

   @Mapping(source = "customer",target = "respCustomer")
   @Mapping(source = "orderStatusList",target = "respOrderStatusList")
   RespOrder toRespOrder(Order order);
}
