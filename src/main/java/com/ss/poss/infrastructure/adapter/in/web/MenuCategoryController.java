package com.ss.poss.infrastructure.adapter.in.web;

import com.ss.poss.domain.menucategory.model.MenuCategory;
import com.ss.poss.domain.menucategory.service.MenuCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/menu-category")
public class MenuCategoryController {
    private static final Logger LOG = LoggerFactory.getLogger(MenuCategoryController.class);

    private final MenuCategoryService menuCategoryService;

    public MenuCategoryController(MenuCategoryService menuCategoryService) {
        this.menuCategoryService = menuCategoryService;
    }

    @GetMapping("/get-list")
    public ResponseEntity<List<MenuCategory>> getMenuCategories() {
        LOG.info("GET LIST MENU CATEGORY REQUEST IS STARTED");
        try {
            List<MenuCategory> menuCategoryList = menuCategoryService.getListMenuCategory();
            return ResponseEntity.ok(menuCategoryList);
        } catch (Exception e){
            LOG.error("GET LIST MENU CATEGORY REQUEST IS ERROR WITH ERROR MESSAGE : {} ", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOG.info("GET LIST MENU CATEGORY REQUEST IS FINISHED");
        }
    }

    @GetMapping("/get/{menuCategoryId}")
    public ResponseEntity<MenuCategory> getMenuCategories(@PathVariable UUID menuCategoryId) {
        LOG.info("GET MENU CATEGORY REQUEST IS STARTED");
        try {
            MenuCategory menuCategory = menuCategoryService.getMenuCategory(menuCategoryId);
            return ResponseEntity.ok(menuCategory);
        } catch (Exception e){
            LOG.error("GET MENU CATEGORY REQUEST IS ERROR WITH ERROR MESSAGE : {} ", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOG.info("GET MENU CATEGORY REQUEST IS FINISHED");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<MenuCategory> createMenuCategory(@RequestBody MenuCategory menuCategory) {
        LOG.info("CREATE MENU CATEGORY REQUEST IS STARTED");
        try {
            menuCategory = menuCategoryService.createMenuCategory(menuCategory);
            return ResponseEntity.ok(menuCategory);
        } catch (Exception e){
            LOG.error("CREATE MENU CATEGORY REQUEST IS ERROR WITH ERROR MESSAGE : {} ", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOG.info("CREATE MENU CATEGORY REQUEST IS FINISHED");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<MenuCategory> updateMenuCategory(@RequestBody MenuCategory menuCategory) {
        LOG.info("UPDATE MENU CATEGORY REQUEST IS STARTED");
        try {
            menuCategory = menuCategoryService.createMenuCategory(menuCategory);
            return ResponseEntity.ok(menuCategory);
        } catch (Exception e){
            LOG.error("UPDATE MENU CATEGORY REQUEST IS ERROR WITH ERROR MESSAGE : {} ", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOG.info("UPDATE MENU CATEGORY REQUEST IS FINISHED");
        }
    }

    @PostMapping("/create-list")
    public ResponseEntity<List<MenuCategory>> createListMenuCategories(@RequestBody List<MenuCategory> menuCategories) {
        LOG.info("CREATE LIST MENU CATEGORY REQUEST IS STARTED");
        try {
            List<MenuCategory> menuCategoryList = menuCategoryService.createListMenuCategory(menuCategories);
            return ResponseEntity.ok(menuCategoryList);
        } catch (Exception e){
            LOG.error("CREATE LIST MENU CATEGORY REQUEST IS ERROR WITH ERROR MESSAGE : {} ", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOG.info("CREATE LIST MENU CATEGORY REQUEST IS FINISHED");
        }
    }
}
