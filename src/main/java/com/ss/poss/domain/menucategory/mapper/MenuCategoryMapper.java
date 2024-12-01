package com.ss.poss.domain.menucategory.mapper;

import com.ss.poss.domain.menucategory.model.MenuCategory;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuCategoryEntity;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MenuCategoryMapper {
    MenuCategoryEntity toMenuCategoryEntity(MenuCategory menuCategory);
    MenuCategory toMenuCategory(MenuCategoryEntity menuCategoryEntity);
    MenuCategoryEntity map(UUID menuCategoryId);
}
