package az.edu.eshopcustomer.service.impl;


import az.edu.eshopcustomer.client.product.ProductDetailsUtil;
import az.edu.eshopcustomer.client.product.dto.RespProductDetails;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.dto.response.Response;
import az.edu.eshopcustomer.entity.Wishlist;
import az.edu.eshopcustomer.enums.EnumAvailableStatus;
import az.edu.eshopcustomer.exception.EshopException;
import az.edu.eshopcustomer.exception.ExceptionConstants;
import az.edu.eshopcustomer.repository.WishlistRepository;
import az.edu.eshopcustomer.service.WishlistService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductDetailsUtil productDetailsUtil;
   // private final JwtGenerator jwtGenerator;

    @Override
    public RespStatus addWishlist(Long productDetailsId, HttpServletRequest httpServletRequest) {
        //Wishlist wishlist = wishlistRepository.findWishlistByCustomerIdAndActive(getCustomerId(httpServletRequest), EnumAvailableStatus.ACTIVE.getValue());
        Wishlist wishlist = wishlistRepository.findWishlistByCustomerIdAndActive(1L, EnumAvailableStatus.ACTIVE.getValue());
        if (wishlist == null) {
            throw new EshopException(ExceptionConstants.WISHLIST_NOT_FOUND, "Wishlist not found");
        }
        wishlist.getProductDetailsIdList().add(productDetailsId);
        wishlistRepository.save(wishlist);
        return RespStatus.getSuccessMessage();
    }

    @Override
    public RespStatus deleteWishlist(Long productDetailsId, HttpServletRequest httpServletRequest) {
        //Wishlist wishlist = wishlistRepository.findWishlistByCustomerIdAndActive(getCustomerId(httpServletRequest), EnumAvailableStatus.ACTIVE.getValue());
        Wishlist wishlist = wishlistRepository.findWishlistByCustomerIdAndActive(1L, EnumAvailableStatus.ACTIVE.getValue());
        if (wishlist == null) {
            throw new EshopException(ExceptionConstants.WISHLIST_NOT_FOUND, "Wishlist not found");
        }
        wishlist.getProductDetailsIdList().removeIf(id -> id.equals(productDetailsId));
        wishlistRepository.save(wishlist);
        return RespStatus.getSuccessMessage();
    }

    @Override
    public Response<List<RespProductDetails>> listByCustomerId(HttpServletRequest httpServletRequest) {
        Response<List<RespProductDetails>> response = new Response<>();
        //Wishlist wishlist = wishlistRepository.findWishlistByCustomerIdAndActive(getCustomerId(httpServletRequest), EnumAvailableStatus.ACTIVE.getValue());
        Wishlist wishlist = wishlistRepository.findWishlistByCustomerIdAndActive(1L, EnumAvailableStatus.ACTIVE.getValue());
        if (wishlist == null) {
            throw new EshopException(ExceptionConstants.WISHLIST_NOT_FOUND, "Wishlist not found");
        }
        if (wishlist.getProductDetailsIdList().isEmpty()) {
            throw new EshopException(ExceptionConstants.PRODUCT_DETAILS_NOT_FOUND, "Wishlist is empty");
        }
        List<RespProductDetails> respProductDetailsList = new ArrayList<>();
        for (Long id : wishlist.getProductDetailsIdList()){
            respProductDetailsList.add(productDetailsUtil.getRespProductDetails(id));
        }
        response.setT(respProductDetailsList);
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    /*private Long getCustomerId(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            return jwtGenerator.getId(token);
        }
        throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA,"Token not found");
    }*/

}
