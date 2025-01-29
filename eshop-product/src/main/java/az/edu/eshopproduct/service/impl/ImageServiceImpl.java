package az.edu.eshopproduct.service.impl;

import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.entity.ProductDetails;
import az.edu.eshopproduct.entity.ProductImage;
import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.exception.EshopException;
import az.edu.eshopproduct.exception.ExceptionConstants;
import az.edu.eshopproduct.repository.ProductDetailsRepository;
import az.edu.eshopproduct.repository.ProductImageRepository;
import az.edu.eshopproduct.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductDetailsRepository productDetailsRepository;

    @Override
    public RespStatus addImages(List<MultipartFile> files, Long productDetailsId) throws IOException {
            if (files.isEmpty()) {
                throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "file is empty");
            }
            ProductDetails productDetails = productDetailsRepository.findProductDetailsByIdAndActive(productDetailsId, EnumAvailableStatus.ACTIVE.getValue());
            if (productDetails == null) {
                throw new EshopException(ExceptionConstants.PRODUCT_DETAILS_NOT_FOUND, "Product details not found");
            }
            for (MultipartFile file : files) {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                if (fileName.contains("..")) {
                    throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Filename contains invalid path sequence");
                }
                ProductImage productImage = new ProductImage(fileName, file.getContentType(), file.getBytes(), productDetails);
                productImageRepository.save(productImage);
            }
        return RespStatus.getSuccessMessage();
    }

    @Override
    public RespStatus deleteImagesByProductDetailsId(Long productDetailsId) {
            ProductDetails productDetails = productDetailsRepository.findProductDetailsByIdAndActive(productDetailsId, EnumAvailableStatus.ACTIVE.getValue());
            if (productDetails == null) {
                throw new EshopException(ExceptionConstants.PRODUCT_DETAILS_NOT_FOUND, "Product details not found");
            }
            List<ProductImage> images = productImageRepository.findProductImageByProductDetailsIdAndActive(productDetailsId, EnumAvailableStatus.ACTIVE.getValue());
            if (images.isEmpty()) {
                throw new EshopException(ExceptionConstants.PRODUCT_IMAGE_NOT_FOUND, "Image is empty");
            }
            productImageRepository.deactivateProductImagesByProductDetailsId(productDetailsId);
            productImageRepository.saveAll(images);
        return RespStatus.getSuccessMessage();
    }

    @Override
    public RespStatus deleteImage(Long imageId) {
            ProductImage productImage = productImageRepository.findProductImageByIdAndActive(imageId, EnumAvailableStatus.ACTIVE.getValue());
            if (productImage == null) {
                throw new EshopException(ExceptionConstants.PRODUCT_IMAGE_NOT_FOUND, "Image not found");
            }
            productImage.setActive(EnumAvailableStatus.DEACTIVATED.getValue());
            productImageRepository.save(productImage);
        return RespStatus.getSuccessMessage();
    }

    @Override
    public List<ResponseEntity<byte[]>> getImages(Long productDetailsId) {
        List<ResponseEntity<byte[]>> response = new ArrayList<>();
            ProductDetails productDetails = productDetailsRepository.findProductDetailsByIdAndActive(productDetailsId, EnumAvailableStatus.ACTIVE.getValue());
            if (productDetails == null) {
                throw new EshopException(ExceptionConstants.PRODUCT_DETAILS_NOT_FOUND, "Product details not found");
            }
            List<ProductImage> productImages = productImageRepository.findProductImageByProductDetailsIdAndActive(productDetailsId,EnumAvailableStatus.ACTIVE.getValue());
            for (ProductImage productImage: productImages){
                ResponseEntity<byte[]> responseEntity = ResponseEntity.ok()
                        .contentType(MediaType.valueOf(productImage.getFileType()))
                        .body(productImage.getData());
                response.add(responseEntity);
            }
        return response;
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long imageId) {
            ProductImage productImage = productImageRepository.findProductImageByIdAndActive(imageId,EnumAvailableStatus.ACTIVE.getValue());
            if (productImage == null){
                throw new EshopException(ExceptionConstants.PRODUCT_IMAGE_NOT_FOUND,"Product Image Not found");
            }
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(productImage.getFileType()))
                .body(productImage.getData());
    }
}
