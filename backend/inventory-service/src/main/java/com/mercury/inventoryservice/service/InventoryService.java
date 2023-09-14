package com.mercury.inventoryservice.service;

import com.mercury.inventoryservice.bean.Inventory;
import com.mercury.inventoryservice.dao.InventoryDao;
import dto.InventoryResponse;
import http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryDao inventoryDao;

    public Inventory getInventoryByTicketId(int id) {
        Optional<Inventory> i = inventoryDao.findByTicketId(id);
        if (i.isPresent()) {
            return i.get();
        } else {
            return null;
        }
    }

    public List<InventoryResponse> getInventoryFromList(List<Integer> inventory_id) {
        return inventoryDao.findInventoryByTicketIdIn(inventory_id).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .ticketId(inventory.getTicketId())
                                .quantity(inventory.getQuantity())
                                .build()
                ).toList();
    }

    public void save(Inventory inventory) {
        inventoryDao.save(inventory);
    }

    public void delete(int id) {
        inventoryDao.deleteById(id);
    }

}
