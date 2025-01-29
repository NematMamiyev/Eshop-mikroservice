package az.edu.eshopproduct.service;

import az.edu.eshopproduct.dto.request.ReqCategory;
import az.edu.eshopproduct.dto.response.RespCategory;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;

import java.util.List;

public interface CategoryService {
    Response<RespCategory> addCategory(ReqCategory reqCategory);

    Response<List<RespCategory>> categoryList();

    Response<RespCategory> getCategoryById(Long id);

    Response<RespCategory> updateCategory(Long id,ReqCategory reqCategory);

    RespStatus deleteCategory(Long id);
}
