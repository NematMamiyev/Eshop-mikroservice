package az.edu.eshopcustomer.service.impl;

import az.edu.eshopcustomer.client.product.ProductDetailsUtil;
import az.edu.eshopcustomer.client.product.dto.RespProductDetails;
import az.edu.eshopcustomer.dto.response.CartProductDetails;
import az.edu.eshopcustomer.dto.response.RespCart;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.dto.response.Response;
import az.edu.eshopcustomer.entity.Cart;
import az.edu.eshopcustomer.enums.EnumAvailableStatus;
import az.edu.eshopcustomer.exception.EshopException;
import az.edu.eshopcustomer.exception.ExceptionConstants;
import az.edu.eshopcustomer.repository.CartRepository;
import az.edu.eshopcustomer.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ProductDetailsUtil productDetailsUtil;
    private final CartRepository cartRepository;
   // private final JwtGenerator jwtGenerator;

    @Override
    public RespStatus addCart(Long productDetailsId, HttpServletRequest httpServletRequest) {
        //Cart cart = getCart(getCustomerId(httpServletRequest));
        Cart cart = cartRepository.findCartByCustomerIdAndActive(1L,EnumAvailableStatus.ACTIVE.getValue());
        if (productDetailsUtil.getRespProductDetails(productDetailsId).isOutOfStock()){
            throw new EshopException(ExceptionConstants.OUT_OF_STOCK,"Product details is out of stock");
        }
        cart.getProductDetailsQuantityMap().merge(productDetailsId,1,Integer::sum);
        cart.setAmount(cart.getAmount().add(productDetailsUtil.getRespProductDetails(productDetailsId).getPrice()));
        cartRepository.save(cart);
        return RespStatus.getSuccessMessage();
    }

    @Override
    public RespStatus deleteCart(Long productDetailsId, HttpServletRequest httpServletRequest) {
        RespProductDetails respProductDetails = productDetailsUtil.getRespProductDetails(productDetailsId);
        //Cart cart = getCart(getCustomerId(httpServletRequest));
        Cart cart = cartRepository.findCartByCustomerIdAndActive(1L,EnumAvailableStatus.ACTIVE.getValue());
        Map<Long,Integer> productDetailsQuantityMap= cart.getProductDetailsQuantityMap();
        if (productDetailsQuantityMap.isEmpty()) {
            throw new EshopException(ExceptionConstants.PRODUCT_IS_NOT_IN_CART, "The product is not in the cart");
        }
        if (productDetailsQuantityMap.containsKey(productDetailsId)) {
            Integer currentQuantity = productDetailsQuantityMap.get(productDetailsId);
            if (currentQuantity > 1) {
                productDetailsQuantityMap.put(productDetailsId, currentQuantity - 1);
            } else {
                productDetailsQuantityMap.remove(productDetailsId);
            }
        }else {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA,"No product Details in cart");
        }
        cart.setAmount(cart.getAmount().subtract(respProductDetails.getPrice()));
        cart.setProductDetailsQuantityMap(productDetailsQuantityMap);
        cartRepository.save(cart);
        return RespStatus.getSuccessMessage();
    }

    @Override
    public Response<RespCart> listByCustomerId(HttpServletRequest httpServletRequest) {
        Response<RespCart> response = new Response<>();
        //Cart cart = getCart(getCustomerId(httpServletRequest));
        Cart cart = cartRepository.findCartByCustomerIdAndActive(1L,EnumAvailableStatus.ACTIVE.getValue());
        if (cart.getProductDetailsQuantityMap().isEmpty()){
            throw new EshopException(ExceptionConstants.CART_IS_EMPTY, "Cart is empty");
        }
        List<CartProductDetails> cartProductDetailsList = new ArrayList<>();
        for (Long id : cart.getProductDetailsQuantityMap().keySet()){
            CartProductDetails cartProductDetails = new CartProductDetails();
            cartProductDetails.setRespProductDetails(productDetailsUtil.getRespProductDetails(id));
            cartProductDetails.setQuantity(cart.getProductDetailsQuantityMap().get(id));
            cartProductDetailsList.add(cartProductDetails);
        }
        RespCart respCart = RespCart.builder()
                .cartProductDetailsList(cartProductDetailsList)
                .amount(cart.getAmount())
                .build();
        response.setT(respCart);
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }
/*    private Long getCustomerId(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            return jwtGenerator.getId(token);
        }
        throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA,"Token not found");
    }*/

    private Cart getCart(Long customerId) {
        Cart cart = cartRepository.findCartByCustomerIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
        if (cart == null) {
            throw new EshopException(ExceptionConstants.CART_NOT_FOUND, "Cart not found");
        }
        return cart;
    }
}
