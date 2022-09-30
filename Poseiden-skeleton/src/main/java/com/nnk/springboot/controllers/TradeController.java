package com.nnk.springboot.controllers;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
   private TradeRepository tradeRepository;



    @GetMapping("/trade/list")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Trade> getTradeList(Model model)
    {
        model.addAttribute("trades",tradeRepository.findAll());
        logger.info("Liste des trade chargée");
        return tradeRepository.findAll();
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade trade) {
        logger.info("Page d'ajout des trades chargée");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeRepository.save(trade);
            model.addAttribute("trades", tradeRepository.findAll());
            logger.info("Nouveau trade ajouté");
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public Trade getTradeById(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        model.addAttribute("trade",tradeRepository.getById(id));
        logger.info("Page pour la mise à jour d'un trade chargée");
        return tradeRepository.getById(id);
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        logger.info("Trade avec l'id "+id+" mit à jour");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeRepository.deleteById(id);
        model.addAttribute("trades", tradeRepository.findAll());
        logger.info("trade avec l'id "+id+" chargé");
        return "redirect:/trade/list";
    }
}
