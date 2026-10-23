package com.example.purchases.controller;

import com.example.purchases.entity.Supplier;
import com.example.purchases.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Чтение всех: GET /api/suppliers (статус 200)
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAll();
    }

    // Чтение по id: GET /api/suppliers/{id} (статус 200 или 404)
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        return supplierService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Создание: POST /api/suppliers (статус 201 Created)
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        Supplier createdSupplier = supplierService.create(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
    }

    // Обновление: PATCH /api/suppliers/{id} (статус 200)
    @PatchMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        try {
            Supplier updatedSupplier = supplierService.update(id, supplier);
            return ResponseEntity.ok(updatedSupplier);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Удаление: DELETE /api/suppliers/{id} (статус 204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}