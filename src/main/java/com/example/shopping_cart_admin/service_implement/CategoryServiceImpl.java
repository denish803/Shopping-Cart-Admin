package com.example.shopping_cart_admin.service_implement;

import com.example.shopping_cart_admin.dto.CategoryDTO;
import com.example.shopping_cart_admin.entity.CategoryEntity;
import com.example.shopping_cart_admin.repository.CategoryRepository;
import com.example.shopping_cart_admin.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void insert(CategoryDTO categoryDTO) {

        try {
            CategoryEntity categoryEntityList = modelMapper.map(categoryDTO, CategoryEntity.class);
            categoryRepository.save(categoryEntityList);
        } catch (Exception e) {
            log.error("category implement in insert method not insert ", e);
        }
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        Optional<CategoryEntity> categoryData = categoryRepository.findById((long)categoryDTO.getId());
        if (categoryData.isPresent()) {
            CategoryEntity categoryEntity = categoryData.get();
            modelMapper.map(categoryDTO, categoryEntity);
            categoryRepository.save(categoryEntity);
        }
    }

    @Override
    public List<CategoryDTO> view() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        List<CategoryDTO> collect2 = categoryEntityList
                .stream()
                .map(categoryEntity -> modelMapper.map(categoryEntity, CategoryDTO.class))
                .collect(Collectors.toList());
        log.info("category service implement data  {}", collect2);
        return collect2;
    }

    @Override
    public CategoryDTO get(int id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById((long) id);
        return modelMapper.map(categoryEntity, CategoryDTO.class);
    }

    @Override
    public int getIdByName(String name) {
        Optional<CategoryEntity> categoryIdEntity = categoryRepository.getIdByName(name);
        if (categoryIdEntity.isPresent()) {
            CategoryEntity categoryEntity = categoryIdEntity.get();
            log.info("get id : {}", categoryEntity.getId());
            return categoryEntity.getId();
        }
        return 0;
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteById((long)id);
    }
}
