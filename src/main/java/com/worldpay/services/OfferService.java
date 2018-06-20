package com.worldpay.services;

import com.worldpay.exception.OfferNotFoundException;
import com.worldpay.model.Offer;
import com.worldpay.repo.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    @Autowired
    private OfferRepo offerRepo;

    public Offer createOffer(Offer offer){
       return offerRepo.save(offer);
    }

    public Offer getOffer(Long id){
        Optional<Offer> offer = offerRepo.findById(id);
        if(offer.isPresent()){
            return offerRepo.findById(id).get();
        }else{
            throw new OfferNotFoundException(" offer with "+ id + " does not exist");
        }

    }

    public List<Offer> getAllOffers(){
        return offerRepo.findAll();
    }

    public List<Offer> getValidOffers(){
        return offerRepo.findValidOffers();
    }
    public List<Offer> getExpiredOffers(){
        return offerRepo.findExpiredOffers();
    }

    public Boolean cancelOffer(Long id){
        int count = offerRepo.cancelOffer(id);
        if(count == 1){
            return true;
        }
        return false;
    }
}
