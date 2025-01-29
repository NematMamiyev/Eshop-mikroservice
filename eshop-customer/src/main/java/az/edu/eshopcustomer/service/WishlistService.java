package az.edu.eshopcustomer.service;


import az.edu.eshopcustomer.client.product.dto.RespProductDetails;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.dto.response.Response;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface WishlistService {
    RespStatus addWishlist(Long productDetailsId, HttpServletRequest httpServletRequest);

    RespStatus deleteWishlist(Long productDetailsId, HttpServletRequest httpServletRequest);

    Response<List<RespProductDetails>> listByCustomerId(HttpServletRequest httpServletRequest);
}
