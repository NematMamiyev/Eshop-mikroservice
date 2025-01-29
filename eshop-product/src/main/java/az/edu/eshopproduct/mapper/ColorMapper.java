package az.edu.eshopproduct.mapper;

import az.edu.eshopproduct.dto.request.ReqColor;
import az.edu.eshopproduct.dto.response.RespColor;
import az.edu.eshopproduct.entity.Color;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ColorMapper {
    Color toColor(ReqColor reqColor);
    RespColor toRespColor(Color color);
    List<RespColor> toRespColorList(List<Color> colorList);
    void updateColorFromReqColor(@MappingTarget Color color,ReqColor reqColor);
}
