package com.example.purchases.service;

import com.example.purchases.entity.PurchaseRequest;
import com.example.purchases.repository.PurchaseRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseRequestService {

    @Autowired
    private PurchaseRequestRepository purchaseRequestRepository;

    public List<PurchaseRequest> getAll() {
        return purchaseRequestRepository.findAll();
    }

    public Optional<PurchaseRequest> getById(Long id) {
        return purchaseRequestRepository.findById(id);
    }

    public PurchaseRequest create(PurchaseRequest request) {
        return purchaseRequestRepository.save(request);
    }

    public PurchaseRequest update(Long id, PurchaseRequest updatedRequest) {
        return purchaseRequestRepository.findById(id).map(request -> {
            if (updatedRequest.getDescription() != null) {
                request.setDescription(updatedRequest.getDescription());
            }
            if (updatedRequest.getLimitAmount() != null) {
                request.setLimitAmount(updatedRequest.getLimitAmount());
            }
            if (updatedRequest.getStatus() != null) {
                request.setStatus(updatedRequest.getStatus());
            }
            if (updatedRequest.getAuthor() != null) {
                request.setAuthor(updatedRequest.getAuthor());
            }
            return purchaseRequestRepository.save(request);
        }).orElseThrow(() -> new RuntimeException("Purchase request not found with id " + id));
    }

    public void delete(Long id) {
        purchaseRequestRepository.deleteById(id);
    }
}