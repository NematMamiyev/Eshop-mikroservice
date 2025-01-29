package az.edu.eshopproduct.mapper;

import az.edu.eshopproduct.dto.request.ReqCategory;
import az.edu.eshopproduct.dto.response.RespCategory;
import az.edu.eshopproduct.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    Category toCategory(ReqCategory reqCategory);
    RespCategory toRespCategory(Category category);
    List<RespCategory> toRespCategoryList(List<Category> categoryList);
    void updateCategoryFromReqCategory(@MappingTarget Category category,ReqCategory reqCategory);

}
