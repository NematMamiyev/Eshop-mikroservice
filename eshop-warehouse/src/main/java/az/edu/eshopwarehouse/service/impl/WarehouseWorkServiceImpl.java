package az.edu.eshopwarehouse.service.impl;

import az.edu.eshopwarehouse.client.customer.CustomerClient;
import az.edu.eshopwarehouse.client.customer.CustomerUtil;
import az.edu.eshopwarehouse.client.customer.RespOrder;
import az.edu.eshopwarehouse.client.notification.EmailDto;
import az.edu.eshopwarehouse.client.notification.NotificationClient;
import az.edu.eshopwarehouse.dto.response.RespStatus;
import az.edu.eshopwarehouse.dto.response.RespWareHouseWork;
import az.edu.eshopwarehouse.dto.response.Response;
import az.edu.eshopwarehouse.entity.WarehouseWork;
import az.edu.eshopwarehouse.enums.Email;
import az.edu.eshopwarehouse.enums.EnumAvailableStatus;
import az.edu.eshopwarehouse.enums.Status;
import az.edu.eshopwarehouse.exception.EshopException;
import az.edu.eshopwarehouse.exception.ExceptionConstants;
import az.edu.eshopwarehouse.mapper.WarehouseWorkMapper;
import az.edu.eshopwarehouse.repository.WarehouseWorkRepository;
import az.edu.eshopwarehouse.service.WarehouseWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseWorkServiceImpl implements WarehouseWorkService {

    private final WarehouseWorkRepository warehouseWorkRepository;
    private final WarehouseWorkMapper warehouseWorkMapper;
    private final CustomerClient customerClient;
    private final CustomerUtil customerUtil;
    private final NotificationClient notificationClient;

    @Override
    public Response<List<RespWareHouseWork>> works() {
        Response<List<RespWareHouseWork>> response = new Response<>();
        List<WarehouseWork> warehouseWorkList = warehouseWorkRepository.findALLByActive(EnumAvailableStatus.ACTIVE.getValue());
        if (warehouseWorkList.isEmpty()) {
            throw new EshopException(ExceptionConstants.WAREHOUSE_WORK_NOT_FOUND, "Warehouse work not found");
        }
        List<RespWareHouseWork> respWareHouseWorkList = warehouseWorkMapper.toRespWarehouseWorkList(warehouseWorkList);
        response.setT(respWareHouseWorkList);
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespWareHouseWork> handleWork(Long id) {
        Response<RespWareHouseWork> response = new Response<>();
        if (id == null) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Id is null");
        }
        WarehouseWork warehouseWork = warehouseWorkRepository.findWarehouseWorkByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue());
        if (warehouseWork == null) {
            throw new EshopException(ExceptionConstants.WAREHOUSE_WORK_NOT_FOUND, "Warehouse work not found");
        }
        warehouseWork.setActive(EnumAvailableStatus.DEACTIVATED.getValue());
        customerClient.addOrderStatus(warehouseWork.getOrderId(), Status.CONFIRMED);
        RespOrder respOrder = customerUtil.getRespOrder(warehouseWork.getOrderId());
        RespWareHouseWork respWareHouseWork = warehouseWorkMapper.toRespWarehouseWork(warehouseWork);
        response.setT(respWareHouseWork);
        response.setStatus(RespStatus.getSuccessMessage());
        warehouseWork.setActive(EnumAvailableStatus.DEACTIVATED.getValue());
        EmailDto emailDto = EmailDto.builder()
                .to(respOrder.getRespCustomer().getEmail())
                .subject(Email.CONFIRMED.toString())
                .body(Email.CONFIRMED.getDescription())
                .build();
        //notificationClient.sendEmail(emailDto);
        warehouseWork.setActive(EnumAvailableStatus.DEACTIVATED.getValue());
        warehouseWorkRepository.save(warehouseWork);
        return response;
    }

    @Override
    public RespStatus addWork(Long id) {
        WarehouseWork warehouseWork = WarehouseWork.builder()
                .orderId(id)
                .build();
        warehouseWorkRepository.save(warehouseWork);
        return RespStatus.getSuccessMessage();
    }
}
