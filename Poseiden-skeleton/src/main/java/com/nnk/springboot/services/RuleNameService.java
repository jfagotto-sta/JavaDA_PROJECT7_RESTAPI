package com.nnk.springboot.services;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    public RuleName getRuleNameById (Integer id){
        return ruleNameRepository.getById(id);
    }

    public List<RuleName> getAllRuleNames(){
        return ruleNameRepository.findAll();
    }

    public void saveNewRuleName(RuleName ruleName){
        ruleNameRepository.save(ruleName);
    }

    public void deleteRuleName(Integer id){
        RuleName r = ruleNameRepository.getById(id);
        ruleNameRepository.delete(r);
    }

    public void deleteAll(){
        ruleNameRepository.deleteAll();
    }



}
