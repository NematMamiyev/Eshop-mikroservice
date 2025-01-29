package az.edu.eshopproduct.service.impl;

import az.edu.eshopproduct.dto.request.ReqProduct;
import az.edu.eshopproduct.dto.request.ReqProductDetails;
import az.edu.eshopproduct.dto.request.ReqProductDetailsForProduct;
import az.edu.eshopproduct.dto.response.*;
import az.edu.eshopproduct.entity.*;
import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.exception.EshopException;
import az.edu.eshopproduct.exception.ExceptionConstants;
import az.edu.eshopproduct.mapper.ProductMapper;
import az.edu.eshopproduct.repository.*;
import az.edu.eshopproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final ProductMapper productMapper;

    @Override
    public Response<RespProduct> addProduct(ReqProduct reqProduct, List<ReqProductDetailsForProduct> reqProductDetailsForProducts) {
        Response<RespProduct> response = new Response<>();
            Product product = productMapper.toProduct(reqProduct);
            productRepository.save(product);
            List<ProductDetails> productDetailsList = reqProductDetailsForProducts.stream().map(reqProductDetails ->
                    ProductDetails.builder()
                            .product(product)
                            .color(getColor(reqProductDetails.getColorId()))
                            .size(getSize(reqProductDetails.getSizeId()))
                            .stock(reqProductDetails.getStock())
                            .currency(reqProductDetails.getCurrency())
                            .price(reqProductDetails.getPrice())
                            .build()
            ).collect(Collectors.toList());
            productDetailsRepository.saveAll(productDetailsList);
            product.setProductDetailsList(productDetailsList);
            response.setT(productMapper.toRespProduct(product));
            response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<List<RespProduct>> getProductList() {
        Response<List<RespProduct>> response = new Response<>();
            List<Product> productList = productRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (productList.isEmpty()) {
                throw new EshopException(ExceptionConstants.PRODUCT_NOT_FOUND, "Product is empty");
            }
            response.setT(productMapper.toRespProductList(productList));
            response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespProduct> getProductById(Long id) {
        Response<RespProduct> response = new Response<>();
            Product product = getProduct(id);
            response.setT(productMapper.toRespProduct(product));
            response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespProduct> updateProduct(Long id, ReqProduct reqProduct) {
        Response<RespProduct> response = new Response<>();
            Product product = getProduct(id);
            productMapper.updateProductFromReqProduct(product, reqProduct);
            productRepository.save(product);
            response.setT(productMapper.toRespProduct(product));
            response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public RespStatus deleteProduct(Long id) {
            Product product = getProduct(id);
            product.setActive(EnumAvailableStatus.DEACTIVATED.getValue());
            productRepository.save(product);
        return RespStatus.getSuccessMessage();
    }

    private Size getSize(Long sizeId) {
        return sizeRepository.findSizeByIdAndActive(sizeId, EnumAvailableStatus.ACTIVE.getValue());
    }

    private Color getColor(Long colorId) {
        return colorRepository.findByIdAndActive(colorId, EnumAvailableStatus.ACTIVE.getValue());
    }
    private Product getProduct(Long id){
        Product product = productRepository.findProductByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue());
        if (product == null) {
            throw new EshopException(ExceptionConstants.PRODUCT_NOT_FOUND, "Product not found");
        }
        return product;
    }
}
