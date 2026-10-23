package com.example.purchases.service;

import com.example.purchases.entity.Supplier;
import com.example.purchases.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getById(Long id) {
        return supplierRepository.findById(id);
    }

    public Supplier create(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier update(Long id, Supplier updatedSupplier) {
        return supplierRepository.findById(id).map(supplier -> {
            if (updatedSupplier.getName() != null) {
                supplier.setName(updatedSupplier.getName());
            }
            if (updatedSupplier.getCategory() != null) {
                supplier.setCategory(updatedSupplier.getCategory());
            }
            if (updatedSupplier.getIsApproved() != null) {
                supplier.setIsApproved(updatedSupplier.getIsApproved());
            }
            return supplierRepository.save(supplier);
        }).orElseThrow(() -> new RuntimeException("Supplier not found with id " + id));
    }

    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }
}