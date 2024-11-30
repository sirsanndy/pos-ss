package com.ss.poss.infrastructure.adapter.out.persistence.menu;

import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    Optional<MenuEntity> findByMenuId(UUID menuId);
}
