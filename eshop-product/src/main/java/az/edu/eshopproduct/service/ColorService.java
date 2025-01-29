package az.edu.eshopproduct.service;



import az.edu.eshopproduct.dto.request.ReqColor;
import az.edu.eshopproduct.dto.response.RespColor;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;

import java.util.List;

public interface ColorService {

    Response<RespColor> addColor(ReqColor reqColor);

    Response<List<RespColor>> colorList();

    Response<RespColor> getColorById(Long id);

    Response<RespColor> updateColor(Long id, ReqColor reqColor);

    RespStatus deleteColor(Long id);
}
