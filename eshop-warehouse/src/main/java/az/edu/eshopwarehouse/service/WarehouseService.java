package az.edu.eshopwarehouse.service;

import az.edu.eshopwarehouse.dto.request.ReqWarehouse;
import az.edu.eshopwarehouse.dto.response.RespStatus;
import az.edu.eshopwarehouse.dto.response.RespWarehouse;
import az.edu.eshopwarehouse.dto.response.Response;

import java.util.List;

public interface WarehouseService {
    Response<RespWarehouse> addWarehouse(ReqWarehouse reqWarehouse);

    Response<List<RespWarehouse>> getWarehouseList();

    Response<RespWarehouse> getWarehouseById(Long id);

    Response<RespWarehouse> updateWarehouse(Long id, ReqWarehouse reqWarehouse);

    RespStatus deleteWarehouse(Long id);
}
