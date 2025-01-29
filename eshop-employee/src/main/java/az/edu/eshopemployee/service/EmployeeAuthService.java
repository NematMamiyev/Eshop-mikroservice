package az.edu.eshopemployee.service;

import az.edu.eshopemployee.dto.request.LoginRequest;
import az.edu.eshopemployee.dto.response.Response;

public interface EmployeeAuthService {
    Response<String> login(LoginRequest loginRequest);
    Response<String> logout(String token);
}
