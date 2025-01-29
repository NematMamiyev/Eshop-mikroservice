package az.edu.eshopwarehouse.controller;

import az.edu.eshopwarehouse.dto.response.RespStatus;
import az.edu.eshopwarehouse.dto.response.RespWareHouseWork;
import az.edu.eshopwarehouse.dto.response.Response;
import az.edu.eshopwarehouse.service.WarehouseWorkService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouseworks")
public class WarehouseWorkController {

    private final WarehouseWorkService warehouseWorkService;

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @GetMapping
    public Response<List<RespWareHouseWork>> works(){
        return warehouseWorkService.works();
    }

    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @PutMapping("/handlework/{id}")
    public Response<RespWareHouseWork> handleWork(@PathVariable @NotNull(message = "Id is required") Long id){
        return warehouseWorkService.handleWork(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}")
    public RespStatus addWork(@PathVariable @NotNull(message = "Id is required") Long id){
       return warehouseWorkService.addWork(id);
    }
}
