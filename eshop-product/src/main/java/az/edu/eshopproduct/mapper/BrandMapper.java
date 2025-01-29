package az.edu.eshopproduct.mapper;

import az.edu.eshopproduct.dto.request.ReqBrand;
import az.edu.eshopproduct.dto.response.RespBrand;
import az.edu.eshopproduct.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {
    Brand toBrand(ReqBrand reqBrand);
    RespBrand toRespBrand(Brand brand);
    List<RespBrand> toRespBrandList (List<Brand> brandList);
    void updateBrandFromReqBrand(@MappingTarget Brand brand, ReqBrand reqBrand);

}
