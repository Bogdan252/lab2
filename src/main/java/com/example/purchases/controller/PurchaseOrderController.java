package com.example.purchases.controller;

import com.example.purchases.entity.PurchaseOrder;
import com.example.purchases.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrderService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getOrderById(@PathVariable Long id) {
        return purchaseOrderService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody PurchaseOrder order) {
        PurchaseOrder created = purchaseOrderService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PurchaseOrder> updateOrder(@PathVariable Long id, @RequestBody PurchaseOrder order) {
        try {
            PurchaseOrder updated = purchaseOrderService.update(id, order);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        purchaseOrderService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}