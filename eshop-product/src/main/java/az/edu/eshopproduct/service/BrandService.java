package az.edu.eshopproduct.service;

import az.edu.eshopproduct.dto.request.ReqBrand;
import az.edu.eshopproduct.dto.response.RespBrand;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;

import java.util.List;

public interface BrandService {
    Response<RespBrand> addBrand(ReqBrand reqBrand);

    Response<List<RespBrand>> brandList();

    Response<RespBrand> getBrandById(Long id);

    Response<RespBrand> updateBrand(Long id, ReqBrand reqBrand);

    RespStatus deleteBrand(Long id);

}
