package com.cafeManagement.service;

import com.cafeManagement.dto.APIResponse;
import com.cafeManagement.dto.MenuItemDto;
import com.cafeManagement.entity.MenuItem;
import com.cafeManagement.repository.MenuItemRepository;
import com.cafeManagement.service.impl.MenuItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MenuItemServiceImplTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private MenuItemServiceImpl menuItemService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    // 1️⃣ Duplicate Name
    @Test
    void testAddMenuItem_DuplicateName() {
        MenuItemDto dto = new MenuItemDto(null, "Coffee", 100.0);

        // Mock repository behavior
        when(menuItemRepository.existsByName("Coffee")).thenReturn(true);

        // Call service
        APIResponse<String> response = menuItemService.addMenuItem(dto);

        // ✅ Assertions
        assertEquals(500, response.getStatus());
        assertTrue(response.getData().contains("already exists"));

        // ✅ Mockito verify
        verify(menuItemRepository, never()).save(any(MenuItem.class));

        // 🖨 Print API response in console
        System.out.println("API Response: " + response);
        System.out.println("Status: " + response.getStatus());
        System.out.println("Message: " + response.getMessage());
        System.out.println("Data: " + response.getData());
    }


    @Test
    void testAddMenuItem_PriceLessThanOrEqualZero() {
        // Arrange (Input data)
        MenuItemDto dto = new MenuItemDto(null, "Tea", 0.0); // Price 0

        // Act (Call method)
        APIResponse<String> response = menuItemService.addMenuItem(dto);

        // Assert (Expected Output)
        assertEquals(500, response.getStatus());
        assertEquals("Price must be greater than zero", response.getMessage());
        assertNull(response.getData());

        // Verify repository save never called
        verify(menuItemRepository, never()).save(any(MenuItem.class));

        // Print response in console for debug

        // Print API Response for debugging
        System.out.println("----- API Response -----");
        System.out.println("Status  : " + response.getStatus());
        System.out.println("Message : " + response.getMessage());
        System.out.println("Data    : " + response.getData());
    }
}
