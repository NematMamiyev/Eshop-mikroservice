package az.edu.eshopproduct.controller;


import az.edu.eshopproduct.dto.request.ReqColor;
import az.edu.eshopproduct.dto.response.RespColor;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;
import az.edu.eshopproduct.service.ColorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/colors")
public class ColorController {

    private final ColorService colorService;

    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @PostMapping
    public Response<RespColor> addColor(@RequestBody @Valid ReqColor reqColor){
        return colorService.addColor(reqColor);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Response<List<RespColor>> colorList(){
        return colorService.colorList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<RespColor> getColorById(@PathVariable @NotNull(message = "Id is required") Long id){
        return colorService.getColorById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Response<RespColor> updateColor(@PathVariable @NotNull(message = "Id is required") Long id,@RequestBody @Valid ReqColor reqColor){
        return colorService.updateColor(id, reqColor);
    }

    @ResponseStatus(HttpStatus.OK)
  //  @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public RespStatus deleteColor(@PathVariable @NotNull(message = "Id is required") Long id){
        return colorService.deleteColor(id);
    }
}
