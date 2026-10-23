package com.example.purchases.repository;

import com.example.purchases.entity.CommercialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialOfferRepository extends JpaRepository<CommercialOffer, Long> {
}