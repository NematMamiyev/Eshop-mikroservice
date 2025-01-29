package az.edu.eshopproduct.service;

import az.edu.eshopproduct.dto.request.ReqSubcategory;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.RespSubcategory;
import az.edu.eshopproduct.dto.response.Response;

import java.util.List;

public interface SubcategoryService{
    Response<RespSubcategory> addSubcategory(ReqSubcategory reqSubcategory);

    Response<List<RespSubcategory>> getSubcategoryList();

    Response<RespSubcategory> getSubcategoryById(Long id);

    Response<RespSubcategory> updateSubcategory(Long id, ReqSubcategory reqSubcategory);

    RespStatus deleteSubcategory(Long id);
}
