package com.nnk.springboot.services;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    public Trade getTradeById (Integer id){
        return tradeRepository.getById(id);
    }

    public List<Trade> getAllTrades(){
        return tradeRepository.findAll();
    }

    public void saveTrade(Trade trade){
        tradeRepository.save(trade);
    }

    public void deleteTrade(Integer id){
        Trade t = tradeRepository.getById(id);
        tradeRepository.delete(t);
    }

    public void deleteAll(){
        tradeRepository.deleteAll();
    }
}
