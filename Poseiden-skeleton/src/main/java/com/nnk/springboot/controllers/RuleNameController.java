package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;



@Controller
@CrossOrigin(origins="http://localhost:4200")
public class RuleNameController {

    @Autowired
    private RuleNameService ruleNameService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/ruleName/list")
    @ResponseStatus(code = HttpStatus.OK)
    public String home(Model model)
    {
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        logger.info("Liste des rulesnames chargée");
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    @ResponseStatus(code = HttpStatus.OK)
    public String addRuleName(RuleName ruleName) {
        logger.info("Page d'ajout des rulenames chargée");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    @ResponseStatus(code = HttpStatus.OK)
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameService.saveNewRuleName(ruleName);
            model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
            logger.info("Nouvelle rulename ajouté");
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleNameById(id);
        model.addAttribute("ruleName", ruleName);
        logger.info("Page pour la mise à jour d'un rulename chargée");
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleName.setId(id);
        ruleNameService.saveNewRuleName(ruleName);
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        logger.info("Rulename avec l'id "+id+" mit à jour");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRuleName(id);
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        logger.info("rulename avec l'id "+id+" chargé");
        return "redirect:/ruleName/list";
    }
}
