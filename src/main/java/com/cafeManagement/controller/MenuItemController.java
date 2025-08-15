package com.cafeManagement.controller;

import com.cafeManagement.dto.APIResponse;
import com.cafeManagement.dto.MenuItemDto;
import com.cafeManagement.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<APIResponse<String>> addMenuItem(@RequestBody MenuItemDto menuItemDto) {
        APIResponse<String> response = menuItemService.addMenuItem(menuItemDto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }



    // ✅ Get all menu items
    @GetMapping("/getAll")
    public ResponseEntity<List<MenuItemDto>> getAllMenuItems() {
        List<MenuItemDto> items = menuItemService.getAllMenuItems();
        return ResponseEntity.ok(items);
    }
}
