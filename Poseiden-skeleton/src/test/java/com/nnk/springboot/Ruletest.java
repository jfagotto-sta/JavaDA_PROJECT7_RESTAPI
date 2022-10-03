package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.RuleNameService;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
public class Ruletest {

    @Autowired
    private RuleNameService ruleNameService;

    @BeforeAll
    public void deleteall() {
        ruleNameService.deleteAll();
    }

    @Test
    public void ruleNameTest() {
        RuleName r = new RuleName();
        r.setName("a");

        // Save
        ruleNameService.saveNewRuleName(r);
        RuleName r2 = ruleNameService.getRuleNameById(1);
        assertNotNull(r2);

        // Update
        r.setName("b");
        ruleNameService.saveNewRuleName(r);
        assertTrue(r.getName().equals("b"));

        // Find
        List<RuleName> listResult = ruleNameService.getAllRuleNames();
        assertTrue(listResult.size() > 0);

        // Delete
        ruleNameService.deleteRuleName(r.getId());
        assertTrue(ruleNameService.getAllRuleNames().size()==0);	}
}

