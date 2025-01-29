package az.edu.eshopproduct.service;

import az.edu.eshopproduct.dto.request.ReqSize;
import az.edu.eshopproduct.dto.response.RespSize;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;

import java.util.List;

public interface SizeService {
    Response<RespSize> addSize(ReqSize reqSize);

    Response<List<RespSize>> getSizeList();

    Response<RespSize> getSizeById(Long id);

    Response<RespSize> updateSize(Long id, ReqSize reqSize);

    RespStatus deleteSize(Long id);
}
