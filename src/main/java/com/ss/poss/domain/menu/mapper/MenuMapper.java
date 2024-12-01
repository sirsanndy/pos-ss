package com.ss.poss.domain.menu.mapper;

import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.domain.menucategory.mapper.MenuCategoryMapper;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuCategoryEntity;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MenuCategoryMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MenuMapper {
    @Mapping (source = "menuCategoryId", target = "menuCategory")
    MenuEntity toEntity(Menu menu);

    @Mapping(source = "menuCategory.menuCategoryId", target = "menuCategoryId")
    Menu toMenu(MenuEntity menuEntity);
}
