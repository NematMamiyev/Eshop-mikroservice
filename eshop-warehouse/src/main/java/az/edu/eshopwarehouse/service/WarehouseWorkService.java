package az.edu.eshopwarehouse.service;

import az.edu.eshopwarehouse.dto.response.RespStatus;
import az.edu.eshopwarehouse.dto.response.RespWareHouseWork;
import az.edu.eshopwarehouse.dto.response.Response;

import java.util.List;

public interface WarehouseWorkService {
    Response<List<RespWareHouseWork>> works();

    Response<RespWareHouseWork> handleWork(Long id);

    RespStatus addWork(Long id);
}
