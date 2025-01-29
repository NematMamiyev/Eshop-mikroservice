/*
package az.edu.eshopemployee.controller;


import az.edu.eshopemployee.dto.request.LoginRequest;
import az.edu.eshopemployee.dto.response.Response;
import az.edu.eshopemployee.service.EmployeeAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/employee")
@RequiredArgsConstructor
public class EmployeeAuthController {

    private final EmployeeAuthService employeeAuthService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public Response<String> login(@RequestBody @Valid LoginRequest loginRequest){
       return employeeAuthService.login(loginRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/logout")
    public Response<String> logout(@RequestHeader("Authorization") String token) {
       return employeeAuthService.logout(token);
    }
}
*/
