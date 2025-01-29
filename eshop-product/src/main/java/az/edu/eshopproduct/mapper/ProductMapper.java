package az.edu.eshopproduct.mapper;

import az.edu.eshopproduct.dto.request.ReqProduct;
import az.edu.eshopproduct.dto.response.RespProduct;
import az.edu.eshopproduct.dto.response.RespProductDetailsForProduct;
import az.edu.eshopproduct.entity.Product;
import az.edu.eshopproduct.entity.ProductDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",uses = {MapperHelper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    @Mapping(source = "subcategoryId", target = "subcategory", qualifiedByName = "mapSubcategory")
    @Mapping(source = "brandId", target = "brand", qualifiedByName = "mapBrand")
    Product toProduct(ReqProduct reqProduct);

    @Mapping(source = "size", target = "respSize")
    @Mapping(source = "color", target = "respColor")
    RespProductDetailsForProduct toRespProductDetailsForProduct(ProductDetails productDetails);

    @Mapping(source = "subcategory", target = "respSubcategory")
    @Mapping(source = "brand", target = "respBrand")
    @Mapping(source = "subcategory.category", target = "respSubcategory.respCategory")
    @Mapping(source = "productDetailsList", target = "respProductDetailsForProductList")
    RespProduct toRespProduct(Product product);

    List<RespProduct> toRespProductList(List<Product> productList);

    @Mapping(source = "subcategoryId", target = "subcategory", qualifiedByName = "mapSubcategory")
    @Mapping(source = "brandId", target = "brand", qualifiedByName = "mapBrand")
    void updateProductFromReqProduct(@MappingTarget Product product, ReqProduct reqProduct);


}
