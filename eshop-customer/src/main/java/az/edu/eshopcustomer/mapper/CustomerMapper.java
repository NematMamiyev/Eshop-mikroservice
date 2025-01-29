package az.edu.eshopcustomer.mapper;

import az.edu.eshopcustomer.dto.request.ReqCustomer;
import az.edu.eshopcustomer.dto.response.RespCustomer;
import az.edu.eshopcustomer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(ReqCustomer reqCustomer);
    RespCustomer toRespCustomer(Customer customer);
    List<RespCustomer> toRespCustomerList(List<Customer> customerList);
    void updateCustomerFromReqCustomer(@MappingTarget Customer customer, ReqCustomer reqCustomer);
}
