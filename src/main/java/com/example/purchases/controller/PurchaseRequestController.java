package com.example.purchases.controller;

import com.example.purchases.entity.PurchaseRequest;
import com.example.purchases.service.PurchaseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-requests")
public class PurchaseRequestController {

    @Autowired
    private PurchaseRequestService purchaseRequestService;

    @GetMapping
    public List<PurchaseRequest> getAllRequests() {
        return purchaseRequestService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseRequest> getRequestById(@PathVariable Long id) {
        return purchaseRequestService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<PurchaseRequest> createRequest(@RequestBody PurchaseRequest request) {
        PurchaseRequest created = purchaseRequestService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PurchaseRequest> updateRequest(@PathVariable Long id, @RequestBody PurchaseRequest request) {
        try {
            PurchaseRequest updated = purchaseRequestService.update(id, request);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        purchaseRequestService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}