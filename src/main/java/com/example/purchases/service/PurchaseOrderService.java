package com.example.purchases.service;

import com.example.purchases.entity.PurchaseOrder;
import com.example.purchases.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }

    public Optional<PurchaseOrder> getById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    public PurchaseOrder create(PurchaseOrder order) {
        return purchaseOrderRepository.save(order);
    }

    public PurchaseOrder update(Long id, PurchaseOrder updatedOrder) {
        return purchaseOrderRepository.findById(id).map(order -> {
            if (updatedOrder.getStatus() != null) {
                order.setStatus(updatedOrder.getStatus());
            }
            if (updatedOrder.getPurchaseRequest() != null) {
                order.setPurchaseRequest(updatedOrder.getPurchaseRequest());
            }
            if (updatedOrder.getCommercialOffer() != null) {
                order.setCommercialOffer(updatedOrder.getCommercialOffer());
            }
            return purchaseOrderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Purchase order not found with id " + id));
    }

    public void delete(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}