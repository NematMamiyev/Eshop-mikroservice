package az.edu.eshopwarehouse.service.impl;

import az.edu.eshopwarehouse.dto.request.ReqWarehouse;
import az.edu.eshopwarehouse.dto.response.RespStatus;
import az.edu.eshopwarehouse.dto.response.RespWarehouse;
import az.edu.eshopwarehouse.dto.response.Response;
import az.edu.eshopwarehouse.entity.Warehouse;
import az.edu.eshopwarehouse.enums.EnumAvailableStatus;
import az.edu.eshopwarehouse.exception.EshopException;
import az.edu.eshopwarehouse.exception.ExceptionConstants;
import az.edu.eshopwarehouse.mapper.WarehouseMapper;
import az.edu.eshopwarehouse.repository.WarehouseRepository;
import az.edu.eshopwarehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;


    @Override
    public Response<RespWarehouse> addWarehouse(ReqWarehouse reqWarehouse) {
        Response<RespWarehouse> response = new Response<>();
        boolean uniqueName = warehouseRepository.existsWarehouseByNameAndActive(reqWarehouse.getName(), EnumAvailableStatus.ACTIVE.getValue());
        if (uniqueName) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Name available in the database");
        }
        Warehouse warehouse = warehouseMapper.toWarehouse(reqWarehouse);
        warehouseRepository.save(warehouse);
        response.setT(warehouseMapper.toRespWarehouse(warehouse));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<List<RespWarehouse>> getWarehouseList() {
        Response<List<RespWarehouse>> response = new Response<>();
        List<Warehouse> warehouseList = warehouseRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
        if (warehouseList.isEmpty()) {
            throw new EshopException(ExceptionConstants.WAREHOUSE_NOT_FOUND, "Warehouse not found");
        }
        response.setT(warehouseMapper.toRespWarehouseList(warehouseList));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespWarehouse> getWarehouseById(Long id) {
        Response<RespWarehouse> response = new Response<>();
        Warehouse warehouse = getWarehouse(id);
        response.setT(warehouseMapper.toRespWarehouse(warehouse));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespWarehouse> updateWarehouse(Long id, ReqWarehouse reqWarehouse) {
        Response<RespWarehouse> response = new Response<>();
        boolean uniqueName = warehouseRepository.existsWarehouseByNameAndActiveAndIdNot(reqWarehouse.getName(), EnumAvailableStatus.ACTIVE.getValue(), id);
        if (uniqueName) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Name available in the database");
        }
        Warehouse warehouse = getWarehouse(id);
        warehouseMapper.updateWarehouseFromReqWarehouse(warehouse, reqWarehouse);
        warehouseRepository.save(warehouse);
        response.setT(warehouseMapper.toRespWarehouse(warehouse));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public RespStatus deleteWarehouse(Long id) {
        Warehouse warehouse = getWarehouse(id);
        warehouse.setActive(EnumAvailableStatus.DEACTIVATED.getValue());
        warehouseRepository.save(warehouse);
        return RespStatus.getSuccessMessage();
    }

    private Warehouse getWarehouse(Long id) {
        Warehouse warehouse = warehouseRepository.findWarehouseByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue());
        if (warehouse == null) {
            throw new EshopException(ExceptionConstants.WAREHOUSE_NOT_FOUND, "Warehouse not found");
        }
        return warehouse;
    }
}
