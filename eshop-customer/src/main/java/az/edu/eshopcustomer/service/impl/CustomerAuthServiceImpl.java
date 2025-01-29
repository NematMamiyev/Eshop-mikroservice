/*
package az.edu.eshopcustomer.service.impl;


import az.edu.eshopcustomer.dto.request.LoginRequest;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.dto.response.Response;
import az.edu.eshopcustomer.entity.Customer;
import az.edu.eshopcustomer.enums.EnumAvailableStatus;
import az.edu.eshopcustomer.exception.EshopException;
import az.edu.eshopcustomer.exception.ExceptionConstants;
import az.edu.eshopcustomer.repository.CustomerRepository;
import az.edu.eshopcustomer.service.CustomerAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CustomerAuthServiceImpl implements CustomerAuthService {
    @Override
    public Response<String> login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Response<String> logout(String token) {
        return null;
    }

    @Value("${jwt.access.expiration}")
    private long jwtAccessExpiration;

   */
/* private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final CustomUserDetailsService customUserDetailsService;
    private final RedisTemplate<String, String> redisTemplate;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Response<String> login(LoginRequest loginRequest) {
        Response<String> response = new Response<>();
        Customer customer = customerRepository.findByEmailAndActive(loginRequest.getMail(), EnumAvailableStatus.ACTIVE.getValue());
        if (customer == null) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Email or password is incorrect");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA,"User not found or password incorrect");
        }
        customUserDetailsService.setRole(Role.CUSTOMER);
        authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getMail(), loginRequest.getPassword()));
        String token = jwtGenerator.generateTokenCustomer(customer);
        response.setT(token);
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<String> logout(String token) {
        Response<String> response = new Response<>();
        String jwtToken = token.substring(7);
        redisTemplate.opsForValue().set(jwtToken, "blacklisted", jwtAccessExpiration, TimeUnit.MILLISECONDS);
        response.setStatus(RespStatus.getSuccessMessage());
        response.setT("Logged out successfully!");
        return response;
    }*//*

}
*/
