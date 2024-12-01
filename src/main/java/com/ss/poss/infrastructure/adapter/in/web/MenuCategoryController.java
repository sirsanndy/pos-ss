package com.ss.poss.infrastructure.adapter.in.web;

import com.ss.poss.domain.menucategory.model.MenuCategory;
import com.ss.poss.domain.menucategory.service.MenuCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu-category")
public class MenuCategoryController {
    private static final Logger LOG = LoggerFactory.getLogger(MenuCategoryController.class);

    private final MenuCategoryService menuCategoryService;

    public MenuCategoryController(MenuCategoryService menuCategoryService) {
        this.menuCategoryService = menuCategoryService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<MenuCategory>> getMenuCategories() {
        LOG.info("GET LIST MENU CATEGORY REQUEST IS STARTED");
        try {
            List<MenuCategory> menuCategoryList = menuCategoryService.getListMenuCategory();
            return ResponseEntity.ok(menuCategoryList);
        } catch (Exception e){
            LOG.error("GET LIST MENU CATEGORY REQUEST IS ERROR WITH ERROR MESSAGE : {} "e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOG.info("GET LIST MENU CATEGORY REQUEST IS FINISHED");
        }
    }
}
