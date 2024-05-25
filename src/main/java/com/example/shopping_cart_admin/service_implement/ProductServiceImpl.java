package com.example.shopping_cart_admin.service_implement;

import com.example.shopping_cart_admin.dto.ProductDTO;
import com.example.shopping_cart_admin.entity.ProductEntity;
import com.example.shopping_cart_admin.repository.ProductRepository;
import com.example.shopping_cart_admin.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getList() {
        List<ProductEntity> productDataEntity = productRepository.findAll();

        return productDataEntity.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO get(int id) {
        Optional<ProductEntity> productDataEntity = productRepository.findById((long) id);
        if (productDataEntity.isPresent()) {
            ProductEntity productEntity = productDataEntity.get();
            return modelMapper.map(productEntity, ProductDTO.class);
        }
        return null;
    }

    @Override
    public void insert(ProductDTO productDTO) {
//        log.info("insert product sub category id : {}", productDTO.getSub_category_id());
        productRepository.save(modelMapper.map(productDTO, ProductEntity.class));
    }

    @Override
    public void update(ProductDTO productDTO) {
        Optional<ProductEntity> productData = productRepository.findById((long) productDTO.getId());
        if (productData.isPresent()) {
            ProductEntity productEntity = productData.get();
            productRepository.save(modelMapper.map(productDTO, ProductEntity.class));
        }
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById((long)id);
    }
}
