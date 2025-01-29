package az.edu.eshopproduct.service.impl;
import az.edu.eshopproduct.dto.request.ReqSubcategory;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.RespSubcategory;
import az.edu.eshopproduct.dto.response.Response;
import az.edu.eshopproduct.entity.Subcategory;
import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.exception.EshopException;
import az.edu.eshopproduct.exception.ExceptionConstants;
import az.edu.eshopproduct.mapper.SubCategoryMapper;
import az.edu.eshopproduct.repository.SubcategoryRepository;
import az.edu.eshopproduct.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final SubCategoryMapper subCategoryMapper;

    @Override
    public Response<RespSubcategory> addSubcategory(ReqSubcategory reqSubcategory) {
        Response<RespSubcategory> response = new Response<>();
        boolean uniqueName = subcategoryRepository.existsSubcategoryByNameAndActive(reqSubcategory.getName(), EnumAvailableStatus.ACTIVE.getValue());
        if (uniqueName) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Name available in the database");
        }
        Subcategory subcategory = subCategoryMapper.toSubcategory(reqSubcategory);
        subcategoryRepository.save(subcategory);
        response.setT(subCategoryMapper.toRespSubcategory(subcategory));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<List<RespSubcategory>> getSubcategoryList() {
        Response<List<RespSubcategory>> response = new Response<>();
        List<Subcategory> subcategoryList = subcategoryRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
        if (subcategoryList.isEmpty()) {
            throw new EshopException(ExceptionConstants.SUBCATEGORY_NOT_FOUND, "Subcategory is empty");
        }
        response.setT(subCategoryMapper.toRespSubcategoryList(subcategoryList));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespSubcategory> getSubcategoryById(Long id) {
        Response<RespSubcategory> response = new Response<>();
        Subcategory subcategory = getSubcategory(id);
        response.setT(subCategoryMapper.toRespSubcategory(subcategory));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespSubcategory> updateSubcategory(Long id, ReqSubcategory reqSubcategory) {
        Response<RespSubcategory> response = new Response<>();
        boolean uniqueName = subcategoryRepository.existsSubcategoryByNameAndActiveAndIdNot(reqSubcategory.getName(), EnumAvailableStatus.ACTIVE.getValue(), id);
        if (uniqueName) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Name available in the database");
        }
        Subcategory subcategory = getSubcategory(id);
        subCategoryMapper.updateSubcategoryFromReqSubcategory(subcategory, reqSubcategory);
        subcategoryRepository.save(subcategory);
        response.setT(subCategoryMapper.toRespSubcategory(subcategory));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public RespStatus deleteSubcategory(Long id) {
        Subcategory subcategory = getSubcategory(id);
        subcategory.setActive(EnumAvailableStatus.DEACTIVATED.getValue());
        subcategoryRepository.save(subcategory);
        return RespStatus.getSuccessMessage();
    }

    private Subcategory getSubcategory(Long id) {
        Subcategory subcategory = subcategoryRepository.findSubcategoryByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue());
        if (subcategory == null) {
            throw new EshopException(ExceptionConstants.SUBCATEGORY_NOT_FOUND, "Subcategory not found");
        }
        return subcategory;
    }
}
