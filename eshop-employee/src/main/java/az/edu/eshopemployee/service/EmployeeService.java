package az.edu.eshopemployee.service;

import az.edu.eshopemployee.dto.request.ReqEmployee;
import az.edu.eshopemployee.dto.response.RespEmployee;
import az.edu.eshopemployee.dto.response.RespStatus;
import az.edu.eshopemployee.dto.response.Response;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface EmployeeService {
    Response<RespEmployee> addEmployee(ReqEmployee reqEmployee, HttpServletRequest httpServletRequest);

    Response<List<RespEmployee>> getEmployeeList();

    Response<RespEmployee> getEmployeeById(Long id);

    Response<RespEmployee> updateEmployee(Long id, ReqEmployee reqEmployee);

    RespStatus deleteEmployee(Long id);
}
