package com.ss.poss.infrastructure.adapter.in.web;

import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.domain.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/webhook")
    public ResponseEntity<List<Menu>> getListMenuWebhook(){
        try {
            List<Menu> menuList = menuService.getListMenu();
            return ResponseEntity.ok(menuList);
        } catch (Exception e){
            LOG.error("ERROR WHEN GET LIST OF MENU REQUEST : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
