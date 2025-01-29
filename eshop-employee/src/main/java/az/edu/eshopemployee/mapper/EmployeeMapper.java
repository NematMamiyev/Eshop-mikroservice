package az.edu.eshopemployee.mapper;

import az.edu.eshopemployee.dto.request.ReqEmployee;
import az.edu.eshopemployee.dto.response.RespEmployee;
import az.edu.eshopemployee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(ReqEmployee reqEmployee);
    RespEmployee toRespEmployee(Employee employee);
    List<RespEmployee> toRespEmployeeList(List<Employee> employeeList);
    void updateEmployeeFromReqEmployee(@MappingTarget Employee employee, ReqEmployee reqEmployee);
}
