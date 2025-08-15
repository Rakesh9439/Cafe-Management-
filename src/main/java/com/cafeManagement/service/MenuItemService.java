package com.cafeManagement.service;

import com.cafeManagement.dto.APIResponse;
import com.cafeManagement.dto.MenuItemDto;

import java.util.List;

public interface MenuItemService {

    APIResponse<String> addMenuItem(MenuItemDto menuItemDto);

    List<MenuItemDto> getAllMenuItems();

}
