package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    public BidList getBidListById (Integer id){
        return bidListRepository.getById(id);
    }

    public List<BidList> getAllBidList(){
        return bidListRepository.findAll();
    }

    public void saveNewBidList(BidList bidList){
        bidListRepository.save(bidList);
    }

    public void deleteBidList(Integer id){
       BidList b = bidListRepository.getById(id);
        bidListRepository.delete(b);
    }

    public void deleteAll(){
        bidListRepository.deleteAll();
    }

}
