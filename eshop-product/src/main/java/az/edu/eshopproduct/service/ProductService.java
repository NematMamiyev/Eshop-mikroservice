package az.edu.eshopproduct.service;

import az.edu.eshopproduct.dto.request.ReqProduct;
import az.edu.eshopproduct.dto.request.ReqProductDetails;
import az.edu.eshopproduct.dto.request.ReqProductDetailsForProduct;
import az.edu.eshopproduct.dto.response.RespProduct;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;

import java.util.List;

public interface ProductService {
    Response<RespProduct> addProduct(ReqProduct reqProduct, List<ReqProductDetailsForProduct> reqProductDetailsForProducts);

    Response<List<RespProduct>> getProductList();

    Response<RespProduct> getProductById(Long id);

    Response<RespProduct> updateProduct(Long id, ReqProduct reqProduct);

    RespStatus deleteProduct(Long id);
}
