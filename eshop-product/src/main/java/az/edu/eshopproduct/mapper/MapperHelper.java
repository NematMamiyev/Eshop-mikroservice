package az.edu.eshopproduct.mapper;


import az.edu.eshopproduct.entity.*;
import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.repository.*;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperHelper {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;

    @Named("mapCategory")
    public Category mapCategory(Long categoryId) {
        return categoryRepository.findByIdAndActive(categoryId, EnumAvailableStatus.ACTIVE.getValue());
    }
    @Named("mapBrand")
    public Brand getBrand(Long brandId){
        return brandRepository.findByIdAndActive(brandId,EnumAvailableStatus.ACTIVE.getValue());
    }
    @Named("mapSubcategory")
    public Subcategory getSubcategory(Long subcategoryId){
        return subcategoryRepository.findSubcategoryByIdAndActive(subcategoryId,EnumAvailableStatus.ACTIVE.getValue());
    }
    @Named("mapSize")
    public Size getSize(Long sizeId){
        return sizeRepository.findSizeByIdAndActive(sizeId,EnumAvailableStatus.ACTIVE.getValue());
    }
    @Named("mapColor")
    public Color getColor(Long colorId){
        return colorRepository.findByIdAndActive(colorId,EnumAvailableStatus.ACTIVE.getValue());
    }
    @Named("mapProduct")
    public Product getProduct(Long productId){
        return productRepository.findProductByIdAndActive(productId,EnumAvailableStatus.ACTIVE.getValue());
    }
    @Named("mapProductDetails")
    public ProductDetails getProductDetails(Long productDetailsId){
        return productDetailsRepository.findProductDetailsByIdAndActive(productDetailsId,EnumAvailableStatus.ACTIVE.getValue());
    }

}
