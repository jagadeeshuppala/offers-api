package com.worldpay.controller;

import com.worldpay.model.Offer;
import com.worldpay.services.OfferService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/offers")
@Api(value="offers - API", description="Operations pertaining to offers")
public class OfferController {
    @Autowired
    private OfferService offerService;

    /**
     * Create the offer, you can just pass offer, description and expiryDate ex: {"offer":"Buy One Get One free","description":"Buy One Get One free","expiryDate":"2018-06-21"}
     */
    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer){
        return new ResponseEntity<>(offerService.createOffer(offer), HttpStatus.CREATED);
    }

    /**
     * Retrieving the offer by providing the id
     * @param id
     * @return ResponseEntity with HttpStatus in the header
     */
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> getOffer(@PathVariable Long id){
        return new ResponseEntity<>(offerService.getOffer(id),HttpStatus.OK);
    }

    /**
     * Gets the offers, if you dont pass any requestParam, it lists all the available offers.
     * If you need the validOffers, pass the requestParam expired = FALSE
     * If you need the expiredOffers, pass the requestParam expired = TRUE
     * @param expired
     * @return ResponseEntity with HttpStatus in the header
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> getAllOffers(@RequestParam(value = "expired", required = false) Boolean expired){
        if(expired!=null && expired){
            return new ResponseEntity<>(offerService.getExpiredOffers(), HttpStatus.OK);
        }else if(expired!=null && !expired){
            return new ResponseEntity<>( offerService.getValidOffers(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>( offerService.getAllOffers(), HttpStatus.OK);
        }
    }

    /**
     * Cancels the offer, need to provide the offerId to cancel it. It returns whether it is success or failure
     * @param id
     * @return ResponseEntity with HttpStatus in the header
     */
    @PutMapping(value = "/cancelOffer/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> cancelOffer(@PathVariable Long id){
        return new ResponseEntity<>(offerService.cancelOffer(id), HttpStatus.OK);
    }
}
