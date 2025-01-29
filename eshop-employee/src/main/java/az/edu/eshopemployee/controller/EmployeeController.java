package az.edu.eshopemployee.controller;

import az.edu.eshopemployee.dto.request.ReqEmployee;
import az.edu.eshopemployee.dto.response.RespEmployee;
import az.edu.eshopemployee.dto.response.RespStatus;
import az.edu.eshopemployee.dto.response.Response;
import az.edu.eshopemployee.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @PostMapping
    public Response<RespEmployee> addEmployee(@RequestBody @Valid ReqEmployee reqEmployee, HttpServletRequest httpServletRequest){
        return employeeService.addEmployee(reqEmployee, httpServletRequest);
    }
    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @GetMapping
    public Response<List<RespEmployee>> getEmployeeList(){
        return employeeService.getEmployeeList();
    }

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public Response<RespEmployee> getEmployeeById(@PathVariable @NotNull(message = "Id is required") Long id){
        return employeeService.getEmployeeById(id);
    }

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Response<RespEmployee> updateEmployee(@PathVariable @NotNull(message = "Id is required") Long id, @RequestBody @Valid ReqEmployee reqEmployee){
        return employeeService.updateEmployee(id,reqEmployee);
    }

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public RespStatus deleteEmployee(@PathVariable @NotNull(message = "Id is required") Long id){
        return employeeService.deleteEmployee(id);
    }
}
