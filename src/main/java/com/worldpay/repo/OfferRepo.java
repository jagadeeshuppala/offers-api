package com.worldpay.repo;

import com.worldpay.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface OfferRepo extends JpaRepository<Offer,Long>{

    @Query("SELECT t FROM Offer t where t.expiryDate <= CURRENT_DATE() or t.cancelOffer = TRUE")
    List<Offer> findExpiredOffers();

    @Query("SELECT t FROM Offer t where t.expiryDate > CURRENT_DATE() AND t.cancelOffer = FALSE")
    List<Offer> findValidOffers();

    @Modifying
    @Query("UPDATE Offer t SET t.cancelOffer = TRUE WHERE t.id = :id")
    Integer cancelOffer(@Param("id") Long id);
}
