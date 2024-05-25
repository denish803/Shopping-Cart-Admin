package com.example.shopping_cart_admin.repository;

import com.example.shopping_cart_admin.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("select s.id from CategoryEntity s where s.name = :name")
    Optional<CategoryEntity> getIdByName(String name);

}
