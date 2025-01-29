package az.edu.eshopproduct.service.impl;

import az.edu.eshopproduct.dto.request.ReqBrand;
import az.edu.eshopproduct.dto.response.RespBrand;
import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.dto.response.Response;
import az.edu.eshopproduct.entity.Brand;
import az.edu.eshopproduct.enums.EnumAvailableStatus;
import az.edu.eshopproduct.exception.EshopException;
import az.edu.eshopproduct.exception.ExceptionConstants;
import az.edu.eshopproduct.mapper.BrandMapper;
import az.edu.eshopproduct.repository.BrandRepository;
import az.edu.eshopproduct.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
   // private final JwtGenerator jwtGenerator;

    @Override
    public Response<RespBrand> getBrandById(Long id) {
        Response<RespBrand> response = new Response<>();
        Brand brand = getBrand(id);
        response.setT(brandMapper.toRespBrand(brand));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespBrand> addBrand(ReqBrand reqBrand) {
        Response<RespBrand> response = new Response<>();
        boolean uniqueName = brandRepository.existsBrandByNameAndActive(reqBrand.getName().trim(), EnumAvailableStatus.ACTIVE.getValue());
        if (uniqueName) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Name available in the database");
        }
        Brand brand = brandMapper.toBrand(reqBrand);
        brandRepository.save(brand);
        response.setT(brandMapper.toRespBrand(brand));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<List<RespBrand>> brandList() {
        Response<List<RespBrand>> response = new Response<>();
        List<Brand> brandList = brandRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
        if (brandList.isEmpty()) {
            throw new EshopException(ExceptionConstants.BRAND_NOT_FOUND, "Brand not found");
        }
        response.setT(brandMapper.toRespBrandList(brandList));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespBrand> updateBrand(Long id, ReqBrand reqBrand) {
        Response<RespBrand> response = new Response<>();
        Brand brand = getBrand(id);
        boolean uniqueName = brandRepository.existsBrandByNameAndActiveAndIdNot(reqBrand.getName(), EnumAvailableStatus.ACTIVE.getValue(), id);
        if (uniqueName) {
            throw new EshopException(ExceptionConstants.INVALID_REQUEST_DATA, "Name available in the database");
        }
        brandMapper.updateBrandFromReqBrand(brand, reqBrand);
        brandRepository.save(brand);
        response.setT(brandMapper.toRespBrand(brand));
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public RespStatus deleteBrand(Long id) {
        Brand brand = getBrand(id);
        brand.setActive(EnumAvailableStatus.DEACTIVATED.getValue());
        brandRepository.save(brand);
        return RespStatus.getSuccessMessage();
    }

    private Brand getBrand(Long id) {
        Brand brand = brandRepository.findByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue());
        if (brand == null) {
            throw new EshopException(ExceptionConstants.BRAND_NOT_FOUND, "Brand not found");
        }
        return brand;
    }
}
