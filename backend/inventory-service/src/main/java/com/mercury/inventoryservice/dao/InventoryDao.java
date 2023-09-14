package com.mercury.inventoryservice.dao;

import com.mercury.inventoryservice.bean.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryDao extends JpaRepository<Inventory, Integer> {
    public Optional<Inventory> findByTicketId(int ticket_id);
    public List<Inventory> findInventoryByTicketIdIn(List<Integer> inventory_id);
}
