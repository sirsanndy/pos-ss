package com.ss.poss.infrastructure.adapter.out.persistence.menucategory;

import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuCategoryRepository extends JpaRepository<MenuCategoryEntity, UUID> {
    List<MenuCategoryEntity> findAllByOrderByCreatedAtDesc();
}
