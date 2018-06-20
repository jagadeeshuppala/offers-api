package com.worldpay.controller;

import com.worldpay.exception.OfferNotFoundException;
import com.worldpay.model.Offer;
import com.worldpay.repo.OfferRepo;
import com.worldpay.services.OfferService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@WebMvcTest(OfferController.class)
public class OfferControllerTest {
    @MockBean
    private OfferRepo offerRepo;
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OfferService offerService;

    private Offer validOffer1, validOffer2, validOffer3, expiredOffer1, canceleddOffer1;

    @Before
    public void setUp() {
         validOffer1 = new Offer("Buy One Get One free", "Buy One Get One free", LocalDate.now().plus(1, ChronoUnit.DAYS));
         validOffer2 = new Offer("Buy One Get Two free", "Buy One Get Two free", LocalDate.now().plus(1, ChronoUnit.DAYS));
         validOffer3 = new Offer("Buy One Get Three free", "Buy One Get Three free", LocalDate.now().plus(1, ChronoUnit.DAYS));
         expiredOffer1 = new Offer("Buy One Get Four free", "Buy One Get Four free", LocalDate.now());
         canceleddOffer1 = new Offer("Buy One Get Five free", "Buy One Get Foivw free", LocalDate.now().plus(1, ChronoUnit.DAYS),true);

        validOffer1.setId(1L);
        validOffer2.setId(2L);
        validOffer3.setId(3L);
        expiredOffer1.setId(4L);
        canceleddOffer1.setId(5L);


        Mockito.when(offerService.createOffer(Mockito.any())).thenReturn(validOffer1);

        Mockito.when(offerService.getOffer(1L))
                .thenReturn(validOffer1);

        Mockito.when(offerService.getAllOffers()).thenReturn(Arrays.asList(validOffer1,validOffer2,validOffer3,expiredOffer1,canceleddOffer1));

        Mockito.when(offerService.getExpiredOffers()).thenReturn(Arrays.asList(expiredOffer1,canceleddOffer1));

        Mockito.when(offerService.getValidOffers()).thenReturn(Arrays.asList(validOffer1,validOffer2, validOffer3));

        Mockito.when(offerService.getOffer(100L)).thenThrow(new OfferNotFoundException("message id 100 does not exist"));


    }


    @Test
    public void testCreateOffer() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/offers").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"id\":1,\"offer\":\"Buy One Get One free\",\"description\":\"Buy One Get One free\",\"createdDate\":\"2018-06-20\",\"expiryDate\":\"2018-06-21\",\"cancelOffer\":false}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testCancelOffer() throws Exception{
        mvc.perform(MockMvcRequestBuilders.put("/offers/cancelOffer/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetOffer() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/offers/1").
                contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testGetAllOffers() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/offers").
                contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(5)));
    }

    @Test
    public void testGetValidOffers() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/offers?expired=FALSE").
                contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)));
    }

    @Test
    public void testGetExpiredOffers() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/offers?expired=TRUE").
                contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(2)));
    }

    @Test
    public void testOfferNotFoundException() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/offers/100").
                contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }


}
