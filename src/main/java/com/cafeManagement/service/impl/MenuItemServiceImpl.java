package com.cafeManagement.service.impl;

import com.cafeManagement.dto.APIResponse;
import com.cafeManagement.dto.MenuItemDto;
import com.cafeManagement.entity.MenuItem;
import com.cafeManagement.repository.MenuItemRepository;
import com.cafeManagement.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MenuItemServiceImpl implements MenuItemService {

    private MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public APIResponse<String> addMenuItem(MenuItemDto menuItemDto) {


        // Check if airline name already exists

        if (menuItemRepository.existsByName(menuItemDto.getName())) {
            APIResponse response = new APIResponse<>();
            response.setMessage("menu-item added Failed !");
            response.setStatus(500);
            response.setData("menu-item with name '" + menuItemDto.getName() + "' already exists.");
            return response;


        }


        // Price validation
        if (menuItemDto.getPrice() <= 0) {
            APIResponse<String> response = new APIResponse<>();
            response.setMessage("Price must be greater than zero");
            response.setStatus(500);
            response.setData(null);
            return response;
        }


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


      if(savedMenuItem == null){
          throw new RuntimeException("menuitem added faild");
      }

                 APIResponse response = new APIResponse<>();
                 response.setMessage("menu-item added successfully");
                 response.setStatus(201);
                 response.setData(resultDto);
                 return response;

    }

    @Override
    public List<MenuItemDto> getAllMenuItems() {
        // Fetch all menu items from DB

        return menuItemRepository.findAll()
                .stream()
                .map(item -> new MenuItemDto(item.getId(), item.getName(), item.getPrice()))
                .collect(Collectors.toList());

    }
}
