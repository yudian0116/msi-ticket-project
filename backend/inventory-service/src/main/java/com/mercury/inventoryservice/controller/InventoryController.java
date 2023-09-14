package com.mercury.inventoryservice.controller;

import com.mercury.inventoryservice.bean.Inventory;
import com.mercury.inventoryservice.service.InventoryService;
import dto.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable int id) {
        return inventoryService.getInventoryByTicketId(id);
    }

    @GetMapping("/list")
    public List<InventoryResponse> getInventoryByIdIn(@RequestParam List<Integer> ids) {
        return inventoryService.getInventoryFromList(ids);
    }

    @PostMapping
    public void save(@RequestBody Inventory inventory) {
        inventoryService.save(inventory);
    }

    @PutMapping
    public void update(@RequestBody Inventory inventory) {
        inventoryService.save(inventory);
    }

    @PutMapping("/multi")
    public void updateMulti(@RequestBody List<Inventory> inventories) {
        for (Inventory inventory : inventories) {
            inventoryService.save(inventory);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        inventoryService.delete(id);
    }
}
