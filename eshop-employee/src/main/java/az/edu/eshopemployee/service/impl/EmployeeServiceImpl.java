package az.edu.eshopemployee.service.impl;

import az.edu.eshopemployee.dto.request.ReqEmployee;
import az.edu.eshopemployee.dto.response.RespEmployee;
import az.edu.eshopemployee.dto.response.RespStatus;
import az.edu.eshopemployee.dto.response.Response;
import az.edu.eshopemployee.entity.Employee;
import az.edu.eshopemployee.enums.EnumAvailableStatus;
import az.edu.eshopemployee.exception.EshopException;
import az.edu.eshopemployee.exception.ExceptionConstants;
import az.edu.eshopemployee.mapper.EmployeeMapper;
import az.edu.eshopemployee.repository.EmployeeRepository;
import az.edu.eshopemployee.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    //private final JwtGenerator jwtGenerator;

    @Override
    public Response<RespEmployee> addEmployee(ReqEmployee reqEmployee, HttpServletRequest httpServletRequest) {
        Response<RespEmployee> response = new Response<>();
       /* String token = httpServletRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        Long id = jwtGenerator.getId(token);
        Employee userEmployee = employeeRepository.findEmployeeByIdAndActive(id,EnumAvailableStatus.ACTIVE.getValue());
        if ((reqEmployee.getRole() == Role.ADMIN || reqEmployee.getRole() == Role.SUPER_ADMIN ) && userEmployee.getRole() == Role.ADMIN){
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA,"You do not have permission to create employee in this role.");
        }*/
        boolean uniqueEmail = employeeRepository.existsEmployeeByEmailIgnoreCaseAndActive(reqEmployee.getEmail(), EnumAvailableStatus.ACTIVE.getValue());
        boolean uniquePhone = employeeRepository.existsEmployeeByPhoneIgnoreCaseAndActive(reqEmployee.getPhone(), EnumAvailableStatus.ACTIVE.getValue());
        if (uniquePhone || uniqueEmail) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Email or phone number available in the database");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(reqEmployee.getPassword());
        reqEmployee.setPassword(encodedPassword);
        Employee employee = employeeMapper.toEmployee(reqEmployee);
        employeeRepository.save(employee);
        response.setT(employeeMapper.toRespEmployee(employee));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<List<RespEmployee>> getEmployeeList() {
        Response<List<RespEmployee>> response = new Response<>();
        List<Employee> employeeList = employeeRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
        if (employeeList.isEmpty()) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Customer list empty");
        }
        response.setT(employeeMapper.toRespEmployeeList(employeeList));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespEmployee> getEmployeeById(Long id) {
        Response<RespEmployee> response = new Response<>();
        Employee employee = getEmployee(id);
        response.setT(employeeMapper.toRespEmployee(employee));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespEmployee> updateEmployee(Long id, ReqEmployee reqEmployee) {
        Response<RespEmployee> response = new Response<>();
        Employee employee = getEmployee(id);
        boolean uniqueEmail = employeeRepository.existsEmployeeByEmailIgnoreCaseAndActiveAndIdNot(reqEmployee.getEmail(), EnumAvailableStatus.ACTIVE.getValue(), id);
        boolean uniquePhone = employeeRepository.existsEmployeeByPhoneIgnoreCaseAndActiveAndIdNot(reqEmployee.getPhone(), EnumAvailableStatus.ACTIVE.getValue(), id);
        if (uniqueEmail || uniquePhone) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Email or phone number available in the database");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(reqEmployee.getPassword());
        reqEmployee.setPassword(encodedPassword);
        employeeMapper.updateEmployeeFromReqEmployee(employee, reqEmployee);
        employeeRepository.save(employee);
        response.setT(employeeMapper.toRespEmployee(employee));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public RespStatus deleteEmployee(Long id) {
        Employee employee = getEmployee(id);
        employee.setActive(EnumAvailableStatus.DEACTIVATED.getValue());
        employeeRepository.save(employee);
        return RespStatus.getSuccessMessage();
    }

    private Employee getEmployee(Long id) {
        Employee employee = employeeRepository.findEmployeeByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue());
        if (employee == null) {
            throw new EshopException(ExceptionConstants.EMPLOYEE_NOT_FOUND, "Employee not found");
        }
        return employee;
    }
}
