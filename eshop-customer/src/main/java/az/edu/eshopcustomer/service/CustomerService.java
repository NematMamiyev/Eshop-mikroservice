package az.edu.eshopcustomer.service;



import az.edu.eshopcustomer.dto.request.ReqCustomer;
import az.edu.eshopcustomer.dto.response.RespCustomer;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.dto.response.Response;

import java.util.List;

public interface CustomerService {
    Response<RespCustomer> register(ReqCustomer reqCustomer);

    Response<List<RespCustomer>> getCustomerList();

    Response<RespCustomer> getCustomerById(Long id);

    Response<RespCustomer> updateCustomer(Long id,ReqCustomer reqCustomer);

    RespStatus deleteCustomer(Long id);
}
