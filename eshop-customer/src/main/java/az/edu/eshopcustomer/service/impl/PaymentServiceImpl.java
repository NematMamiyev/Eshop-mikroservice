package az.edu.eshopcustomer.service.impl;


import az.edu.eshopcustomer.client.notification.EmailDto;
import az.edu.eshopcustomer.client.notification.NotificationClient;
import az.edu.eshopcustomer.client.product.ProductDetailsClient;
import az.edu.eshopcustomer.client.product.ProductDetailsUtil;
import az.edu.eshopcustomer.client.product.dto.ReqProductDetails;
import az.edu.eshopcustomer.client.product.dto.RespProductDetails;
import az.edu.eshopcustomer.client.warehouse.WarehouseClient;
import az.edu.eshopcustomer.dto.response.RespStatus;
import az.edu.eshopcustomer.entity.*;
import az.edu.eshopcustomer.enums.Email;
import az.edu.eshopcustomer.enums.EnumAvailableStatus;
import az.edu.eshopcustomer.enums.PaymentMethod;
import az.edu.eshopcustomer.enums.Status;
import az.edu.eshopcustomer.exception.EshopException;
import az.edu.eshopcustomer.exception.ExceptionConstants;
import az.edu.eshopcustomer.repository.*;
import az.edu.eshopcustomer.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductDetailsUtil productDetailsUtil;
    private final ProductDetailsClient productDetailsClient;
    private final WarehouseClient warehouseClient;
    private final NotificationClient notificationClient;
//    private final JwtGenerator jwtGenerator;

    @Override
    public RespStatus payment(PaymentMethod paymentMethod, HttpServletRequest httpServletRequest) {
//        String token = httpServletRequest.getHeader("Authorization");
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//        }
//        Long customerId = jwtGenerator.getId(token);
        Long customerId = 1L;
        Cart cart = cartRepository.findCartByCustomerIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
        if (cart == null) {
            throw new EshopException(ExceptionConstants.CART_NOT_FOUND, "Cart not found");
        }
        if (cart.getProductDetailsQuantityMap().isEmpty()) {
            throw new EshopException(ExceptionConstants.CART_IS_EMPTY, "Cart is empty");
        }
        for (Long id : cart.getProductDetailsQuantityMap().keySet()){
            RespProductDetails respProductDetails = productDetailsUtil.getRespProductDetails(id);
            if (respProductDetails.getStock() < cart.getProductDetailsQuantityMap().get(id)){
                throw new EshopException(ExceptionConstants.OUT_OF_STOCK, "Out of stock");
            } else {
                respProductDetails.setStock(respProductDetails.getStock()-cart.getProductDetailsQuantityMap().get(id));
                if (respProductDetails.getStock() ==0){
                    respProductDetails.setOutOfStock(true);
                }
                ReqProductDetails reqProductDetails = ReqProductDetails.builder()
                        .currency(respProductDetails.getCurrency())
                        .productId(respProductDetails.getRespProductForProductDetails().getId())
                        .price(respProductDetails.getPrice())
                        .stock(respProductDetails.getStock())
                        .colorId(respProductDetails.getRespColor().getId())
                        .sizeId(respProductDetails.getRespSize().getId())
                        .outOfStock(respProductDetails.isOutOfStock())
                        .build();
                productDetailsClient.updateProductDetails(respProductDetails.getId(), reqProductDetails);
            }
        }
        Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
        Payment payment = Payment.builder()
                .paymentMethod(paymentMethod)
                .customer(customer)
                .amount(cart.getAmount())
                .build();
        paymentRepository.save(payment);
        Order order = Order.builder()
                .customer(customer)
                .amount(cart.getAmount())
                .productDetailsQuantityMap(new HashMap<>(cart.getProductDetailsQuantityMap()))
                .build();
        orderRepository.save(order);
        OrderStatus orderStatus = OrderStatus.builder()
                .status(Status.ORDERED)
                .order(order)
                .build();
        orderStatusRepository.save(orderStatus);
        EmailDto emailDto = EmailDto.builder()
                .to(customer.getEmail())
                .subject(Email.ORDERED.toString())
                .body(Email.ORDERED.getDescription())
                .build();
       // notificationClient.sendEmail(emailDto);
        cart.getProductDetailsQuantityMap().clear();
        cart.setAmount(BigDecimal.ZERO);
        warehouseClient.addWork(order.getId());
        cartRepository.save(cart);
        return RespStatus.getSuccessMessage();
    }
}
