package az.edu.eshopproduct.service;

import az.edu.eshopproduct.dto.request.ReqProductDetails;
import az.edu.eshopproduct.dto.request.ReqUpdateProductDetails;
import az.edu.eshopproduct.dto.response.RespProductDetails;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;

import java.util.List;

public interface ProductDetailsService {
    Response<RespProductDetails> addProductDetails(ReqProductDetails reqProductDetails);

    Response<RespProductDetails> updateProductDetails(Long id, ReqUpdateProductDetails reqUpdateProductDetails);

    Response<List<RespProductDetails>> getProductDetails();

    Response<RespProductDetails> getProductDetailsById(Long id);

    RespStatus deleteProductDetails(Long id);
}
