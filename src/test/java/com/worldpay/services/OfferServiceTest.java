package com.worldpay.services;

import com.worldpay.model.Offer;
import com.worldpay.repo.OfferRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class OfferServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public OfferService offerService() {
            return new OfferService();
        }
    }

    @Autowired
    private OfferService offerService;

    @MockBean
    private OfferRepo offerRepo;

    @Before
    public void setUp() {
        Offer validOffer1 = new Offer("Buy One Get One free", "Buy One Get One free", LocalDate.now().plus(1, ChronoUnit.DAYS));
        Offer validOffer2 = new Offer("Buy One Get Two free", "Buy One Get Two free", LocalDate.now().plus(1, ChronoUnit.DAYS));
        Offer validOffer3 = new Offer("Buy One Get Three free", "Buy One Get Three free", LocalDate.now().plus(1, ChronoUnit.DAYS));
        Offer expiredOffer1 = new Offer("Buy One Get Four free", "Buy One Get Four free", LocalDate.now());
        Offer canceleddOffer1 = new Offer("Buy One Get Five free", "Buy One Get Foivw free", LocalDate.now().plus(1, ChronoUnit.DAYS),true);

        validOffer1.setId(1L);
        validOffer2.setId(2L);
        validOffer3.setId(3L);
        expiredOffer1.setId(4L);
        canceleddOffer1.setId(5L);



        Mockito.when(offerRepo.findById(1L))
                .thenReturn(Optional.of(validOffer1));

        Mockito.when(offerRepo.findAll()).thenReturn(Arrays.asList(validOffer1,validOffer2,validOffer3,expiredOffer1,canceleddOffer1));

        Mockito.when(offerRepo.findExpiredOffers()).thenReturn(Arrays.asList(expiredOffer1,canceleddOffer1));

        Mockito.when(offerRepo.findValidOffers()).thenReturn(Arrays.asList(validOffer1,validOffer2, validOffer3));


    }


    @Test
    public void testGetAll() {
        //when
        List<Offer> allOffers= offerService.getAllOffers();
        //then
        assertEquals("The list size should 5", 5, allOffers.size());

    }

    @Test
    public void testGetValid() {
        //when
        List<Offer> validOffers= offerService.getValidOffers();
        //then
        assertEquals("The list size should 3", 3, validOffers.size());

    }

    @Test
    public void testGetExpired() {
        //when
        List<Offer> expiredOffers= offerService.getExpiredOffers();
        //then
        assertEquals("The list size should 3", 2, expiredOffers.size());

    }

}
