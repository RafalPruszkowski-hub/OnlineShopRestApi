package com.store.app.serviceImpl;

import com.store.app.database.entity.ProductEntity;
import com.store.app.database.repository.ProductRepository;
import com.store.app.dto.ProductDto;
import com.store.app.exception.product.ProductNotFoundException;
import com.store.app.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDto create(ProductDto productDto) {
        ProductDto returnValue = new ProductDto();

        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);

        String publicId = UUID.randomUUID().toString();
        productEntity.setPublicProductId(publicId);

        ProductEntity createdEntity = productRepository.save(productEntity);
        BeanUtils.copyProperties(createdEntity, returnValue);

        return returnValue;
    }

    @Override
    public ProductDto get(String id) {
        ProductDto returnValue = new ProductDto();

        ProductEntity productEntity = productRepository.findByPublicProductId(id);
        if (productEntity == null) throw new ProductNotFoundException();
        BeanUtils.copyProperties(productEntity, returnValue);

        return returnValue;
    }

    @Override
    public ProductDto update(ProductDto productDto, String id) {
        ProductDto returnValue = new ProductDto();

        ProductEntity productEntity = productRepository.findByPublicProductId(id);
        if(productEntity == null) throw new ProductNotFoundException();


        if (productDto.getProductBrand() != null) productEntity.setProductBrand(productDto.getProductBrand());
        if (productDto.getProductDescription() != null) productEntity.setProductDescription(productDto.getProductDescription());
        if (productDto.getProductModel() != null) productEntity.setProductModel(productDto.getProductModel());
        if (productDto.getProductName() != null) productEntity.setProductName(productDto.getProductName());

        //TODO MAKE THIS WORK LATER
        //if(productDto.getProductPrice()!=null) productEntity.setProductPrice(productDto.getProductPrice());
        //if(productDto.getQuantityOfStock()!=null) productEntity.setQuantityOfStock(productDto.getQuantityOfStock());

        ProductEntity updatedUser = productRepository.save(productEntity);
        BeanUtils.copyProperties(updatedUser, returnValue);


        return returnValue;
    }

    @Override
    public List<ProductDto> getList(int page, int limit) {
        List<ProductDto> returnValue = new ArrayList();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<ProductEntity> productPage = productRepository.findAll(pageableRequest);
        List<ProductEntity> products = productPage.getContent();

        for (ProductEntity productEntity : products) {
            ProductDto productDto = new ProductDto(productEntity);
            returnValue.add(productDto);
        }
        return returnValue;
    }

    @Override
    public ProductDto updateProductStock(int quantity, String publicProductId) {
        ProductEntity productEntity = productRepository.findByPublicProductId(publicProductId);
        if(productEntity == null) throw new ProductNotFoundException();

        ProductDto returnValue = new ProductDto();

                productEntity.setQuantityOfStock(productEntity.getQuantityOfStock() - quantity);
        ProductEntity storedEntity = productRepository.save(productEntity);

        BeanUtils.copyProperties(storedEntity, returnValue);

        return returnValue;
    }
}
