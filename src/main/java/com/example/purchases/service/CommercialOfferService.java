package com.example.purchases.service;

import com.example.purchases.entity.CommercialOffer;
import com.example.purchases.repository.CommercialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommercialOfferService {

    @Autowired
    private CommercialOfferRepository commercialOfferRepository;

    public List<CommercialOffer> getAll() {
        return commercialOfferRepository.findAll();
    }

    public Optional<CommercialOffer> getById(Long id) {
        return commercialOfferRepository.findById(id);
    }

    public CommercialOffer create(CommercialOffer offer) {
        return commercialOfferRepository.save(offer);
    }

    public CommercialOffer update(Long id, CommercialOffer updatedOffer) {
        return commercialOfferRepository.findById(id).map(offer -> {
            if (updatedOffer.getPrice() != null) {
                offer.setPrice(updatedOffer.getPrice());
            }
            if (updatedOffer.getDeliveryTerm() != null) {
                offer.setDeliveryTerm(updatedOffer.getDeliveryTerm());
            }
            if (updatedOffer.getValidityPeriod() != null) {
                offer.setValidityPeriod(updatedOffer.getValidityPeriod());
            }
            if (updatedOffer.getPurchaseRequest() != null) {
                offer.setPurchaseRequest(updatedOffer.getPurchaseRequest());
            }
            if (updatedOffer.getSupplier() != null) {
                offer.setSupplier(updatedOffer.getSupplier());
            }
            return commercialOfferRepository.save(offer);
        }).orElseThrow(() -> new RuntimeException("Commercial offer not found with id " + id));
    }

    public void delete(Long id) {
        commercialOfferRepository.deleteById(id);
    }
}