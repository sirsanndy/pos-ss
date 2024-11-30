package com.ss.poss.domain.menu.mapper;

import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuEntity toEntity(Menu menu);
    Menu toMenu(MenuEntity menuEntity);
}
