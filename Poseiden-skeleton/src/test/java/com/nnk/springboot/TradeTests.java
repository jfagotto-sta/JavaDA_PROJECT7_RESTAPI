package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeService tradeService;

	@Test
	public void tradeTest() {
		Trade trade = new Trade();
		trade.setType("a");
		trade.setAccount("b");

		// Save
        tradeService.saveTrade(trade);
		Trade t = tradeService.getTradeById(1);
        assertNotNull(t);
		assertTrue(t.getAccount().equals("b"));

		// Update
		trade.setAccount("Trade Account Update");
		tradeService.saveTrade(trade);
		assertTrue(trade.getAccount().equals("Trade Account Update"));

		// Find
		List<Trade> listResult = tradeService.getAllTrades();
		assertTrue(listResult.size() > 0);
//
//		// Delete
//		Integer id = trade.getId();
//		tradeService.getTradeById(id);
//
//		assertNull(tradeService.getTradeById(1));	}
} }
