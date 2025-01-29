/*
package az.edu.eshopcustomer.controller;


import az.edu.eshopcustomer.dto.request.LoginRequest;
import az.edu.eshopcustomer.dto.response.Response;
import az.edu.eshopcustomer.service.CustomerAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/customer")
@RequiredArgsConstructor
public class CustomerAuthController {

    private final CustomerAuthService customerAuthService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public Response<String> login(@RequestBody @Valid LoginRequest loginRequest){
       return customerAuthService.login(loginRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/logout")
    public Response<String> logout(@RequestHeader("Authorization") String token) {
        return customerAuthService.logout(token);
    }
}
*/
