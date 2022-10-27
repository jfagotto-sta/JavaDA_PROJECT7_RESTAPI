package com.nnk.springboot.ServicesTests;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
public class BidListTest {

    @Autowired
    private BidListService bidListService;

    @BeforeEach
    public  void  deleteall() {
        bidListService.deleteAll();
    }

    @Test
    public void bidListTest() {
        BidList b = new BidList();
        b.setAccount("a");
        b.setType("b");

        // Save
        bidListService.saveNewBidList(b);
        BidList b2 = bidListService.getBidListById(1);
        assertNotNull(b2);

        // Update
        b.setAccount("test");
        bidListService.saveNewBidList(b);
        assertTrue(b.getAccount().equals("test"));

        // Find
        List<BidList> listResult = bidListService.getAllBidList();
        assertTrue(listResult.size() > 0);

        // Delete
        bidListService.deleteBidList(b.getBidListId());
        assertTrue(bidListService.getAllBidList().size()==0);	}
}

