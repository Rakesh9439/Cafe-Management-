package com.cafeManagement.service;

import com.cafeManagement.dto.MenuItemDto;

import java.util.List;

public interface MenuItemService {

    MenuItemDto addMenuItem(MenuItemDto menuItemDto);

    List<MenuItemDto> getAllMenuItems();

}
