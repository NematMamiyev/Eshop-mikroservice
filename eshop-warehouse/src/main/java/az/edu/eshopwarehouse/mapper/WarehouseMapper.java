package az.edu.eshopwarehouse.mapper;

import az.edu.eshopwarehouse.dto.request.ReqWarehouse;
import az.edu.eshopwarehouse.dto.response.RespWarehouse;
import az.edu.eshopwarehouse.entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    Warehouse toWarehouse(ReqWarehouse reqWarehouse);
    RespWarehouse toRespWarehouse(Warehouse warehouse);
    List<RespWarehouse> toRespWarehouseList(List<Warehouse> warehouseList);
    void updateWarehouseFromReqWarehouse(@MappingTarget Warehouse warehouse, ReqWarehouse reqWarehouse);
}