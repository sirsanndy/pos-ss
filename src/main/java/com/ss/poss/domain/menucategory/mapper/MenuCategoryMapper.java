package com.ss.poss.domain.menucategory.mapper;

import com.ss.poss.domain.menucategory.model.MenuCategory;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuCategoryMapper {
    MenuCategoryEntity toMenuCategoryEntity(MenuCategory menuCategory);
    MenuCategory toMenuCategory(MenuCategory menuCategory);
}
