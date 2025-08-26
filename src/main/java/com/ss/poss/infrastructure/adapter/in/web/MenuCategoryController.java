package com.ss.poss.infrastructure.adapter.in.web;

import com.ss.poss.application.port.in.menucategory.CreateMenuCategoryUseCase;
import com.ss.poss.application.port.in.menucategory.GetListMenuCategoryUseCase;
import com.ss.poss.application.port.in.menucategory.GetMenuCategoryUseCase;
import com.ss.poss.domain.menucategory.model.MenuCategory;
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

    private final CreateMenuCategoryUseCase createMenuCategoryUseCase;
    private final GetMenuCategoryUseCase getMenuCategoryUseCase;
    private final GetListMenuCategoryUseCase getListMenuCategoryUseCase;

    public MenuCategoryController(CreateMenuCategoryUseCase createMenuCategoryUseCase, GetMenuCategoryUseCase getMenuCategoryUseCase, GetListMenuCategoryUseCase getListMenuCategoryUseCase) {
        this.createMenuCategoryUseCase = createMenuCategoryUseCase;
        this.getMenuCategoryUseCase = getMenuCategoryUseCase;
        this.getListMenuCategoryUseCase = getListMenuCategoryUseCase;
    }

    @GetMapping("/get-list")
    public ResponseEntity<List<MenuCategory>> getMenuCategories() {
        LOG.info("GET LIST MENU CATEGORY REQUEST IS STARTED");
        try {
            List<MenuCategory> menuCategoryList = getListMenuCategoryUseCase.getListMenuCategory();
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
            MenuCategory menuCategory = getMenuCategoryUseCase.getMenuCategoryById(menuCategoryId);
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
            menuCategory = createMenuCategoryUseCase.createMenuCategory(menuCategory);
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
            menuCategory = createMenuCategoryUseCase.createMenuCategory(menuCategory);
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
            List<MenuCategory> menuCategoryList = createMenuCategoryUseCase.createListMenuCategory(menuCategories);
            return ResponseEntity.ok(menuCategoryList);
        } catch (Exception e){
            LOG.error("CREATE LIST MENU CATEGORY REQUEST IS ERROR WITH ERROR MESSAGE : {} ", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOG.info("CREATE LIST MENU CATEGORY REQUEST IS FINISHED");
        }
    }
}
