package az.edu.eshopproduct.service.impl;

import az.edu.eshopproduct.dto.request.ReqSize;
import az.edu.eshopproduct.dto.response.RespSize;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;
import az.edu.eshopproduct.entity.Size;
import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.exception.EshopException;
import az.edu.eshopproduct.exception.ExceptionConstants;
import az.edu.eshopproduct.mapper.SizeMapper;
import az.edu.eshopproduct.repository.SizeRepository;
import az.edu.eshopproduct.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;
    private final SizeMapper sizeMapper;

    @Override
    public Response<RespSize> addSize(ReqSize reqSize) {
        Response<RespSize> response = new Response<>();
        boolean uniqueName = sizeRepository.existsSizeByNameAndActive(reqSize.getName(), EnumAvailableStatus.ACTIVE.getValue());
        if (uniqueName) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Name available in the database");
        }
        Size size = sizeMapper.toSize(reqSize);
        sizeRepository.save(size);
        response.setT(sizeMapper.toRespSize(size));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<List<RespSize>> getSizeList() {
        Response<List<RespSize>> response = new Response<>();
        List<Size> sizeList = sizeRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
        if (sizeList.isEmpty()) {
            throw new EshopException(ExceptionConstants.SIZE_NOT_FOUND, "Size is empty");
        }
        response.setT(sizeMapper.toRespSizeList(sizeList));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespSize> getSizeById(Long id) {
        Response<RespSize> response = new Response<>();
        Size size = getSize(id);
        response.setT(sizeMapper.toRespSize(size));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespSize> updateSize(Long id, ReqSize reqSize) {
        Response<RespSize> response = new Response<>();
        boolean uniqueSize = sizeRepository.existsSizeByNameAndActiveAndIdNot(reqSize.getName(), EnumAvailableStatus.ACTIVE.getValue(), id);
        if (uniqueSize) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Name available in the database");
        }
        Size size = getSize(id);
        sizeMapper.updateSizeFromReqSize(size, reqSize);
        sizeRepository.save(size);
        response.setT(sizeMapper.toRespSize(size));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public RespStatus deleteSize(Long id) {
        Size size = getSize(id);
        size.setActive(EnumAvailableStatus.DEACTIVATED.getValue());
        sizeRepository.save(size);
        return RespStatus.getSuccessMessage();
    }

    private Size getSize(Long id) {
        Size size = sizeRepository.findSizeByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue());
        if (size == null) {
            throw new EshopException(ExceptionConstants.SIZE_NOT_FOUND, "Size not found");
        }
        return size;
    }
}
