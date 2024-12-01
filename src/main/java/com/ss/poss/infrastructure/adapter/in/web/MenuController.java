package com.ss.poss.infrastructure.adapter.in.web;

import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.domain.menu.model.MenuWebhook;
import com.ss.poss.domain.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/get-list")
    public ResponseEntity<List<Menu>> getListMenuWebhook(){
        try {
            List<Menu> menuList = menuService.getListMenu();
            return ResponseEntity.ok(menuList);
        } catch (Exception e){
            LOG.error("ERROR WHEN GET LIST OF MENU REQUEST : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create-list")
    public ResponseEntity<List<Menu>> createMenus(@RequestBody List<Menu> menus){
        LOG.info("CREATE LIST MENU REQUEST : {} STARTED", menus.size());
        try {
            List<Menu> menuList = menuService.createMenus(menus);
            return ResponseEntity.ok(menuList);
        } catch (Exception e){
            LOG.error("ERROR WHEN CREATE LIST OF MENU REQUEST : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } finally {
            LOG.info("CREATE LIST MENU REQUEST : {} FINISHED", menus.size());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu){
        LOG.info("CREATE MENU REQUEST : {} STARTED", menu.getMenuId());
        try {
            menu = menuService.createMenu(menu);
            return ResponseEntity.ok(menu);
        } catch (Exception e){
            LOG.error("CREATE WHEN UPDATE LIST OF MENU REQUEST : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } finally {
            LOG.info("CREATE MENU REQUEST : {} FINISHED", menu.getMenuId());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Menu> updateMenu(@RequestBody Menu menu){
        LOG.info("UPDATE MENU REQUEST : {} STARTED", menu.getMenuId());
        try {
            menu = menuService.createMenu(menu);
            return ResponseEntity.ok(menu);
        } catch (Exception e){
            LOG.error("ERROR WHEN UPDATE LIST OF MENU REQUEST : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } finally {
            LOG.info("UPDATE MENU REQUEST : {} FINISHED", menu.getMenuId());
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<MenuWebhook> webhookMenus(@RequestBody MenuWebhook menuWebhook){
        LOG.info("WEBHOOK MENU REQUEST : {} STARTED", menuWebhook);
        try {
            menuService.send(menuWebhook);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            LOG.error("ERROR WHEN WEBHOOK MENU REQUEST : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } finally {
            LOG.info("WEBHOOK MENU REQUEST : {} FINISHED", menuWebhook);
        }
    }
}
