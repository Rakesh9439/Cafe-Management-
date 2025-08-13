package com.cafeManagement.service.impl;

import com.cafeManagement.dto.MenuItemDto;
import com.cafeManagement.entity.MenuItem;
import com.cafeManagement.repository.MenuItemRepository;
import com.cafeManagement.service.MenuItemService;
import org.springframework.stereotype.Service;


@Service
public class MenuItemServiceImpl implements MenuItemService {

    private MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItemDto addMenuItem(MenuItemDto menuItemDto) {

        // Null check for DTO itself
        if (menuItemDto == null) {
            throw new IllegalArgumentException("Menu item data cannot be null");
        }

        // DTO → Entity
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDto.getName());
        menuItem.setPrice(menuItemDto.getPrice());

        // Save entity
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        // Entity → DTO
        MenuItemDto resultDto = new MenuItemDto();
        resultDto.setId(savedMenuItem.getId());
        resultDto.setName(savedMenuItem.getName());
        resultDto.setPrice(savedMenuItem.getPrice());

        return resultDto;

    }
}
