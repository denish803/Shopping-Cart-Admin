package com.example.shopping_cart_admin.service_implement;

import com.example.shopping_cart_admin.dto.SubCategoryDTO;
import com.example.shopping_cart_admin.entity.SubCategoryEntity;
import com.example.shopping_cart_admin.repository.SubCategoryRepository;
import com.example.shopping_cart_admin.service.SubCategoryService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SubCategoryDTO> getList() {
        List<SubCategoryEntity> subCategoryEntityData = subCategoryRepository.findAll();
        return subCategoryEntityData
                .stream()
                .map(subCategoryEntity -> modelMapper.map(subCategoryEntity, SubCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubCategoryDTO get(int id) {
        Optional<SubCategoryEntity> subCategoryEntityData = subCategoryRepository.findById((long) id);
        if (subCategoryEntityData.isPresent()) {
            SubCategoryEntity subCategoryEntity = subCategoryEntityData.get();
            return modelMapper.map(subCategoryEntity, SubCategoryDTO.class);
        }
        return null;
    }

    @Override
    public void insert(SubCategoryDTO subCategoryDTO) {
        SubCategoryEntity subCategoryEntity = modelMapper.map(subCategoryDTO, SubCategoryEntity.class);
        subCategoryRepository.save(subCategoryEntity);
    }

    @Override
    public void update(SubCategoryDTO subCategoryDTO) {
        Optional<SubCategoryEntity> subCategoryEntity = subCategoryRepository
                .findById((long) subCategoryDTO.getId());
        if (subCategoryEntity.isPresent()) {
            SubCategoryEntity subCategoryEntityData = subCategoryEntity.get();
            log.info("update data sub category name : {}", subCategoryDTO.getName());
            modelMapper.map(subCategoryDTO, subCategoryEntityData);
            subCategoryRepository.save(subCategoryEntityData);
        }
    }

    @Override
    public void delete(int id) {
        subCategoryRepository.deleteById((long)id);
    }
}
