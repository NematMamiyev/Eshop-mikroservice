package az.edu.eshopproduct.mapper;

import az.edu.eshopproduct.dto.request.ReqSize;
import az.edu.eshopproduct.dto.response.RespSize;
import az.edu.eshopproduct.entity.Size;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SizeMapper {

    Size toSize(ReqSize reqSize);
    RespSize toRespSize(Size size);
    List<RespSize> toRespSizeList(List<Size> sizeList);
    void updateSizeFromReqSize(@MappingTarget Size size, ReqSize reqSize);
}
