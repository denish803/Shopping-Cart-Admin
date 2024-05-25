package com.example.shopping_cart_admin.repository;

import com.example.shopping_cart_admin.entity.SingUpInEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SingUpInRepository extends JpaRepository<SingUpInEntity, Long> {

    Optional<SingUpInEntity> getByEmail(String email);

}
