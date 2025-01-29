package az.edu.eshopproduct.mapper;

import az.edu.eshopproduct.dto.request.ReqSubcategory;
import az.edu.eshopproduct.dto.response.RespSubcategory;
import az.edu.eshopproduct.entity.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MapperHelper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubCategoryMapper {
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "mapCategory")
    Subcategory toSubcategory(ReqSubcategory reqSubcategory);

    @Mapping(source = "category", target = "respCategory")
    RespSubcategory toRespSubcategory(Subcategory subcategory);

    @Mapping(source = "categoryId", target = "category", qualifiedByName = "mapCategory")
    void updateSubcategoryFromReqSubcategory(@MappingTarget Subcategory subcategory, ReqSubcategory reqSubcategory);

    List<RespSubcategory> toRespSubcategoryList(List<Subcategory> subcategoryList);
}