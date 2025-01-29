package az.edu.eshopwarehouse.mapper;

import az.edu.eshopwarehouse.client.customer.CustomerUtil;
import az.edu.eshopwarehouse.dto.response.RespWareHouseWork;
import az.edu.eshopwarehouse.entity.WarehouseWork;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CustomerUtil.class})
public interface WarehouseWorkMapper {

    @Mapping(source = "orderId",target = "respOrder",qualifiedByName = "mapOrder")
    RespWareHouseWork toRespWarehouseWork(WarehouseWork warehouseWork);

    List<RespWareHouseWork> toRespWarehouseWorkList(List<WarehouseWork> warehouseWorkList);
}
