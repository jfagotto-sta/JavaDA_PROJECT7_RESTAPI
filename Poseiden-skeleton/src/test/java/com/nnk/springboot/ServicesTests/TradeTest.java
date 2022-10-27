package com.nnk.springboot.ServicesTests;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.TradeService;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class TradeTest {

    @Autowired
    private TradeService tradeService;

    @BeforeEach
    public void deleteall() {
        tradeService.deleteAll();
    }

    @Test
    public void tradeTest() {
        Trade t = new Trade();
        t.setAccount("a");
        t.setType("b");


        // Save
        tradeService.saveTrade(t);
        Trade t2 = tradeService.getTradeById(1);
        assertNotNull(t2);

        // Update
        t.setType("ab");
        tradeService.saveTrade(t);
        assertTrue(t.getType().equals("ab"));

        // Find
        List<Trade> listResult = tradeService.getAllTrades();
        assertTrue(listResult.size() > 0);

        // Delete
        tradeService.deleteTrade(t.getTradeId());
        tradeService.deleteAll();
        assertTrue(tradeService.getAllTrades().size()==0);	}
}
