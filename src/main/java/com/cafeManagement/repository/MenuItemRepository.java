package com.cafeManagement.repository;

import com.cafeManagement.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {


    boolean existsByName(String name);
}