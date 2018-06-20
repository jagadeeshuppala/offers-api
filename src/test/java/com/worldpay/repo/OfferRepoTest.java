package com.worldpay.repo;


import com.worldpay.model.Offer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class OfferRepoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OfferRepo offerRepo;

    @Test
    public void testFindOfferById(){
        //given
        Offer offer = new Offer("Buy One Get One free", "Buy One Get One free", LocalDate.now().plus(1, ChronoUnit.DAYS), false);
        Offer savedOffer = entityManager.persist(offer);
        entityManager.flush();

        //when
        Offer found = offerRepo.findById(savedOffer.getId()).get();

        //then
        assertEquals(offer.getOffer(), found.getOffer());

    }

    @Test
    public void testGetAll(){

        //given
        Offer offer1 = new Offer("Buy One Get One free", "Buy One Get One free", LocalDate.now().plus(1, ChronoUnit.DAYS), false);
        Offer offer2 = new Offer("Buy One Get Two free", "Buy One Get Two free", LocalDate.now().plus(1, ChronoUnit.DAYS), false);
        entityManager.persist(offer1);
        entityManager.persist(offer2);
        entityManager.flush();

        List<Offer> list = offerRepo.findAll();
        assertEquals(6L, list.size());

    }

    @Test
    public void testGetExpired(){

        //given
        Offer offer1 = new Offer("Buy One Get One free", "Buy One Get One free", LocalDate.now(), false);
        Offer offer2 = new Offer("Buy One Get Two free", "Buy One Get Two free", LocalDate.now(), false);
        entityManager.persist(offer1);
        entityManager.persist(offer2);
        entityManager.flush();

        List<Offer> list = offerRepo.findExpiredOffers();
        assertTrue("The list size should be greater than or equal to 2", list.size()>=2);

    }

    @Test
    public void testGetValid(){

        //given
        Offer offer1 = new Offer("Buy One Get One free", "Buy One Get One free", LocalDate.now().plus(1, ChronoUnit.DAYS), false);
        Offer offer2 = new Offer("Buy One Get Two free", "Buy One Get Two free", LocalDate.now().plus(1, ChronoUnit.DAYS), false);
        entityManager.persist(offer1);
        entityManager.persist(offer2);
        entityManager.flush();

        List<Offer> list = offerRepo.findExpiredOffers();
        assertTrue("The list size should be greater than or equal to 2", list.size()>=2);

    }

    @Test
    public void testCancelOffer(){
        //given there are 4 records already in the database

        //when
        offerRepo.cancelOffer(1L);
        Offer found = offerRepo.findById(1L).get();

        //then
        assertTrue("This offer's cancelOrder should be true", found.getCancelOffer());
    }





}
