package com.ss.poss.infrastructure.adapter.out.persistence.menu;

import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
}
