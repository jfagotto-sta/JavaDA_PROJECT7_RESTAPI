package com.nnk.springboot.controllers;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@Controller
public class RuleNameController {
    // TODO: Inject RuleName service

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @RequestMapping("/ruleName/list")
    public List<RuleName> ruleNameList( )
    {
        return ruleNameRepository.findAll();
    }

    @PostMapping("/ruleName/add")
    public RuleName addRuleForm(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    @GetMapping("/rulename/id")
    public RuleName getRulenameById(@RequestParam int id){
        return ruleNameRepository.getById(id);
    }

    @PostMapping("rulename/update")
    public RuleName updateUser(@RequestBody RuleName ruleName){
        RuleName r = ruleNameRepository.getById(ruleName.getId());
       r.setDescription(ruleName.getDescription());
        r.setName(ruleName.getName());
        r.setjSon(ruleName.getjSon());
        r.setTemplate(ruleName.getTemplate());
        r.setSqlPart(ruleName.getSqlPart());
        r.setSqlStr(ruleName.getSqlStr());
        ruleNameRepository.save(r);
        return r;
    }

    @DeleteMapping("rulename/delete/id")
    public Boolean deleteRulename(@RequestParam int id){
        RuleName r = ruleNameRepository.getById(id);
        ruleNameRepository.delete(r);
        return true;
    }


//    @PostMapping("/ruleName/validate")
//    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
//        // TODO: check data valid and save to db, after saving return RuleName list
//        return "ruleName/add";
//    }
//
//    @GetMapping("/ruleName/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
//        // TODO: get RuleName by Id and to model then show to the form
//        return "ruleName/update";
//    }
//
//    @PostMapping("/ruleName/update/{id}")
//    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
//                             BindingResult result, Model model) {
//        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
//        return "redirect:/ruleName/list";
//    }
//
//    @GetMapping("/ruleName/delete/{id}")
//    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
//        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
//        return "redirect:/ruleName/list";
//    }
}
