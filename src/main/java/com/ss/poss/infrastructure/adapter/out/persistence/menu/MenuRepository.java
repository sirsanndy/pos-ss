package com.ss.poss.infrastructure.adapter.out.persistence.menu;

import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, UUID> {
    Optional<MenuEntity> findById(UUID uuid);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("from MenuEntity where menuId = ?1")
    Optional<MenuEntity> findByMenuIdLocked(UUID menuId);

    List<MenuEntity> findAllByOrderByCreatedAtDesc();
}
