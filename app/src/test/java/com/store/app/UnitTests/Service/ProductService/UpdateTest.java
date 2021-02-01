package com.store.app.UnitTests.Service.ProductService;

import com.store.app.Util.UUIDGenerator;
import com.store.app.database.entity.ProductEntity;
import com.store.app.database.repository.ProductRepository;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.ProductDto;
import com.store.app.service.ProductService;
import com.store.app.service.UserService;
import com.store.app.serviceImpl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductServiceImpl.class)
public class UpdateTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private UUIDGenerator uuidGenerator;

    //TODO FIND WHY w/o userService and userRepository it's not working
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void productCreate_basic(){
        String publicId = "publicId";
        String name = "name";
        String brand = "brand";
        String model = "model";
        String desc = "desc";
        int quant = 100;
        double price = 23.43;

        String publicIdUpdated = "publicIdUpdated";
        String nameUpdated = "nameUpdated";
        String brandUpdated = "brandUpdated";
        String modelUpdated = "modelUpdated";
        String descUpdated = "descUpdated";
        int quantUpdated = 999;
        double priceUpdated = 55.55;

        ProductEntity saved = new ProductEntity(1,publicId,name,brand,model,desc,quant,price,null);
        ProductDto productDto = new ProductDto(1,publicIdUpdated,nameUpdated,brandUpdated,modelUpdated,descUpdated,quantUpdated,priceUpdated);

        when(uuidGenerator.generate()).thenReturn(publicId);
        when(productRepository.save(Mockito.any(ProductEntity.class))).thenReturn(saved);
        ProductDto result = productService.update(productDto,publicId);

        assertEquals(result.getProductId(), 1);
        assertEquals(result.getPublicProductId(), publicIdUpdated);
        assertEquals(result.getProductName(), nameUpdated);
        assertEquals(result.getProductBrand(), brandUpdated);
        assertEquals(result.getProductModel(), modelUpdated);
        assertEquals(result.getProductDescription(), descUpdated);
        assertEquals(result.getQuantityOfStock(), quantUpdated);
        assertEquals(result.getProductPrice(), priceUpdated);
    }
}
