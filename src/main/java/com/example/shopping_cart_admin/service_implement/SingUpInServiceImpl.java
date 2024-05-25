package com.example.shopping_cart_admin.service_implement;

import com.example.shopping_cart_admin.dto.SingUpInDTO;
import com.example.shopping_cart_admin.entity.SingUpInEntity;
import com.example.shopping_cart_admin.repository.SingUpInRepository;
import com.example.shopping_cart_admin.service.SingUpInService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class SingUpInServiceImpl implements SingUpInService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SingUpInRepository singUpInRepository;

    @Override
    public void insert(SingUpInDTO singUpDTO) {
        try {
            SingUpInEntity singUpEntity = modelMapper.map(singUpDTO, SingUpInEntity.class);
            singUpInRepository.save(singUpEntity);
        } catch (Exception e) {
            log.error("Sing up in method ", e);
        }
    }

    @Override
    public boolean singIn(String email, String password) {
        boolean isVaild = false;
        Optional<SingUpInEntity> userData = singUpInRepository.getByEmail(email);
        if (userData.isPresent()) {
            SingUpInEntity singInEntity = userData.get();
            String originalPassword = singInEntity.getPassword();
            isVaild = originalPassword.equals(password);
        }
        return isVaild;
    }
}
