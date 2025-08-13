package com.cafeManagement.controller;

import com.cafeManagement.dto.MenuItemDto;
import com.cafeManagement.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }
       //     http://localhost:8088/api/menu-items/addItems

    // Add Menu Item
    @PostMapping("/addItems")
    public ResponseEntity<MenuItemDto> addMenuItem(@RequestBody MenuItemDto menuItemDto) {
        MenuItemDto savedItem = menuItemService.addMenuItem(menuItemDto);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }
}
