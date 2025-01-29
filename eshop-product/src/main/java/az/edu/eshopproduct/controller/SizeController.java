package az.edu.eshopproduct.controller;

import az.edu.eshopproduct.dto.request.ReqSize;
import az.edu.eshopproduct.dto.response.RespSize;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;
import az.edu.eshopproduct.service.SizeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/size")
public class SizeController {

    private final SizeService sizeService;

    @ResponseStatus(HttpStatus.CREATED)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @PostMapping
    public Response<RespSize> addSize(@RequestBody @Valid ReqSize reqSize){
        return sizeService.addSize(reqSize);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Response<List<RespSize>> getSizeList(){
        return sizeService.getSizeList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<RespSize> getSizeById(@PathVariable @NotNull(message = "Id is required") Long id){
        return sizeService.getSizeById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @PutMapping("/{id}")
    public Response<RespSize> updateSize(@PathVariable @NotNull(message = "Id is required") Long id,@RequestBody @Valid ReqSize reqSize){
        return sizeService.updateSize(id, reqSize);
    }

    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public RespStatus deleteSize(@PathVariable @NotNull(message = "Id is required") Long id){
        return sizeService.deleteSize(id);
    }
}
