package az.edu.eshopproduct.mapper;

import az.edu.eshopproduct.dto.request.ReqProductDetails;
import az.edu.eshopproduct.dto.request.ReqUpdateProductDetails;
import az.edu.eshopproduct.dto.response.RespProductDetails;
import az.edu.eshopproduct.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",uses = {MapperHelper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDetailsMapper {
    @Mapping(source = "productId", target = "product", qualifiedByName = "mapProduct")
    @Mapping(source = "sizeId", target = "size", qualifiedByName = "mapSize")
    @Mapping(source = "colorId", target = "color", qualifiedByName = "mapColor")
    ProductDetails toProductDetails(ReqProductDetails reqProductDetails);

    @Mapping(source = "size", target = "respSize")
    @Mapping(source = "color", target = "respColor")
    @Mapping(source = "product.brand", target = "respProductForProductDetails.respBrand")
    @Mapping(source = "product.subcategory", target = "respProductForProductDetails.respSubcategory")
    @Mapping(source = "product.subcategory.category", target = "respProductForProductDetails.respSubcategory.respCategory")
    RespProductDetails toRespProductDetails(ProductDetails productDetails);

    List<RespProductDetails> toRespProductDetailsList(List<ProductDetails> productDetailsList);

    @Mapping(source = "productId", target = "product", qualifiedByName = "mapProduct")
    @Mapping(source = "sizeId", target = "size", qualifiedByName = "mapSize")
    @Mapping(source = "colorId", target = "color", qualifiedByName = "mapColor")
    void updateProductDetailsFromReqProductDetails(@MappingTarget ProductDetails productDetails, ReqUpdateProductDetails reqUpdateProductDetails);


}
